package uk.ac.ncl.twitterrank;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.la4j.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetClassificationRepository;
import br.les.opus.twitter.repositories.TwitterUserRepository;
import uk.ac.ncl.twitterrank.generator.TeleportationVectorGenerator;
import uk.ac.ncl.twitterrank.generator.TransitionProbabilityMatrixGenerator;
import uk.ac.ncl.twitterrank.normalizer.InterestNormalizer;
import uk.ac.ncl.twitterrank.normalizer.ParticipationNormalizer;
import uk.ac.ncl.twitterrank.persist.TwitterRankPersistence;

@Component
@Transactional
public class TwitterRank {
	
	private static final Double GAMMA = 0.85;
	
	private static final Integer ITERACTIONS = 1000;
	
	private static final Double TOLERANCE = 1e-16;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TwitterUserRepository userDao;
	
	@Autowired
	private TweetClassificationRepository topicDao;
	
	@Autowired
	private TransitionProbabilityMatrixGenerator ptGenerator;
	
	@Autowired
	private TeleportationVectorGenerator etGenerator;
	
	@Autowired
	private TwitterRankPersistence twitterRankPersistence;
	
	@Autowired
	private InterestNormalizer interestNormalizer;
	
	@Autowired
	private ParticipationNormalizer participationNormalizer;
	
	private List<TwitterUser> users;
	
	private Vector oldTrVector;
	
	private Vector trVector;
	
	private TransitionProbabilityMatrix ptMatrix;
	
	private Vector teleportationVector;
	
	private Double computeTwitterRankFor(int userIndex) {
		TwitterUser user = this.users.get(userIndex);
		Double tr = 0d;
		for (int i = 0; i < this.users.size(); i++) {
			TwitterUser anotherOne = this.users.get(i);
			Double pt = this.ptMatrix.getPt(user, anotherOne);
			Double oldTr = this.oldTrVector.get(i);
			tr += GAMMA * pt * oldTr;
		}
		return tr + (1 - GAMMA) * teleportationVector.get(userIndex);
	}
	
	private void computeTwitterRankForAllUsers() {
		for (int i = 0; i < this.users.size(); i++) {
			Double tr = computeTwitterRankFor(i);
			trVector.set(i, tr);
		}
		
	}
	
	private void computeTwitterRankForTopic(TweetClassification topic) {
		this.users = this.userDao.findAllRelevant();
		this.ptMatrix = this.ptGenerator.generate(topic);

		/*
		 * The next block does:
		 * - copies the tr vector to check the convergence
		 * - starts the twitter rank vector with the teleportation vector
		 * - copies the tr vector to check the convergence
		 * 
		 * The algorithm ensures that the order of users of the list
		 * is the same of the following vectors. In this way, if a user is in
		 * position i of the users list, its twitter rank will be at tr[i]
		 */
		this.teleportationVector = this.etGenerator.generate(this.users, topic);
		this.trVector = teleportationVector.copy();
		this.oldTrVector = this.trVector.copy();
		
		/*
		 * Run the TwitterRank algorithm until convergence. If convergence is
		 * not obtained, stops the execution in the 1000th iteration 
		 */
		for (int i = 0; i < ITERACTIONS; i++) {
			logger.info("Iteraction: " + i);
			this.computeTwitterRankForAllUsers();
			Double distance = trVector.subtract(oldTrVector).euclideanNorm();
			if (distance <= TOLERANCE) {
				logger.info("Convergence obtained in iteraction " + i);
				break;
			}
			this.oldTrVector = this.trVector.copy();
		}
	}
	
	public void computeForAllTopics() {
		logger.info("Starting the computation of all twitter ranks for all topics...");
		
		logger.info("Normalizing the interests...");
		interestNormalizer.normalize();
		logger.info("Normalizing the participations...");
		participationNormalizer.normalize();
		logger.info("Done normalizing!");
		
		List<TweetClassification> topics = topicDao.findAllUsedInTwitterRank();
		for (TweetClassification topic : topics) {
			logger.info("Computing twitter rank for " + topic);
			this.computeTwitterRankForTopic(topic);
			logger.info("Saving the results for " + topic);
			this.twitterRankPersistence.save(this.trVector, this.users, topic);
		}
		logger.info("Done! \\o/");
	}
}

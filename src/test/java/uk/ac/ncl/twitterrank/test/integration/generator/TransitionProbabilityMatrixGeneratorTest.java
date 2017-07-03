package uk.ac.ncl.twitterrank.test.integration.generator;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;
import br.les.opus.twitter.repositories.TwitterUserRepository;
import uk.ac.ncl.twitterrank.TransitionProbabilityMatrix;
import uk.ac.ncl.twitterrank.generator.TransitionProbabilityMatrixGenerator;
import uk.ac.ncl.twitterrank.normalizer.InterestNormalizer;
import uk.ac.ncl.twitterrank.normalizer.ParticipationNormalizer;
import uk.ac.ncl.twitterrank.test.util.DbTestUtil;

/**
 * The expected values were generated using the tp.xlsx spreadsheet in the docs folder
 * @author Diego Cedrim
 */
public class TransitionProbabilityMatrixGeneratorTest extends DbTestUtil {

	@Autowired
	private TransitionProbabilityMatrixGenerator generator;
	
	@Autowired
	private TwitterUserRepository userDao;
	
	@Autowired
	private ParticipationNormalizer participationNormalizer;
	
	@Autowired
	private InterestNormalizer interestNormalizer;
	
	@Autowired
	private TweetsMetadataRepository metadataDao;
	
	@Before
	public void setup() {
		participationNormalizer.normalize();
		interestNormalizer.normalize();
	}
	
	@After
	public void tearDown() {
		metadataDao.deleteAllParticipations();
		metadataDao.deleteAllInterests();
	}
	
	private void testMatrix(TransitionProbabilityMatrix matrix, double[][] expected) {
		List<TwitterUser> users = userDao.findAll();
		for (TwitterUser iUser : users) {
			int i = iUser.getId().intValue() - 1;
			for (TwitterUser jUser : users) {
				int j = jUser.getId().intValue() - 1;
				double computed = matrix.getPt(iUser, jUser);
				Assert.assertEquals("Differences between users " + iUser + " and " + jUser,expected[i][j], computed, 1e-8);
			}
		}
	}
	
	@Test
	public void ptMatrixGenerationTopic1Test() {
		double[][] expected = new double[][]{
			{0, 0.041496599,0.581292517,0.16122449,0},
			{0.061616162,0,0,0,0.395959596},
			{0.234752747,0.026923077,0,0,0.311263736},
			{0,0.03803132,0.383668904,0,0.284116331},
			{0,0.574780059,0,0,0}
		};
		TransitionProbabilityMatrix matrix = generator.generate(new TweetClassification(1l));
		testMatrix(matrix, expected);
	}
	
	@Test
	public void ptMatrixGenerationTopic2Test() {
		double[][] expected = new double[][]{
			{0,0.069387755,0.195918367,0.144217687,0},
			{0.103030303,0,0,0,0.609090909},
			{0.079120879,0.193543956,0,0,0.481318681},
			{0,0.072334079,0.207307979,0,0.252050708},
			{0,0.884164223,0,0,0}
		};
		TransitionProbabilityMatrix matrix = generator.generate(new TweetClassification(2l));
		testMatrix(matrix, expected);
	}
	
	@Test
	public void ptMatrixGenerationTopic3Test() {
		double[][] expected = new double[][]{
			{0,0.181632653,0.253401361,0.154421769,0},
			{0.26969697,0,0,0,0.67979798},
			{0.102335165,0.054258242,0,0,0.145604396},
			{0,0.113348248,0.221849366,0,0.313944817},
			{0,0.986803519,0,0,0},
		};
		TransitionProbabilityMatrix matrix = generator.generate(new TweetClassification(3l));
		testMatrix(matrix, expected);
	}
	
	@Test
	public void ptMatrixGenerationTopic4Test() {
		double[][] expected = new double[][]{
			{0,0.20952381,0.523809524,0.171428571,0},
			{0.311111111,0,0,0,0.466666667},
			{0.211538462,0.166208791,0,0,0.460164835},
			{0,0.147651007,0.369127517,0,0.281879195},
			{0,0.677419355,0,0,0}
		};
		TransitionProbabilityMatrix matrix = generator.generate(new TweetClassification(4l));
		testMatrix(matrix, expected);
	}
	
	
}

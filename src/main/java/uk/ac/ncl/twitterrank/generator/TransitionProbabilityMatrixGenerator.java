package uk.ac.ncl.twitterrank.generator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TweetsMetadata;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;
import br.les.opus.twitter.repositories.TwitterUserRepository;
import uk.ac.ncl.twitterrank.TransitionProbabilityMatrix;

@Component
public class TransitionProbabilityMatrixGenerator {
	
	@Autowired
	private TwitterUserRepository userDao;
	
	@Autowired
	private TweetsMetadataRepository metadataDao;
	
	private Double getInterestOnTopic(TwitterUser user, TweetClassification topic) {
		TweetsMetadata metadata = metadataDao.findOne(user, topic);
		if (metadata == null) {
			return 0d;
		}
		Double interest = metadata.getInterest();
		if (interest == null) {
			return 0d;
		}
		return interest;
	}
	
	private Double similarity(TwitterUser user, TwitterUser friend, TweetClassification topic) {
		Double interestUser = this.getInterestOnTopic(user, topic);
		Double interestFriend = this.getInterestOnTopic(friend, topic);
		return 1d - Math.abs(interestUser - interestFriend);
	}
	
	private Double computePtFrom(TwitterUser user, TwitterUser friend, TweetClassification topic) {
		Double tweetsCountOfFriends = metadataDao.countPublishedTweetsFrom(user.getFollowing()).doubleValue();
		if (tweetsCountOfFriends == 0) {
			return 0d;
		}
		
		Double tweetsCount = metadataDao.countPublishedTweetsFrom(friend).doubleValue();
		Double sim = this.similarity(user, friend, topic);
		return tweetsCount / tweetsCountOfFriends * sim;
	}
	
	public TransitionProbabilityMatrix generate(TweetClassification topic) {
		TransitionProbabilityMatrix matrix = new TransitionProbabilityMatrix();

		//iterate over all relevant users computing their transition probability
		List<TwitterUser> users = userDao.findAllRelevant();
		for (TwitterUser user : users) {
			//if the user a follows b, then a is a follower and b is a friend, so we need to compute Pt
			List<TwitterUser> friends = user.getFollowing();
			for (TwitterUser friend : friends) {
				Double pt = this.computePtFrom(user, friend, topic);
				matrix.setPt(user, friend, pt);
			}
		}
		
		return matrix;
	}
}

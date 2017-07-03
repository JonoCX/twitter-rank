package uk.ac.ncl.twitterrank.test.integration.generator;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.la4j.Vector;
import org.springframework.beans.factory.annotation.Autowired;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;
import br.les.opus.twitter.repositories.TwitterUserRepository;
import uk.ac.ncl.twitterrank.generator.TeleportationVectorGenerator;
import uk.ac.ncl.twitterrank.normalizer.InterestNormalizer;
import uk.ac.ncl.twitterrank.normalizer.ParticipationNormalizer;
import uk.ac.ncl.twitterrank.test.util.DbTestUtil;

public class TeleportationVectorGeneratorTest extends DbTestUtil {

	@Autowired
	private InterestNormalizer interestNormalizer;
	
	@Autowired
	private ParticipationNormalizer participationNormalizer;
	
	@Autowired
	private TweetsMetadataRepository metadataDao;
	
	@Autowired
	private TwitterUserRepository userDao;
	
	@Autowired
	private TeleportationVectorGenerator generator;
	
	@Before
	public void setup() {
		interestNormalizer.normalize();
		participationNormalizer.normalize();
	}
	
	@After
	public void tearDown() {
		metadataDao.deleteAllInterests();
		metadataDao.deleteAllParticipations();		
	}
	
	private void assertAllElements(Vector v, double[] array) {
		Assert.assertEquals(array.length, v.length());
		for (int i = 0; i < v.length(); i++) {
			Assert.assertEquals(v.get(i), array[i],0);
		}
	}
	
	@Test
	public void teleportationVectorTopic1Test() {
		TweetClassification topic = new TweetClassification(1l);
		List<TwitterUser> users = userDao.findAllRelevant();
		Vector etVector = generator.generate(users, topic);
		double[] expected = new double[]{3.0/59, 20.0/59, 3.0/59, 3.0/59, 30.0/59};
		this.assertAllElements(etVector, expected);
	}
	
	@Test
	public void teleportationVectorTopic2Test() {
		TweetClassification topic = new TweetClassification(2l);
		List<TwitterUser> users = userDao.findAllRelevant();
		Vector etVector = generator.generate(users, topic);
		double[] expected = new double[]{20.0/43, 1.0/43, 2.0/43, 10.0/43, 10.0/43};
		this.assertAllElements(etVector, expected);
	}
	
	@Test
	public void teleportationVectorTopic3Test() {
		TweetClassification topic = new TweetClassification(3l);
		List<TwitterUser> users = userDao.findAllRelevant();
		Vector etVector = generator.generate(users, topic);
		double[] expected = new double[]{5.0/63, 1.0/63, 50.0/63, 5.0/63, 2.0/63};
		this.assertAllElements(etVector, expected);
	}
	
	@Test
	public void teleportationVectorTopic4Test() {
		TweetClassification topic = new TweetClassification(4l);
		List<TwitterUser> users = userDao.findAllRelevant();
		Vector etVector = generator.generate(users, topic);
		double[] expected = new double[]{0, 0, 10.0/30, 0, 20.0/30};
		this.assertAllElements(etVector, expected);
	}
}



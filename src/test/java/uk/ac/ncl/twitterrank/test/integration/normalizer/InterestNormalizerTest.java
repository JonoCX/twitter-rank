package uk.ac.ncl.twitterrank.test.integration.normalizer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TweetsMetadata;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;
import uk.ac.ncl.twitterrank.normalizer.InterestNormalizer;
import uk.ac.ncl.twitterrank.test.util.DbTestUtil;

public class InterestNormalizerTest extends DbTestUtil {
	
	@Autowired
	private TweetsMetadataRepository metadataDao;
	
	@Autowired
	private InterestNormalizer normalizer;
	
	@Before
	public void setup() {
		normalizer.normalize();
	}
	
	@After
	public void tearDown() {
		metadataDao.deleteAllInterests();
	}
	
	@Test
	public void interestUser1Test() {
		Long userId = 1l;
		TweetsMetadata meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(1l));
		Assert.assertEquals(3.0/28, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(2l));
		Assert.assertEquals(20.0/28, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(3l));
		Assert.assertEquals(5.0/28, meta.getInterest().doubleValue(),0);

		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(4l));
		Assert.assertNull(meta);
	}
	
	@Test
	public void interestUser2Test() {
		Long userId = 2l;
		TweetsMetadata meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(1l));
		Assert.assertEquals(20.0/22, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(2l));
		Assert.assertEquals(1.0/22, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(3l));
		Assert.assertEquals(1.0/22, meta.getInterest().doubleValue(),0);

		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(4l));
		Assert.assertNull(meta);
	}
	
	@Test
	public void interestUser3Test() {
		Long userId = 3l;
		TweetsMetadata meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(1l));
		Assert.assertEquals(3.0/65, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(2l));
		Assert.assertEquals(2.0/65, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(3l));
		Assert.assertEquals(50.0/65, meta.getInterest().doubleValue(),0);

		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(4l));
		Assert.assertEquals(10.0/65, meta.getInterest().doubleValue(),0);
	}
	
	@Test
	public void interestUser4Test() {
		Long userId = 4l;
		TweetsMetadata meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(1l));
		Assert.assertEquals(3.0/18, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(2l));
		Assert.assertEquals(10.0/18, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(3l));
		Assert.assertEquals(5.0/18, meta.getInterest().doubleValue(),0);

		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(4l));
		Assert.assertNull(meta);
	}
	
	@Test
	public void interestUser5Test() {
		Long userId = 5l;
		TweetsMetadata meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(1l));
		Assert.assertEquals(30.0/62, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(2l));
		Assert.assertEquals(10.0/62, meta.getInterest().doubleValue(),0);
		
		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(3l));
		Assert.assertEquals(2.0/62, meta.getInterest().doubleValue(),0);

		meta = metadataDao.findOne(new TwitterUser(userId), new TweetClassification(4l));
		Assert.assertEquals(20.0/62, meta.getInterest().doubleValue(),0);
	}
}

package uk.ac.ncl.twitterrank.test.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;
import uk.ac.ncl.twitterrank.TwitterRank;
import uk.ac.ncl.twitterrank.test.util.DbTestUtil;

public class TwitterRankTest extends DbTestUtil {

	@Autowired
	private TwitterRank rank;
	
	@Autowired
	private TweetsMetadataRepository metaDao;
	
	@Before
	public void setup() {
		rank.computeForAllTopics();
	}
	
	@Test
	public void twitterRankTopic1Test() {
		TweetClassification topic = new TweetClassification(1l);
		Assert.assertEquals(0.04390089180524811, metaDao.findOne(new TwitterUser(1l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.09432759387029391, metaDao.findOne(new TwitterUser(2l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.05091802714955172, metaDao.findOne(new TwitterUser(3l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.05683060181366159, metaDao.findOne(new TwitterUser(4l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.12235616338727906, metaDao.findOne(new TwitterUser(5l), topic).getTwitterRank(), 0);
	}
	
	@Test
	public void twitterRankTopic2Test() {
		TweetClassification topic = new TweetClassification(2l);
		Assert.assertEquals(0.08847130663008286, metaDao.findOne(new TwitterUser(1l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.04795581902049976, metaDao.findOne(new TwitterUser(2l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.04983266772499621, metaDao.findOne(new TwitterUser(3l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.061808405345070275, metaDao.findOne(new TwitterUser(4l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.0709244174682929, metaDao.findOne(new TwitterUser(5l), topic).getTwitterRank(), 0);
	}
	
	@Test
	public void twitterRankTopic3Test() {
		TweetClassification topic = new TweetClassification(3l);
		Assert.assertEquals(0.05101964885823721, metaDao.findOne(new TwitterUser(1l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.03265570880486534, metaDao.findOne(new TwitterUser(2l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.1289709929369542, metaDao.findOne(new TwitterUser(3l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.047951438557560934, metaDao.findOne(new TwitterUser(4l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.03215295787308252, metaDao.findOne(new TwitterUser(5l), topic).getTwitterRank(), 0);
	}
	
	@Test
	public void twitterRankTopic4Test() {
		TweetClassification topic = new TweetClassification(4l);
		Assert.assertEquals(0.13457436600563527, metaDao.findOne(new TwitterUser(3l), topic).getTwitterRank(), 0);
		Assert.assertEquals(0.1468012231622153, metaDao.findOne(new TwitterUser(5l), topic).getTwitterRank(), 0);
	}
}

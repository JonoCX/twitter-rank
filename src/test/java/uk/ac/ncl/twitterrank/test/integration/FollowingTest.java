package uk.ac.ncl.twitterrank.test.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TwitterUserRepository;
import uk.ac.ncl.twitterrank.test.util.DbTestUtil;

public class FollowingTest extends DbTestUtil {
	
	@Autowired
	private TwitterUserRepository userDao;

	private void compare(List<Long> ids, List<TwitterUser> users) {
		Assert.assertEquals(ids.size(), users.size());
		
		List<Long> obtainedIds = new ArrayList<>();
		for (TwitterUser user : users) {
			obtainedIds.add(user.getId());
		}
		
		for (Long id : ids) {
			if (obtainedIds.indexOf(id) == -1){
				Assert.fail(id + " not found");
			}
		}
	}
	
	private void testSocialGraph(TwitterUser user, List<Long> followsIds, List<Long> followedByIds) {
		user = userDao.findOne(user.getId());
		List<TwitterUser> follows = user.getFollowing();
		compare(followsIds, follows);
		
		List<TwitterUser> followedBy = user.getFollowers();
		compare(followedByIds, followedBy);
	}
	
	@Test
	public void user1SocialGraphTest() {
		List<Long> follows = Arrays.asList(2l, 3l, 4l);
		List<Long> followedBy = Arrays.asList(2l, 3l);
		testSocialGraph(new TwitterUser(1l), follows, followedBy);
	}
	
	@Test
	public void user2SocialGraphTest() {
		List<Long> follows = Arrays.asList(1l, 5l);
		List<Long> followedBy = Arrays.asList(1l, 4l, 3l, 5l);
		testSocialGraph(new TwitterUser(2l), follows, followedBy);
	}
	
	@Test
	public void user3SocialGraphTest() {
		List<Long> follows = Arrays.asList(1l, 2l, 5l);
		List<Long> followedBy = Arrays.asList(1l, 4l);
		testSocialGraph(new TwitterUser(3l), follows, followedBy);
	}
	
	@Test
	public void user4SocialGraphTest() {
		List<Long> follows = Arrays.asList(2l, 3l, 5l);
		List<Long> followedBy = Arrays.asList(1l);
		testSocialGraph(new TwitterUser(4l), follows, followedBy);
	}
	
	@Test
	public void user5SocialGraphTest() {
		List<Long> follows = Arrays.asList(2l);
		List<Long> followedBy = Arrays.asList(4l, 2l, 3l);
		testSocialGraph(new TwitterUser(5l), follows, followedBy);
	}
}

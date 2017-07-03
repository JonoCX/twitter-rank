package uk.ac.ncl.twitterrank.persist;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.la4j.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TweetsMetadata;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;

@Component
public class TwitterRankPersistence {
	
	@Autowired
	private TweetsMetadataRepository metadataDao;

	@Transactional(value = TxType.REQUIRES_NEW)
	public void save(Vector tr, List<TwitterUser> users, TweetClassification topic) {
		for (int i = 0; i < users.size(); i++) {
			TwitterUser user = users.get(i);
			Double twitterRankValue = tr.get(i);
			TweetsMetadata metadata = metadataDao.findOne(user, topic);
			if (metadata != null) {
				metadata.setTwitterRank(twitterRankValue);
				metadataDao.save(metadata);
			}
		}
	}
}

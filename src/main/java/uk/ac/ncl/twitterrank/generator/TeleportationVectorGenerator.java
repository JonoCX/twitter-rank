package uk.ac.ncl.twitterrank.generator;

import java.util.List;

import org.la4j.Vector;
import org.la4j.vector.dense.BasicVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.les.opus.twitter.domain.TweetClassification;
import br.les.opus.twitter.domain.TweetsMetadata;
import br.les.opus.twitter.domain.TwitterUser;
import br.les.opus.twitter.repositories.TweetsMetadataRepository;

@Component
public class TeleportationVectorGenerator {
	
	@Autowired
	private TweetsMetadataRepository metadataDao;

	public Vector generate(List<TwitterUser> users, TweetClassification topic) {
		Vector teleportationVector = new BasicVector(users.size());
		for (int i = 0; i < users.size(); i++) {
			TweetsMetadata metadata = metadataDao.findOne(users.get(i), topic);
			if (metadata != null) {
				teleportationVector.set(i, metadata.getParticipation());
			} else {
				teleportationVector.set(i, 0d);
			}
		}
		return teleportationVector;
	}
}

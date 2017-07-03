package uk.ac.ncl.twitterrank.normalizer;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.les.opus.twitter.repositories.TweetsMetadataRepository;

@Component
public class ParticipationNormalizer {

	@Autowired
	private TweetsMetadataRepository metadataDao;
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public void normalize() {
		metadataDao.updateAllParticipations();
	}
}

package uk.ac.ncl.twitterrank;

import java.util.HashMap;
import java.util.Map;

import br.les.opus.twitter.domain.TwitterUser;

public class TransitionProbabilityMatrix {
	
	private Map<TwitterUser, Map<TwitterUser, Double> > probabilities;
	
	public TransitionProbabilityMatrix() {
		this.probabilities = new HashMap<>();
	}
	
	public Double getPt(TwitterUser i, TwitterUser j) {
		Map<TwitterUser, Double> friends = this.probabilities.get(i);
		if (friends == null) {
			return 0d;
		}
		Double pt = friends.get(j);
		if (pt == null) {
			return 0d;
		}
		return pt;
	}
	
	public void setPt(TwitterUser i, TwitterUser j, Double pt) {
		Map<TwitterUser, Double> friends = this.probabilities.get(i);
		if (friends == null) {
			friends = new HashMap<>();
			this.probabilities.put(i, friends);
		}
		friends.put(j, pt);
	}
}

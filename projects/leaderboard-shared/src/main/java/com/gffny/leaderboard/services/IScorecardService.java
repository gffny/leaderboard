/**
 * 
 */
package com.gffny.leaderboard.services;

import java.util.List;

import com.gffny.leaderboard.model.IScorecard;


/**
 * @author John Gaffney (john@gffny.com)
 * Jul 27, 2012
 *
 */
public interface IScorecardService {
	
	public List<IScorecard> getLatestScorecardByUserId(String userId);

}

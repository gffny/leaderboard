/**
 * 
 */
package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com) Jul 27, 2012
 * 
 */
public interface IScorecardService {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<IScorecard> getLatestScorecardByUserId(String userId);

	/**
	 * @param competitionRoundId
	 * @param userId
	 * @param scoreArray
	 * @throws ServiceException
	 */
	public IServiceResult submitScorecardForCompetitionRound(
			String competitionRoundId, String userId, String[] scoreArray)
			throws ServiceException;

}

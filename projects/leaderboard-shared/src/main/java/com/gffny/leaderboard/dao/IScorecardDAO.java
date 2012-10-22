/**
 * 
 */
package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com) Jul 27, 2012
 * 
 */
public interface IScorecardDAO {

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<IScorecard> getScorecardListForUser(String userId)
			throws ServiceException;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<IScorecard> getLatestScorecardForUser(String userId)
			throws ServiceException;

	/**
	 * 
	 * @param userId
	 * @param xNumberOfScorecards
	 * @return
	 * @throws ServiceException
	 */
	public List<IScorecard> getLastXScorecardListForUser(String userId,
			int xNumberOfScorecards) throws ServiceException;

	/**
	 * @param competitionRoundId
	 * @param userId
	 * @param scoreArray
	 */
	public void submitScorecardForCompetitionRound(String competitionRoundId,
			String userId, String[] scoreArray) throws ServiceException;

}
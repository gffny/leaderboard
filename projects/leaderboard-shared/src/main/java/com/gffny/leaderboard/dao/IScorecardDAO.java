/**
 * 
 */
package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
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
			throws DAOException;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<IScorecard> getLatestScorecardForUser(String userId)
			throws DAOException;

	/**
	 * 
	 * @param userId
	 * @param xNumberOfScorecards
	 * @return
	 * @throws ServiceException
	 */
	public List<IScorecard> getLastXScorecardListForUser(String userId,
			int xNumberOfScorecards) throws DAOException;

	/**
	 * 
	 * @param competitionId
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public List<IScorecard> getScorecardListForCompetitionByUserId(
			String competitionId, String userId) throws DAOException;

	/**
	 * @param competitionRoundId
	 * @param userId
	 * @param scoreArray
	 */
	public void submitScorecardForCompetitionRound(String competitionRoundId,
			String userId, String[] scoreArray) throws DAOException;
	/*
	 * SELECT * FROM t_scrcrd s WHERE s.scrr_id = '1' AND s.cmpttn_rnd_id IN (
	 * SELECT r.cmpttn_rnd_id AS blah FROM t_cmpttn c INNER JOIN t_cmpttn_rnd r
	 * ON c.cmpttn_id = r.cmpttn_id );
	 */
}

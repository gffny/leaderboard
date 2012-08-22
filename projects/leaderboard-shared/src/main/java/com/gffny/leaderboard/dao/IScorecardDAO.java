/**
 * 
 */
package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 27, 2012
 *
 */
public interface IScorecardDAO {
	
	public List<IScorecard> getScorecardListForUser(String userId) throws ServiceException;
	
	public List<IScorecard> getLatestScorecardForUser(String userId) throws ServiceException;
	
	public List<IScorecard> getLastXScorecardListForUser(String userId, int xNumberOfScorecards) throws ServiceException;

}
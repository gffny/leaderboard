/**
 * 
 */
package com.gffny.leaderboard.dao;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfBag;

/**
 * @author John Gaffney (john@gffny.com) Apr 13, 2013
 * 
 */
public interface IGolfBagDAO {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public IGolfBag getGolfBagByUserId(int userId) throws DAOException;
}

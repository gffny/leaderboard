/**
 * 
 */
package com.gffny.leaderboard.service;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
public interface IAuthorisationService {

	/**
	 * @param userId
	 * @param enterScorecard
	 * @return
	 */
	boolean isPermitted(String userId, String enterScorecard);

}
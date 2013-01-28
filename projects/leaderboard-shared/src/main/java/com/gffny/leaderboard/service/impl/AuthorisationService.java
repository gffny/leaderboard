/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import com.gffny.leaderboard.service.IAuthorisationService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
public class AuthorisationService implements IAuthorisationService {

	/**
	 * @see com.gffny.leaderboard.service.IAuthorisationService#isPermitted(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean isPermitted(String userId, String enterScorecard) {
		// TODO handle authorisation
		return true;
	}

}

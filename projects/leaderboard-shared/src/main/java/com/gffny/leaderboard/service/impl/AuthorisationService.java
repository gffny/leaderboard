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
	 * 
	 */
	private static AuthorisationService INSTANCE = null;

	/**
	 * 
	 * @return
	 */
	public static AuthorisationService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AuthorisationService();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	private AuthorisationService() {

	}

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

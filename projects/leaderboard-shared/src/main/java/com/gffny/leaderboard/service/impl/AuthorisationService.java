/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gffny.leaderboard.dao.IUserDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.LeaderboardUserDetails;
import com.gffny.leaderboard.service.IAuthorisationService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
public class AuthorisationService implements IAuthorisationService,
		UserDetailsService {

	private static Logger log = Logger.getLogger(AuthorisationService.class);

	@Autowired
	private IUserDAO userDao;

	/**
	 * @see com.gffny.leaderboard.service.IAuthorisationService#isPermitted(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean isPermitted(String userId, String enterScorecard) {
		// TODO handle authorisation
		return true;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			IGolfer user = userDao.getGolferByHandle(username);
			return new LeaderboardUserDetails(user);
		} catch (DAOException e) {
			log.error("User (" + username + ") has not been found");
			throw new UsernameNotFoundException("User (" + username
					+ ") has not been found");
		}
	}
}

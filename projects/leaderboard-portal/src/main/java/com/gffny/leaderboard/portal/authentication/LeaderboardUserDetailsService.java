/**
 * 
 */
package com.gffny.leaderboard.portal.authentication;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.LeaderboardUserDetails;
import com.gffny.leaderboard.portal.manager.IUserManager;

/**
 * @author John Gaffney (john@gffny.com) Aug 7, 2012
 * 
 */
@Component
public class LeaderboardUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserManager userManager;

	/**
	 * 
	 */
	private static Logger log = Logger
			.getLogger(LeaderboardUserDetailsService.class);

	/**
	 * Returns a populated {@link UserDetails} object. The username is first
	 * retrieved from the database and then mapped to a {@link UserDetails}
	 * object.
	 * 
	 * @param String
	 *            username
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		log.info("getting user details for " + username);
		IGolfer user;
		try {
			user = userManager.findUserByHandle(username);
			if (user == null) {
				throw new UsernameNotFoundException("User not found: "
						+ username);
			}
		} catch (ServiceException e) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		LeaderboardUserDetails userDetails = new LeaderboardUserDetails(user);
		return userDetails;
	}
}

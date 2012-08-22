/**
 * 
 */
package com.gffny.leaderboard.portal.web.auth;

import java.util.Collection;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 7, 2012
 *
 */
public class AuthenticationManager implements
		org.springframework.security.authentication.AuthenticationManager {

	/**
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		System.out.println("AuthenticationManager::authenticate()");
		if(authentication.getPrincipal() == null || authentication.getCredentials() == null) {
			throw new BadCredentialsException("Invalid username/password");
		}

        User loggedInUser = null;
        Collection<? extends GrantedAuthority> grantedAuthorities = null;
        
		//loggedInUser = userDao.findByAlias(authentication.getName());
//		if(loggedInUser != null)
//		{
			// Verify password etc.
//			loggedInUser.setOnline(true);
//			userDao.save(loggedInUser);
//		}
//		else
//		{
//			loggedInUser = null;
//			throw new BadCredentialsException("Unknown username");
//		}

		return new UsernamePasswordAuthenticationToken(loggedInUser, authentication.getCredentials(), grantedAuthorities);
	}

}

package com.gffny.leaderboard.portal.authentication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.manager.IUserManager;

/**
 * Register successful login
 * 
 * @author John Gaffney (john@gffny.com) Apr 9, 2013
 * 
 */
@Component
public class LeaderboardAuthenticationSuccessListener implements
		ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private IUserManager userManager;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void onApplicationEvent(AuthenticationSuccessEvent ev) {
		String username = ev.getAuthentication().getName();
		registerSuccessfulLogin(username);
	}

	private void registerSuccessfulLogin(String username) {
		try {
			IGolfer user = userManager.findUserByHandle(username);
			user.resetFailedLoginAttemptsCount();
			user.setLastLogin(new Date());
			userManager.save(user);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
	}
}

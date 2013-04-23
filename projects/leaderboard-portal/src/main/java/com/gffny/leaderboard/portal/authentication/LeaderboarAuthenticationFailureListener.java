package com.gffny.leaderboard.portal.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.manager.IUserManager;

/**
 * Register failed login attempt
 * 
 * @author John Gaffney (john@gffny.com) Apr 9, 2013
 * 
 */
@Component
public class LeaderboarAuthenticationFailureListener implements
		ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private IUserManager userManager;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent ev) {
		String username = ev.getAuthentication().getName();
		registerFailedLoginAttempt(username);
	}

	private void registerFailedLoginAttempt(String username) {
		try {
			IGolfer user = userManager.findUserByHandle(username);
			if (user != null) {
				user.incrementFailedLoginAttemptsCount();

				/*
				 * if (user.getFailedLoginAttemptsCount() >
				 * IConfigConstants.MAX_FAILED_LOGIN_ATTEMPTS &&
				 * !user.getAccountStatus().equals(
				 * ConfigManager.ACCOUNT_STATUS_LOCKED_ID)) { AccountStatus
				 * accountStatus = accountStatusRepository
				 * .findOne(ConfigManager.ACCOUNT_STATUS_LOCKED_ID);
				 * user.setAccountStatus(accountStatus); }
				 */

				userManager.save(user);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

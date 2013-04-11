/**
 * 
 */
package com.gffny.leaderboard.portal.authentication;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 * @author John Gaffney (john@gffny.com) Apr 9, 2013
 * 
 */
public class LeaderboardPermissionEvaluator implements PermissionEvaluator {

	/**
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		// TODO Create actual permission checks
		return true;
	}

	/**
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication,
	 *      java.io.Serializable, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Create actual permission checks
		return true;
	}

}

/**
 * 
 */
package com.gffny.leaderboard.portal.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author John Gaffney (john@gffny.com) Apr 9, 2013
 * 
 */
public class LeaderboardAuthenticationEntryPoint implements
		AuthenticationEntryPoint {

	/**
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		// TODO investigate what this does
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

	}

}

package com.gffny.leaderboard.portal.model.ui.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import com.gffny.leaderboard.portal.model.ui.RequestContext;
import com.gffny.leaderboard.service.impl.UserService;
import com.gffny.leaderboard.util.ApplicationConfiguration;

@Component("unauthenticationFilter")
public class UnauthenticationFilter extends AuthenticationFilter {

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Add Managers to the request context
		RequestContext.get().setUserService(userService);

		filterChain.doFilter(request, response);
	}

	private boolean skipFilter() {
		if (!ApplicationConfiguration.useMockEnvironment()) {
			return false;
		}

		return PatternMatchUtils.simpleMatch(new String[] { "/images/*",
				"/css/*", "/js/*", "/swf/*", "/fonts/*" }, RequestContext.get()
				.getServletData().getContextFreeRequestURI());
	}
}

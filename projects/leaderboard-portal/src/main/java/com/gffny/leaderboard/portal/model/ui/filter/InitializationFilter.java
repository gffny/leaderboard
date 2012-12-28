package com.gffny.leaderboard.portal.model.ui.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gffny.leaderboard.portal.model.ui.RequestContext;
import com.gffny.leaderboard.portal.model.ui.ServletData;
import com.gffny.leaderboard.util.PerfLogger;
import com.gffny.leaderboard.util.PerfLogger.LogType;

@Component("initializationFilter")
public class InitializationFilter extends OncePerRequestFilter {

	public static final long serialVersionUID = -1L;

	private static Logger log = Logger.getLogger(InitializationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		StopWatch monitor = new StopWatch();
		monitor.start();

		try {
			RequestContext.get().setServletData(
					new ServletData(request, response));
			filterChain.doFilter(request, response);
		} finally {
			RequestContext.get().release();
			monitor.stop();
			PerfLogger.log(request.getRequestURI(), LogType.WEB,
					monitor.getTotalTimeMillis());
		}
	}

}

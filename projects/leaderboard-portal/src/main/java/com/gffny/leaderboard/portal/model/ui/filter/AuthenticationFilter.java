package com.gffny.leaderboard.portal.model.ui.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.model.ui.PortalContext;
import com.gffny.leaderboard.portal.model.ui.RequestContext;
import com.gffny.leaderboard.portal.model.ui.ServletData;
import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.leaderboard.service.IUserService;
import com.gffny.leaderboard.service.impl.CompetitionService;
import com.gffny.leaderboard.service.impl.IGolfService;
import com.gffny.leaderboard.util.ApplicationConfiguration;
import com.gffny.leaderboard.util.StringUtils;

@Component("authenticationFilter")
public class AuthenticationFilter extends OncePerRequestFilter {

	private static Logger log = Logger.getLogger(AuthenticationFilter.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IGolfService golfService;

	@Autowired
	private IAuthorisationService authorisationService;

	@Autowired
	private CompetitionService competitionService;

	protected String defaultId = ApplicationConfiguration.getString(
			"default.org.id", "DEFAULT");

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.debug("doFilterInternal");

		loadRequestContext();

		ServletData servletData = RequestContext.get().getServletData();

		IGolfer user = initializeUser(servletData);
		/*
		 * if (user == null) { // initializeOrganizationByUrl();
		 * 
		 * // unsuccessful ajax request if (servletData.isAjaxRequest()) { //
		 * servletData.redirectRequest( //
		 * "/authentication/logout?ajaxSession=1", true); return; }
		 * 
		 * // servletData.redirectRequest("/authentication/logout", true);
		 * return; }
		 */
		/*
		 * OrganizationUnit organizationUnit = initializeOrganization(user);
		 * RequestContext.get().setOrganizationUnit(organizationUnit); if
		 * (organizationUnit.isDomainInvalid()) { initializeOrganizationByUrl();
		 * servletData.redirectRequest("/logout?domain=1", true); return; }
		 */
		/*
		 * RequestContext.get().setUser(user); if
		 * (!servletData.isReturningContext()) { if
		 * (!userService.isGolferActive(user.getUserId())) {
		 * logger.info("User is inactive: " + user);
		 * servletData.redirectRequest( "/authentication/logout?inactive=1",
		 * true); return; } }
		 */
		servletData.setRegistered(true);

		/*
		 * if (ApplicationConfiguration.isI18nEnabled() &&
		 * RequestContext.get().getOrganizationUnit()
		 * .getPortalConfiguration().isSpanishEnabled() &&
		 * user.isLanguagePreferenceSetup()) {
		 * servletData.storeLocaleCookie(user.getLanguagePreference()); }
		 */

		filterChain.doFilter(request, response);
	}

	protected IGolfer initializeUser(ServletData servletData) {

		printDebugInformation(servletData);
		String username = servletData.getUsername();

		if (StringUtils.isEmpty(username)) {
			return null;
		}

		PortalContext context = servletData.getPortalContextFromCookie();
		if (context != null && context.isValid(username)) {
			IGolfer user = userService.getGolferById(context.getUid());
			if (user != null) {
				servletData.setReturningContext();
				return user;
			}
		}

		String golferId = servletData.getGolferId();
		if (StringUtils.isEmpty(golferId) || !StringUtils.isNumeric(golferId)) {
			return null;
		}

		context = new PortalContext();

		IGolfer user = userService.getGolferById(golferId);
		if (user == null) {
			// This indicates that the user needs to be registered, so we get
			// them from the service
			// user = individualDataDao.getIndividual(golferId);
			// user.setFirstTimeUser(true);
			// userDao.save(user);
		}
		context.setUsername(username);
		context.setUid(String.valueOf(user.getUserId()));
		servletData.storePortalContextCookie(context);

		logger.info("setting new login attribute: " + user.toString());
		servletData.setAttribute("new-login", true);

		return user;
	}

	protected void loadRequestContext() {
		// Add services to the request context
		RequestContext.get().setUserService(userService);
		RequestContext.get().setCompetitionService(competitionService);
		RequestContext.get().setGolfCourseService(golfService);
		RequestContext.get().setScorecardService(golfService);
		RequestContext.get().setAuthorisationSerivce(authorisationService);
	}

	protected void printDebugInformation(ServletData servletData) {
		if (ApplicationConfiguration.getBoolean(
				"print.authentication.messages", false)) {

			logger.info("username: " + servletData.getUsername());
			logger.info("individualId: " + servletData.getGolferId());

			Principal principal = servletData.getRequest().getUserPrincipal();
			logger.info("principal: " + principal);
			if (principal != null) {
				logger.info("principal name: " + principal.getName());
			}
			logger.info("remote user: "
					+ servletData.getRequest().getRemoteUser());

			String mobileIndividualId = servletData.getRequest().getHeader(
					"USER_CN");
			logger.info("mobileIndividualId: " + mobileIndividualId);
			try {
				@SuppressWarnings("unchecked")
				List<String> headers = Collections
						.list((Enumeration<String>) servletData.getRequest()
								.getHeaderNames());
				logger.info(headers);
				for (String h : headers) {
					logger.info("Header - " + h + ", header value - "
							+ servletData.getRequest().getHeader(h));
				}
			} catch (Throwable ex) {
				logger.error("Error - could not fetch headers.", ex);
			}
		}
	}
}

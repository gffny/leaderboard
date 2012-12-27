/**
 * 
 */
package com.gffny.leaderboard.portal.model.ui;

import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.cache.Cache;
import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class RequestContext {

	/**
	 * 
	 */
	private static ThreadLocal<RequestContext> currentInstance = new ThreadLocal<RequestContext>() {

		/**
		 * 
		 */
		@Override
		protected RequestContext initialValue() {
			return new RequestContext();
		}
	};

	/**
	 * 
	 */
	private IGolfer user;

	/**
	 * 
	 */
	private ServletData servletData;

	/**
	 * 
	 */
	private IUserService userService;

	/**
	 * 
	 */
	private ICompetitionService competitionService;

	/**
	 * 
	 */
	private IGolfCourseService golfCourseService;

	/**
	 * 
	 */
	private IScorecardService scorecardService;

	/**
	 * 
	 */
	private IAuthorisationService authorisationSerivce;

	/**
	 * 
	 */
	private Cache cache;

	/**
	 * 
	 * @return
	 */
	public static RequestContext get() {
		return currentInstance.get();
	}

	/**
	 * 
	 * @return
	 */
	public IGolfer getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(IGolfer user) {
		this.user = user;
	}

	/**
	 * 
	 * @return
	 */
	public ServletData getServletData() {
		return servletData;
	}

	/**
	 * 
	 * @param servletData
	 */
	public void setServletData(ServletData servletData) {
		this.servletData = servletData;
	}

	/*
	 * public Locale userLocaleFromDb() { try { return
	 * getUser().getLanguagePreference(); } catch (Throwable ex) { return null;
	 * }
	 * 
	 * }
	 */
	/**
	 * @return the competitionService
	 */
	public ICompetitionService getCompetitionService() {
		return competitionService;
	}

	/**
	 * @param competitionService
	 *            the competitionService to set
	 */
	public void setCompetitionService(ICompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	/**
	 * @return the golfCourseService
	 */
	public IGolfCourseService getGolfCourseService() {
		return golfCourseService;
	}

	/**
	 * @param golfCourseService
	 *            the golfCourseService to set
	 */
	public void setGolfCourseService(IGolfCourseService golfCourseService) {
		this.golfCourseService = golfCourseService;
	}

	/**
	 * @return the scorecardService
	 */
	public IScorecardService getScorecardService() {
		return scorecardService;
	}

	/**
	 * @param scorecardService
	 *            the scorecardService to set
	 */
	public void setScorecardService(IScorecardService scorecardService) {
		this.scorecardService = scorecardService;
	}

	/**
	 * @return the authorisationSerivce
	 */
	public IAuthorisationService getAuthorisationSerivce() {
		return authorisationSerivce;
	}

	/**
	 * @param authorisationSerivce
	 *            the authorisationSerivce to set
	 */
	public void setAuthorisationSerivce(
			IAuthorisationService authorisationSerivce) {
		this.authorisationSerivce = authorisationSerivce;
	}

	/**
	 * 
	 * @return
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @param cache
	 *            the cache to set
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * 
	 * @return
	 */
	public Cache getCache() {
		if (cache == null) {
			cache = new Cache();
		}

		return cache;
	}

	/**
	 * 
	 */
	public void release() {
		user = null;
		servletData = null;
		userService = null;
		cache = null;
	}
}

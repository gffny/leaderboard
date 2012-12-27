/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;
import com.gffny.leaderboard.service.IServiceFactory;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public class ServiceFactory implements IServiceFactory {

	/**
	 * 
	 */
	private static IServiceFactory INSTANCE = null;

	/**
	 * 
	 * @return
	 */
	public static IServiceFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ServiceFactory();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	private ServiceFactory() {

	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getCompetitionService()
	 */
	@Override
	public ICompetitionService getCompetitionService() {
		return CompetitionService.getInstance();
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getGolfCourseService()
	 */
	@Override
	public IGolfCourseService getGolfCourseService() {
		return GolfService.getInstance();
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getScorecardService()
	 */
	@Override
	public IScorecardService getScorecardService() {
		return GolfService.getInstance();
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getUserService()
	 */
	@Override
	public IUserService getUserService() {
		return UserService.getInstance();
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getAuthorisationService()
	 */
	@Override
	public IAuthorisationService getAuthorisationService() {
		return AuthorisationService.getInstance();
	}
}

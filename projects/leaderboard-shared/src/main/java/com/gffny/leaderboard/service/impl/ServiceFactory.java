/**
 * 
 */
package com.gffny.leaderboard.service.impl;

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
	 * @see com.gffny.leaderboard.service.IServiceFactory#getCompetitionService()
	 */
	@Override
	public ICompetitionService getCompetitionService() {

		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getGolfCourseService()
	 */
	@Override
	public IGolfCourseService getGolfCourseService() {
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getScorecardService()
	 */
	@Override
	public IScorecardService getScorecardService() {
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getUserService()
	 */
	@Override
	public IUserService getUserService() {

		return null;
	}
}

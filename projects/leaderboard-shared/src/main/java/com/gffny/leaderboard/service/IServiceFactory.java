/**
 * 
 */
package com.gffny.leaderboard.service;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public interface IServiceFactory {

	/**
	 * 
	 * @return
	 */
	public ICompetitionService getCompetitionService();

	/**
	 * 
	 * @return
	 */
	public IGolfCourseService getGolfCourseService();

	/**
	 * 
	 * @return
	 */
	public IScorecardService getScorecardService();

	/**
	 * 
	 * @return
	 */
	public IUserService getUserService();

}

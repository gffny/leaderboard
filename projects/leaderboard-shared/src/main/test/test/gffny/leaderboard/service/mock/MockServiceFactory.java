/**
 * 
 */
package test.gffny.leaderboard.service.mock;

import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;
import com.gffny.leaderboard.service.IServiceFactory;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public class MockServiceFactory implements IServiceFactory {

	private static MockServiceFactory INSTANCE = null;

	/**
	 * 
	 */
	private MockServiceFactory() {

	}

	/**
	 * 
	 * @return
	 */
	public static MockServiceFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockServiceFactory();
		}
		return INSTANCE;
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getCompetitionService()
	 */
	@Override
	public ICompetitionService getCompetitionService() {
		return MockCompetitionService.getInstance();
	}

	/**
	 * @see com.gffny.leaderboard.service.IServiceFactory#getGolfCourseService()
	 */
	@Override
	public IGolfCourseService getGolfCourseService() {
		return MockGolfService.getInstance();
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
		return MockUserService.getInstance();
	}

}

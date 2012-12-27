/**
 * 
 */
package test.gffny.leaderboard.service.mock;

import com.gffny.leaderboard.service.IAuthorisationService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
public class MockAuthorisationService implements IAuthorisationService {

	/**
	 * 
	 */
	private static MockAuthorisationService INSTANCE = null;

	/**
	 * 
	 */
	private MockAuthorisationService() {

	}

	/**
	 * 
	 * @return
	 */
	public static MockAuthorisationService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockAuthorisationService();
		}
		return INSTANCE;
	}

	/**
	 * @see com.gffny.leaderboard.service.IAuthorisationService#isPermitted(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean isPermitted(String userId, String enterScorecard) {
		return true;
	}

}

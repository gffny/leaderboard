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
	 * @see com.gffny.leaderboard.service.IAuthorisationService#isPermitted(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean isPermitted(String userId, String enterScorecard) {
		return true;
	}

}

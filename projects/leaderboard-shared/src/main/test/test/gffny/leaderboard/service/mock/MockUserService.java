/**
 * 
 */
package test.gffny.leaderboard.service.mock;

import java.util.List;

import test.utilities.TestUtilities;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author John Gaffney (john@gffny.com) Oct 2, 2012
 * 
 */
public class MockUserService implements IUserService {

	private static MockUserService INSTANCE = null;

	/**
	 * 
	 */
	private MockUserService() {
	}

	public static MockUserService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockUserService();
		}
		return INSTANCE;
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getAllSocietyMembers(java.lang.String)
	 */
	@Override
	public List<IGolfer> getAllSocietyMembers(String societyId)
			throws ServiceException {
		return TestUtilities.getTestGolferList();
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferBySocietyMemberId(java.lang.String)
	 */
	@Override
	public IGolfer getGolferBySocietyMemberId(String societyMemberId)
			throws ServiceException {
		return TestUtilities.getTestGolferByID(societyMemberId);
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getSocietyMemberListAssociatedWithUser(java.lang.String)
	 */
	@Override
	public List<IGolfer> getSocietyMemberListAssociatedWithUser(
			String societyMemberId) throws ServiceException {
		System.out
				.println("mock UserService: getSocietyMemberListAssociatedWithUser("
						+ societyMemberId + ")");
		return TestUtilities.getTestGolferList();
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#isIndividualActive(int)
	 */
	public boolean isGolferActive(int userId) {
		return true;
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferById(java.lang.String)
	 */
	@Override
	public IGolfer getGolferById(String golferId) {
		return TestUtilities.getTestGolferByID(golferId);
	}

}

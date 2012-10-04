package test.gffny.leaderboard.service.mock;

import java.util.Arrays;
import java.util.List;

import test.utilities.TestUtilities;

import com.gffny.leaderboard.model.ICountry;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.service.IGolfCourseService;

public class MockGolfService implements IGolfCourseService {
	//
	private static MockGolfService INSTANCE = null;

	/**
	 * 
	 */
	private MockGolfService() {
	}

	/**
	 * 
	 * @return
	 */
	public static MockGolfService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockGolfService();
		}
		return INSTANCE;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getAllGolfCoursesList()
	 */
	@Override
	public List<IGolfCourse> getAllGolfCoursesList() {
		return Arrays.asList(TestUtilities.getTestGolfCourse(18, 72));
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseById(java.lang.String)
	 */
	@Override
	public List<IGolfCourse> getGolfCourseById(String courseId) {
		return Arrays.asList(TestUtilities.getTestGolfCourse(18, 72));
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getSupportedCountryList()
	 */
	@Override
	public List<ICountry> getSupportedCountryList() {
		return TestUtilities.getTestCountryList();
	}
}

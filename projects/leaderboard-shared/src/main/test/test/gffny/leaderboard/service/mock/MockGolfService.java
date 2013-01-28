package test.gffny.leaderboard.service.mock;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import test.utilities.TestUtilities;

import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICountry;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IScorecard;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;

public class MockGolfService implements IGolfCourseService, IScorecardService {

	private static Logger log = Logger.getLogger(MockGolfService.class);

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
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseShortListByUserId(java.lang.String)
	 */
	@Override
	public java.util.List<IGolfCourse> getGolfCourseShortListByUserId(
			String userId) {
		return Arrays.asList(TestUtilities.getTestGolfCourse(18, 72));
	};

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

	/**
	 * @see com.gffny.leaderboard.service.IScorecardService#getLatestScorecardByUserId(java.lang.String)
	 */
	@Override
	public List<IScorecard> getLatestScorecardByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IScorecardService#submitScorecardForCompetitionRound(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	@Override
	public IServiceResult submitScorecardForCompetitionRound(
			String competitionRoundId, String golferId, String[] scoreArray)
			throws ServiceException {
		log.error("competitionRoundId " + competitionRoundId + " / golferId "
				+ golferId + " / scoreArray " + ArrayUtils.toString(scoreArray));
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IScorecardService#submitScorecardMapForCompetitionRound(java.lang.String,
	 *      java.util.Map)
	 */
	@Override
	public void submitScorecardMapForCompetitionRound(
			String competitionRoundId, Map<String, String[]> scoreMap)
			throws ServiceException {
		// check if the parameters are not null
		if (competitionRoundId != null && scoreMap != null
				&& !scoreMap.isEmpty()) {
			Iterator<Entry<String, String[]>> iterator = scoreMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String[]> pair = iterator.next();
				submitScorecardForCompetitionRound(competitionRoundId,
						pair.getKey(), pair.getValue());
			}
		}
	}

	/**
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseByCity(java.lang.String)
	 */
	@Override
	public List<IGolfCourse> getGolfCourseByCity(String city)
			throws ServiceException {
		return Arrays.asList(TestUtilities.getTestGolfCourse(18, 72));
	}
}

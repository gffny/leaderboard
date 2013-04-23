package com.gffny.leaderboard.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICountry;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IScorecard;
import com.gffny.leaderboard.service.AbstractService;
import com.gffny.leaderboard.service.IGolfService;
import com.gffny.leaderboard.service.IUserService;

public class GolfService extends AbstractService implements IGolfService {

	/**
	 * 
	 */
	public static Logger log = Logger.getLogger(GolfService.class);

	/**
	 * 
	 */
	@Autowired
	private IScorecardDAO scorecardDao;

	/**
	 * 
	 */
	@Autowired
	private IGolfCourseDAO courseDao;

	/**
	 * 
	 */
	@Autowired
	private IUserService userService;

	/**
	 * 
	 */
	public List<IScorecard> getLatestScorecardByUserId(String userId) {
		try {
			return scorecardDao.getLatestScorecardForUser(userId);
		} catch (DAOException e) {
			// TODO
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<IScorecard> getAllScorecardByUserId(String userId) {
		try {
			return scorecardDao.getScorecardListForUser(userId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param userId
	 * @param xNumberOfScorecards
	 * @return
	 */
	public List<IScorecard> getLatestXScorecardListByUserId(String userId,
			int xNumberOfScorecards) {
		try {
			return scorecardDao.getLastXScorecardListForUser(userId,
					xNumberOfScorecards);
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getSupportedCountryList()
	 */
	@Override
	public List<ICountry> getSupportedCountryList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IScorecardService#submitScorecardForCompetitionRound(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	@Override
	public IServiceResult submitScorecardForCompetitionRound(
			String competitionRoundId, String userId, String[] scoreArray)
			throws ServiceException {
		// Check if the parameters are not null
		if (competitionRoundId != null && userId != null && scoreArray != null
				&& (scoreArray.length > 0 && scoreArray.length < 18)) {
			try {
				scorecardDao.submitScorecardForCompetitionRound(
						competitionRoundId, userId, scoreArray);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}
		System.out.println(competitionRoundId + " " + userId + " "
				+ scoreArray.toString());
		return null;
	}

	/**
	 * @throws ServiceException
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

	// ################### GOLF COURSE SERVICES #############################
	/**
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseById(java.lang.String)
	 */
	@Override
	public List<IGolfCourse> getGolfCourseById(String courseId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return new ArrayList<IGolfCourse>();
	}

	/**
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseByCity(java.lang.String)
	 */
	@Override
	public List<IGolfCourse> getGolfCourseByCity(String city)
			throws ServiceException {
		// TODO Auto-generated method stub
		return new ArrayList<IGolfCourse>();
	}

	/**
	 * 
	 */
	public List<IGolfCourse> getAllGolfCoursesList() {
		try {
			return courseDao.getAllGolfCourseList();
		} catch (DAOException daoEx) {
			return logErrorReturnEmptyList(daoEx, log, IGolfCourse.class);
		}
	}

	/**
	 * 
	 * @param courseId
	 */
	public List<IGolfCourse> getGolfCourseByIdAndTeeColour(String courseId,
			String teeColour) {
		try {
			return courseDao.getCourseByIdAndTeeColour(courseId, teeColour);
		} catch (DAOException daoEx) {
			return logErrorReturnEmptyList(daoEx, log, IGolfCourse.class);
		}
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseShortListByUserId(java.lang.String)
	 */
	@Override
	public List<IGolfCourse> getGolfCourseShortListByUserId(String userId) {
		try {
			return userService.getGolferFavouriteClub(userId);
		} catch (ServiceException serEx) {
			return logErrorReturnEmptyList(serEx, log, IGolfCourse.class);
		}
	};
}

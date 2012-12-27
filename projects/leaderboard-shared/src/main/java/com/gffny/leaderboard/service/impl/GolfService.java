package com.gffny.leaderboard.service.impl;

import java.util.List;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.dao.factory.DAOFactory;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICountry;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IScorecard;

public class GolfService implements IGolfService {

	private IScorecardDAO scorecardDao;
	private IGolfCourseDAO courseDao;
	private static GolfService INSTANCE = null;

	/**
	 * 
	 */
	private GolfService() {
		scorecardDao = DAOFactory.getInstance().getScorecardDAO();
		courseDao = DAOFactory.getInstance().getGolfCourseDAO();
	}

	/**
	 * 
	 * @return
	 */
	public static GolfService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GolfService();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	public List<IGolfCourse> getAllGolfCoursesList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param courseId
	 */
	public List<IGolfCourse> getGolfCourseById(String courseId) {
		try {
			return courseDao.getCourseById(courseId);
		} catch (DAOException e) {
			// TODO
		}
		return null;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.service.IGolfCourseService#getGolfCourseShortListByUserId(java.lang.String)
	 */
	@Override
	public java.util.List<IGolfCourse> getGolfCourseShortListByUserId(
			String userId) {
		return null; // TODO implement meaningfully
	};

	/**
	 * 
	 */
	public List<IScorecard> getLatestScorecardByUserId(String userId) {
		try {
			return scorecardDao.getLatestScorecardForUser(userId);
		} catch (ServiceException e) {
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
		} catch (ServiceException e) {
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
		} catch (ServiceException e) {

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
			scorecardDao.submitScorecardForCompetitionRound(competitionRoundId,
					userId, scoreArray);
		}
		System.out.println(competitionRoundId + " " + userId + " "
				+ scoreArray.toString());
		return null;
	}
}

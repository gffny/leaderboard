package com.gffny.leaderboard.services.impl;

import java.util.List;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.dao.factory.DAOFactory;
import com.gffny.leaderboard.layerUtils.ServiceException;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IScorecard;
import com.gffny.leaderboard.services.IGolfCourseService;
import com.gffny.leaderboard.services.IScorecardService;


public class GolfService implements IScorecardService, IGolfCourseService {

	IScorecardDAO scorecardDao = DAOFactory.getInstance().getScorecardDAO();
	IGolfCourseDAO courseDao = DAOFactory.getInstance().getGolfCourseDAO();

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
		
		return null;
	}

	/**
	 * 
	 */
	public List<IScorecard> getLatestScorecardByUserId(String userId) {
		try {
			return scorecardDao.getLatestScorecardForUser(userId);
		} catch (ServiceException e) {
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
	public List<IScorecard> getLatestXScorecardListByUserId(String userId, int xNumberOfScorecards) {
		try {
			return scorecardDao.getLastXScorecardListForUser(userId, xNumberOfScorecards);
		} catch (ServiceException e) {

			e.printStackTrace();
		}
		return null;
	}
}

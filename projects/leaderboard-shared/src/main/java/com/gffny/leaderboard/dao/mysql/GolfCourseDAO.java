/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.util.List;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 31, 2012
 *
 */
public class GolfCourseDAO implements IGolfCourseDAO {

	/**
	 * 
	 * @param golfCourseId
	 */
	public List<IGolfCourse> getGolfCourseById(String golfCourseId)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param golfCourseName
	 */
	public List<IGolfCourse> getGolfCourseByName(String golfCourseName)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public List<IGolfCourse> getCourseList() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param countryCode
	 */
	public List<IGolfCourse> getCourseListByCountry(String countryCode)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param country
	 * @param stateCode
	 */
	public List<IGolfCourse> getCourseListByCountryAndState(String country,
			String stateCode) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}

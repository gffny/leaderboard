package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 23, 2013
 * 
 */
public interface IGolfCourseDAO {

	/**
	 * 
	 */
	public static String USA_COUNTRY_CODE = "USA";

	/**
	 * 
	 */
	public static String MA_USA_STATE_CODE = "MA";

	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws DAOException
	 */
	public abstract List<String> getTeeColourListByCourseId(String courseId)
			throws DAOException;

	/**
	 * 
	 * @param clubName
	 * @param courseName
	 * @return
	 * @throws DAOException
	 */
	public abstract List<String> getTeeColourListByClubNameAndCourseName(
			String clubName, String courseName) throws DAOException;

	/**
	 * 
	 * @param clubName
	 * @return
	 * @throws DAOException
	 */
	public abstract List<String> getCourseNameListByClubName(String clubName)
			throws DAOException;

	/**
	 * 
	 * @param courseId
	 * @param teeColour
	 * @return
	 * @throws DAOException
	 */
	public abstract List<IGolfCourse> getCourseByIdAndTeeColour(
			String courseId, String teeColour) throws DAOException;

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public abstract List<IGolfCourse> getAllGolfCourseList()
			throws DAOException;
}

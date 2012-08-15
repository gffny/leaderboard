package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;

public interface IGolfCourseDAO {
	
	public static String USA_COUNTRY_CODE = "USA";
	public static String MA_USA_STATE_CODE = "MA";

	public List<IGolfCourse> getCourseById(String courseId) throws DAOException;

	public List<String> getCourseNameListByClub(String clubName) throws DAOException;

	public List<IGolfCourse> getCourseByClubAndCourseName(String clubName, String courseName) throws DAOException;

	public List<String> getGolfClubNameListByCountryAndState(String country, String state) throws DAOException;

}

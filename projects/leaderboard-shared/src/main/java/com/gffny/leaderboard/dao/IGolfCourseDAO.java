package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;

public interface IGolfCourseDAO {
	
	public static String USA_COUNTRY_CODE = "USA";
	public static String MA_USA_STATE_CODE = "MA";
	
	public List<IGolfCourse> getGolfCourseById(String golfCourseId) throws DAOException;
	
	public List<IGolfCourse> getGolfCourseByName(String golfCourseName) throws DAOException;

	public List<IGolfCourse> getCourseList() throws DAOException;
	
	public List<IGolfCourse> getCourseListByCountry(String countryCode) throws DAOException;
	
	public List<IGolfCourse> getCourseListByCountryAndState(String country, String stateCode) throws DAOException;
	
}

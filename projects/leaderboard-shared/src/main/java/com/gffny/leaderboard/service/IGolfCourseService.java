package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICountry;
import com.gffny.leaderboard.model.IGolfCourse;

public interface IGolfCourseService {

	/**
	 * returns a list of country objects that have courses
	 * 
	 * @return
	 */
	public List<IGolfCourse> getAllGolfCoursesList() throws ServiceException;

	/**
	 * returns a list of country objects that have courses
	 * 
	 * @return
	 */
	public List<IGolfCourse> getGolfCourseById(String courseId)
			throws ServiceException;

	/**
	 * returns a list of country objects that have courses
	 * 
	 * @return
	 */
	public List<IGolfCourse> getGolfCourseShortListByUserId(String userId)
			throws ServiceException;

	/**
	 * returns a list of country objects that have courses
	 * 
	 * @return
	 */
	public List<ICountry> getSupportedCountryList() throws ServiceException;

	/**
	 * @param city
	 * @return
	 */
	public List<IGolfCourse> getGolfCourseByCity(String city)
			throws ServiceException;

}

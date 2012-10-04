package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICountry;
import com.gffny.leaderboard.model.IGolfCourse;

public interface IGolfCourseService {

	public List<IGolfCourse> getAllGolfCoursesList();

	public List<IGolfCourse> getGolfCourseById(String courseId)
			throws ServiceException;

	/**
	 * returns a list of country objects that have courses
	 * 
	 * @return
	 */
	public List<ICountry> getSupportedCountryList() throws ServiceException;

}

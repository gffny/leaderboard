package com.gffny.leaderboard.services;

import java.util.List;

import com.gffny.leaderboard.model.IGolfCourse;

public interface IGolfCourseService {

	public List<IGolfCourse> getAllGolfCoursesList();
	
	public List<IGolfCourse> getGolfCourseById(String courseId);

}

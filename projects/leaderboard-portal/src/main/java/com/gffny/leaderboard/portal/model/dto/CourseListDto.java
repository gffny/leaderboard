/**
 * 
 */
package com.gffny.leaderboard.portal.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.model.IGolfCourse;

/**
 * @author John Gaffney (john@gffny.com) May 1, 2013
 * 
 */
public class CourseListDto extends BaseDto {

	private List<IGolfCourse> golfCourseList = null;

	/**
	 * 
	 * @param golfCourseList
	 */
	public CourseListDto(List<IGolfCourse> golfCourseList) {
		if (golfCourseList != null) {
			setGolfCourseList(golfCourseList);
		} else {
			this.golfCourseList = new ArrayList<IGolfCourse>();
		}
	}

	/**
	 * @return the golfCourseList
	 */
	public List<IGolfCourse> getGolfCourseList() {
		return golfCourseList;
	}

	/**
	 * @param golfCourseList
	 *            the golfCourseList to set
	 */
	public void setGolfCourseList(List<IGolfCourse> golfCourseList) {
		this.golfCourseList = golfCourseList;
	}
}
/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.List;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public interface IGolfCourse extends IEntity {

	/**
	 * 
	 * @return
	 */
	public int getCourseId();

	/**
	 * 
	 * @return
	 */
	public int getPar();

	/**
	 * 
	 * @return
	 */
	public String getTeeColour();

	/**
	 * 
	 * @return
	 */
	public int[] getHoleParArray();

	/**
	 * 
	 * @param holeNumber
	 * @return
	 */
	public int getHolePar(int holeNumber);

	/**
	 * 
	 * @return
	 */
	public int[] getHoleIndexArray();

	/**
	 * 
	 * @param holeNumber
	 * @return
	 */
	public int getHoleIndex(int holeNumber);

	/**
	 * 
	 * @param holeNumber
	 * @return
	 */
	public IGolfCourseHole getHole(int holeNumber);

	/**
	 * 
	 * @return
	 */
	public List<IGolfCourseHole> getHoleList();

	/**
	 * 
	 * @return
	 */
	public int[] getTeeDistanceArray();

	/**
	 * 
	 * @return
	 */
	public String toString();
}

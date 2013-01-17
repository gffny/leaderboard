/**
 * 
 */
package com.gffny.leaderboard.model;

/**
 * @author John Gaffney (john@gffny.com) Jul 31, 2012
 * 
 */
public interface IGolfCourseHole extends IEntity {

	/**
	 * 
	 * @return
	 */
	public int getPar();

	/**
	 * 
	 * @return
	 */
	public int getIndex();

	/**
	 * 
	 * @return
	 */
	public int getTeeDistance();
}

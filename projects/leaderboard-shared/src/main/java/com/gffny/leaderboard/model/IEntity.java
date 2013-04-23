/**
 * 
 */
package com.gffny.leaderboard.model;

/**
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public interface IEntity extends JSONable {

	/**
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * 
	 * @return
	 */
	public Object getId();

	/**
	 * 
	 * @param value
	 */
	public void setNew(boolean value);

	/**
	 * 
	 * @return
	 */
	public boolean isNew();
}
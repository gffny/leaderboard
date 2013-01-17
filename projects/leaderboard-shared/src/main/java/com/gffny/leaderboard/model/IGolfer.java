/**
 * 
 */
package com.gffny.leaderboard.model;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public interface IGolfer extends IEntity {

	/**
	 * 
	 * @return
	 */
	public String getProfileHandle();

	/**
	 * 
	 * @param profileHandle
	 */
	public void setProfileHandle(String profileHandle);

	/**
	 * 
	 * @return
	 */
	public String getEmailAddress();

	/**
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * 
	 * @return
	 */
	public String getFirstName();

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName);

	/**
	 * 
	 * @return
	 */
	public String getLastName();

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName);

	/**
	 * 
	 * @return
	 */
	public String getHandicap();

	/**
	 * 
	 * @param handicap
	 */
	public void setHandicap(String handicap);

	/**
	 * 
	 * @return
	 */
	public String getLocation();

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location);

	/**
	 * 
	 * @return
	 */
	public int getUserId();
}

/**
 * 
 */
package com.gffny.leaderboard.model;

/**
 * @author John Gaffney (john@gffny.com) Apr 12, 2013
 * 
 */
public interface IBasicGolfer extends IEntity {

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
	public Object getHandicap();

	/**
	 * 
	 * @param handicap
	 */
	public void setHandicap(int handicap);

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

	/**
	 * 
	 * @return
	 */
	public Object getSocietyId();

	/**
	 * 
	 * @param societyId
	 */
	public void setSocietyId(int societyId);

}

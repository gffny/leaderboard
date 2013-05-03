/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.Date;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public interface IGolfer extends IBasicGolfer {

	/**
	 * 
	 * @return
	 */
	public abstract String getPassword();

	/**
	 * @param object
	 */
	public abstract void setPassword(String password);

	/**
	 * 
	 * @return
	 */
	public String getLastLogin();

	/**
	 * 
	 * @param lastLogin
	 */
	public void setLastLogin(Date lastLogin);

	/**
	 * 
	 * @param lastLogin
	 */
	public void setLastLogin(String lastLogin);

	/**
	 * 
	 * @return
	 */
	public int getFailedLoginAttemptsCount();

	/**
	 * 
	 */
	public void incrementFailedLoginAttemptsCount();

	/**
	 * 
	 */
	public void resetFailedLoginAttemptsCount();

	/**
	 * 
	 * @return
	 */
	public IGolfBag getGolfBag();

	/**
	 * @param golfBagByUserId
	 */
	public void setGolfBag(IGolfBag golfBag);
}

/**
 * 
 */
package com.gffny.leaderboard.portal.model;

import java.util.HashMap;

import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com) Jul 26, 2012
 * 
 */
public class GolferMap extends HashMap<String, Object> implements IGolfer {

	private static final String HANDICAP = "handicap";
	private static final String LOCATION = "location";
	private static final String USER_ID = "userId";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String EMAIL_ADDRESS = "emailAddress";
	private static final String PROFILE_HANDLE = "profileHandle";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public String getProfileHandle() {
		return (String) this.get(PROFILE_HANDLE);
	}

	/**
	 * 
	 */
	public void setProfileHandle(String profileHandle) {
		this.put(PROFILE_HANDLE, profileHandle);
	}

	/**
	 * 
	 */
	public String getEmailAddress() {
		return (String) this.get(EMAIL_ADDRESS);
	}

	/**
	 * 
	 */
	public void setEmailAddress(String emailAddress) {
		this.put(EMAIL_ADDRESS, emailAddress);
	}

	/**
	 * 
	 */
	public String getFirstName() {
		return (String) this.get(FIRST_NAME);
	}

	/**
	 * 
	 */
	public void setFirstName(String firstName) {
		this.put(FIRST_NAME, firstName);
	}

	/**
	 * 
	 */
	public String getLastName() {
		return (String) this.get(LAST_NAME);
	}

	/**
	 * 
	 */
	public void setLastName(String lastName) {
		this.put(LAST_NAME, lastName);
	}

	/**
	 * 
	 */
	public int getUserId() {
		return Integer.parseInt((String) this.get(USER_ID));
	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.put(USER_ID, userId);
	}

	/**
	 * 
	 */
	public String getHandicap() {
		return (String) this.get(HANDICAP);
	}

	/**
	 * 
	 */
	public void setHandicap(String handicap) {
		this.put(HANDICAP, handicap);
	}

	/**
	 * 
	 */
	public String getLocation() {
		return (String) this.get(LOCATION);
	}

	/**
	 * 
	 */
	public void setLocation(String location) {
		this.put(LOCATION, location);
	}

	/**
	 * 
	 */
	public String getName() {
		return getProfileHandle();
	}

	/**
	 * 
	 */
	public Object getId() {
		return getUserId();
	}

	@Deprecated
	public void setName(String name) {
	};
}
/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.IGolfer;

/**
 * @author
 * 
 */
public class Golfer extends Entity implements IGolfer {

	private int societyId;
	private String profileHandle;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String location;
	private String handicap;

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Golfer [userId=" + this.getUserId() + ", societyId="
				+ societyId + ", profileHandle=" + profileHandle
				+ ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", handicap=" + handicap
				+ ", location=" + location + "]";
	}

	/**
	 * 
	 * @param userId
	 * @param societyId
	 * @param profileHandle
	 * @param emailAddress
	 * @param firstName
	 * @param lastName
	 * @param location
	 * @param handicap
	 */
	public Golfer(int userId, int societyId, String profileHandle,
			String emailAddress, String firstName, String lastName,
			String location, String handicap) {
		super(profileHandle);
		this.setId(userId);
		this.societyId = societyId;
		this.profileHandle = profileHandle;
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
		this.handicap = handicap;
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param profileHandle
	 */
	public Golfer(int userId, String firstName, String lastName,
			String profileHandle) {
		super(profileHandle);
		this.setId(userId);
		this.firstName = firstName;
		this.lastName = lastName;
		this.profileHandle = profileHandle;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getUserId()
	 */
	public int getUserId() {
		return this.getId();
	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.setId(userId);
	}

	/**
	 * 
	 * @return
	 */
	public int getSocietyId() {
		return societyId;
	}

	/**
	 * 
	 * @param societyId
	 */
	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getProfileHandle()
	 */
	public String getProfileHandle() {
		return this.getName();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setProfileHandle(java.lang.String)
	 */
	public void setProfileHandle(String profileHandle) {
		this.setName(profileHandle);
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getEmailAddress()
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setEmailAddress(java.lang.String)
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getLocation()
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getHandicap()
	 */
	public String getHandicap() {
		return handicap;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setHandicap(java.lang.String)
	 */
	public void setHandicap(String handicap) {
		this.handicap = handicap;
	}
}

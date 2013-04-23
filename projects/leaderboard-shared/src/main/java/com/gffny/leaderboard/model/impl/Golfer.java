/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Date;

import com.gffny.leaderboard.model.IGolfBag;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.abst.SQLEntity;
import com.gffny.leaderboard.util.DateUtils;

/**
 * @author
 * 
 */
public class Golfer extends SQLEntity implements IGolfer {

	private int societyId;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String location;
	private String handedness;
	private int handicap;
	private Date lastLogin;
	private String password;
	private int failedLoginAttemptsCount = 0;
	private IGolfBag golfBag;

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param profileHandle
	 */
	public Golfer(int id, String firstName, String lastName,
			String profileHandle) {
		super(profileHandle, id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastLogin = new Date(); // TODO handle lastLoginDate
	}

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param profileHandle
	 * @param emailAddress
	 * @param location
	 * @param handedness
	 * @param handicap
	 */
	public Golfer(int id, String firstName, String lastName,
			String profileHandle, String emailAddress, String location,
			String handedness, int handicap) {
		this(id, firstName, lastName, profileHandle);
		this.emailAddress = emailAddress;
		this.location = location;
		this.handedness = handedness;
		this.handicap = handicap;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Golfer [userId=" + this.getUserId() + ", societyId="
				+ societyId + ", profileHandle=" + this.getName()
				+ ", emailAddress=" + emailAddress + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", handicap=" + handicap
				+ ", location=" + location + "]";
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
	public Integer getSocietyId() {
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
	public Integer getHandicap() {
		return handicap;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setHandicap(java.lang.String)
	 */
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getLocation()
	 */
	public String getHandedness() {
		return handedness;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setHandedness(String handedness) {
		this.handedness = handedness;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public IGolfBag getGolfBag() {
		return golfBag;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setGolfBag(com.gffny.leaderboard.model.IGolfBag)
	 */
	@Override
	public void setGolfBag(IGolfBag golfBag) {
		this.golfBag = golfBag;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getLastLogin()
	 */
	@Override
	public String getLastLogin() {
		return this.lastLogin.toString();
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLastLogin(java.lang.String)
	 */
	@Override
	public void setLastLogin(String lastLogin) {
		this.lastLogin = DateUtils.parseDateOrNull(lastLogin,
				DateUtils.MYSQL_DATE_FORMAT.getPattern());
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLastLogin(java.lang.String)
	 */
	@Override
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getFailedLoginAttemptsCount()
	 */
	@Override
	public int getFailedLoginAttemptsCount() {
		return this.failedLoginAttemptsCount;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#incrementFailedLoginAttemptsCount()
	 */
	@Override
	public void incrementFailedLoginAttemptsCount() {
		this.failedLoginAttemptsCount++;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#resetFailedLoginAttemptsCount()
	 */
	@Override
	public void resetFailedLoginAttemptsCount() {
		this.failedLoginAttemptsCount = 0;
	}
}

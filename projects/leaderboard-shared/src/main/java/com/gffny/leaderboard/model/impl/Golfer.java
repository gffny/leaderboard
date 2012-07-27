/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.IGolfer;

/**
 * @author 
 *
 */
public class Golfer implements IGolfer {

	public String toString() {
		return "Golfer [userId=" + userId + ", societyId=" + societyId
				+ ", profileHandle=" + profileHandle + ", emailAddress="
				+ emailAddress + ", firstName=" + firstName + ", lastName="
				+ lastName + ", handicap=" + handicap + ", location=" + location +"]";
	}

	public Golfer(int userId, int societyId, String profileHandle,
			String emailAddress, String firstName, String lastName,
			String location, String handicap) {
		super();
		this.userId = userId;
		this.societyId = societyId;
		this.profileHandle = profileHandle;
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
		this.handicap = handicap;
	}

	public Golfer() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSocietyId() {
		return societyId;
	}

	public void setSocietyId(int societyId) {
		this.societyId = societyId;
	}

	public String getProfileHandle() {
		return profileHandle;
	}

	public void setProfileHandle(String profileHandle) {
		this.profileHandle = profileHandle;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHandicap() {
		return handicap;
	}

	public void setHandicap(String handicap) {
		this.handicap = handicap;
	}

	private int userId;
	private int societyId;
	private String profileHandle;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String location;
	private String handicap;
}

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

	public Golfer(int userId, int societyId, String profileHandle,
			String emailAddress, String firstName, String lastName) {
		this.userId = userId;
		this.societyId = societyId;
		this.profileHandle = profileHandle;
		this.setEmailAddress(emailAddress);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Golfer() {

	}

	public String getProfileHandle() {
		return profileHandle;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setProfileHandle(String profileHandle) {
		this.profileHandle = profileHandle;
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

	public int getUserId() {
		return userId;
	}

	public int getSocietyId() {
		return societyId;
	}

	public String toString() {
		return "Golfer [userId=" + userId + ", societyId=" + societyId
				+ ", profileHandle=" + profileHandle + ", emailAddress="
				+ emailAddress + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	private int userId;
	private int societyId;
	private String profileHandle;
	private String emailAddress;
	private String firstName;
	private String lastName;
}

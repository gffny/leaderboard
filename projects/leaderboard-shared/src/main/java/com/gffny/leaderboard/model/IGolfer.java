package com.gffny.leaderboard.model;

public interface IGolfer {

	public String getProfileHandle();

	public void setProfileHandle(String profileHandle);

	public String getEmailAddress();

	public void setEmailAddress(String emailAddress);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);
	
	public String getHandicap();
	
	public void setHandicap(String handicap);
	
	public String getLocation();
	
	public void setLocation(String location);
	
	public int getUserId();

}

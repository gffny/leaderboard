package com.gffny.leaderboard.model;

public interface IGolfCourse {
	
	public String getName();
	
	public String getPar();
	
	public String getTeeColour();
	
	public String[] getHoleParArray();
	
	public String getHolePar(int holeNumber);
	
	public String[] getHoleIndexArray();
	
	public String getHoleIndex(int holeNumber);
	
	public IGolfCourseHole getHole(int holeNumber);
	
	public String[] getTeeDistanceArray();
}

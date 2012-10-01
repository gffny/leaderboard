package com.gffny.leaderboard.model;

public interface IGolfCourse {

	public String getName();

	public int getPar();

	public String getTeeColour();

	public int[] getHoleParArray();

	public int getHolePar(int holeNumber);

	public int[] getHoleIndexArray();

	public int getHoleIndex(int holeNumber);

	public IGolfCourseHole getHole(int holeNumber);

	public int[] getTeeDistanceArray();
}

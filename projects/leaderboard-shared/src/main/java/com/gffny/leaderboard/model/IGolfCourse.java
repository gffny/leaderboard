package com.gffny.leaderboard.model;

import java.util.List;

public interface IGolfCourse {

	public int getCourseId();

	public String getName();

	public int getPar();

	public String getTeeColour();

	public int[] getHoleParArray();

	public int getHolePar(int holeNumber);

	public int[] getHoleIndexArray();

	public int getHoleIndex(int holeNumber);

	public IGolfCourseHole getHole(int holeNumber);

	public List<IGolfCourseHole> getHoleList();

	public int[] getTeeDistanceArray();

	public String toString();
}

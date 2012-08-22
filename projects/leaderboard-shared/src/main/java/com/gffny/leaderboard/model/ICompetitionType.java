package com.gffny.leaderboard.model;

public interface ICompetitionType {

	public String getCompetitionTypeName();

	public int getGroupSize();

	public String getGroupName();

	public boolean isMultiRound();
	
	public int getNumberOfRounds();

}

package com.gffny.leaderboard.model;

public interface ICompetitionType {

	public boolean isIndividual();

	public boolean isPair();

	public boolean isTeam();

	public String getScorer();

	public String getScheduler();

	public String getName();

}

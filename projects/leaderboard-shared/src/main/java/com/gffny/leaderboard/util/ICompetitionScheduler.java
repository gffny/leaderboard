package com.gffny.leaderboard.util;

import java.util.List;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.IGolfer;

public interface ICompetitionScheduler {
	
	public ICompetition generateAndScheduleCompetition(List<IGolfer> competitorList, String competitionName, String date, String firstTeeTime, 
			ICompetitionType competitionType, int groupSize,  boolean listOrdered);

	public ICompetition scheduleNextRound(ICompetition competition, String date, String firstTeeTime, 
			List<IGolfer> competitiorList, int groupSize, boolean listOrdered);

	public ICompetition regenerateAndScheduleCompetition(ICompetition competition, List<IGolfer> updateCompetitorList);
	
	public ICompetition extendCompetition(ICompetition competition, List<IGolfer> updatedCompetitorList);

	//scheduler will do some rudimentary compatibility checks between new and corresponding competition types but exhaustive checking should be done by utilising service/class
	public ICompetition generateAndScheduleCorrespondantCompetition(ICompetition correspondantCompetition, String competitionName, 
			ICompetitionType newCompetitionType);

}

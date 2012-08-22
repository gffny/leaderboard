/**
 * 
 */
package com.gffny.leaderboard.util.impl;

import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.util.ICompetitionScheduler;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 21, 2012
 *
 */
public class SimpleCompetitionScheduler implements ICompetitionScheduler {

	/**
	 * @see com.gffny.leaderboard.util.ICompetitionScheduler#generateCompetition(java.util.List, java.lang.String, java.lang.String, java.lang.String, com.gffny.leaderboard.model.ICompetitionType, int)
	 */
														//ICompetition competition, String date, String firstTeeTime, List<IGolfer> competitiorList, int groupSize, boolean listOrdered
	public ICompetition generateAndScheduleCompetition(List<IGolfer> competitorList,  String competitionName, String date, String firstTeeTime, ICompetitionType competitionType, int groupSize, boolean listOrdered) {
		
		//MULTIPLE CASES BASED ON COMPETITITON TYPE!
		//CASE: INDIVIDUAL SCRATCH
		//CASE: INDIVIDUAL STABLEFORD
		//CASE: INDIVIDUAL STROKEPLAY
		//CASE: INDIVIDUAL MATCHPLAY
		//CASE: PAIR SCRATCH
		//CASE: PAIR STABLEFORD
		//CASE: PAIR STROKEPLAY
		//CASE: PAIR MATCHPLAY
		//CASE: THREE PERSON TEAM SCRATCH
		//CASE: THREE PERSON TEAM STABLEFORD
		//CASE: THREE PERSON TEAM STROKEPLAY
		//CASE: FOUR PERSON TEAM SCRATCH
		//CASE: FOUR PERSON TEAM STABLEFORD
		//CASE: FOUR PERSON TEAM STROKEPLAY
		
		//check the list is valid to proceed
		if(competitorList != null && !competitorList.isEmpty()) {
			//if the list is ordered for a certain competition type, then we do not shuffle!
			if(!listOrdered) {
				competitorList = shuffleList(competitorList);
			}
			int groupSizeToUse = 4;
			if(competitionType.getGroupSize() > 4 || competitionType.getGroupSize() < 1) {
				groupSizeToUse = competitionType.getGroupSize()
			} else if (groupSize > 4 || groupSize < 1)
			//after shufflin' (or not!) we pop the competitors off into their lists!
			while(!competitorList.isEmpty()) {
				for(int i=0; i<)
				
			}
		}
		
		Competition generated = new Competition(competitionName, date, firstTeeTime, competitionType.getCompetitionTypeName(), new ArrayList());
		
		return generated;
	}

	private List<IGolfer> shuffleList(List<IGolfer> competitorList) {
		// TODO SHUFFLIN SHUFFLIN SHUFFLIN
		return competitorList;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.util.ICompetitionScheduler#regenerateCompetition(com.gffny.leaderboard.model.ICompetition, java.util.List)
	 */
	@Override
	public ICompetition regenerateAndScheduleCompetition(ICompetition competition,
			List<IGolfer> updateCompetitorList) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.util.ICompetitionScheduler#extendCompetition(com.gffny.leaderboard.model.ICompetition, java.util.List)
	 */
	@Override
	public ICompetition extendCompetition(ICompetition competition,
			List<IGolfer> updatedCompetitorList) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.util.ICompetitionScheduler#generateCorrespondantCompetition(com.gffny.leaderboard.model.ICompetition, java.lang.String, com.gffny.leaderboard.model.ICompetitionType)
	 */
	@Override
	public ICompetition generateAndScheduleCorrespondantCompetition(
			ICompetition correspondantCompetition, String competitionName,
			ICompetitionType newCompetitionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICompetition scheduleNextRound(ICompetition competition,
			String date, String firstTeeTime, List<IGolfer> competitiorList,
			int groupSize, boolean listOrdered) {
		// TODO Auto-generated method stub
		return null;
	}
}

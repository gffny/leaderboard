/**
 * 
 */
package com.gffny.leaderboard.component.scheduler;

import java.util.Date;
import java.util.List;

import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public interface ICompetitionScheduler {

	/**
	 * 
	 * @param roundNumber
	 * @param roundDate
	 * @param teeTimeArray
	 * @param roundCourse
	 * @param competitorList
	 * @param playerGroupSize
	 * @param numberOfHoles
	 * @return
	 */
	public ICompetitionRound scheduleCompetitionRound(String competitionName,
			int roundNumber, Date roundDate, IGolfCourse roundCourse,
			String[] teeTimeArray, List<IGolfer> competitorList,
			int playerGroupSize, int numberOfHoles);

	/**
	 * 
	 * @param competitionName
	 * @param roundNumber
	 * @return
	 */
	public String getRoundName(String competitionName, int roundNumber);

	/**
	 * 
	 * @param roundName
	 * @param groupNumber
	 * @return
	 */
	public String getGroupName(String roundName, int groupNumber);

}

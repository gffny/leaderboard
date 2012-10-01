/**
 * 
 */
package com.gffny.leaderboard.component.scheduler;

import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;

/**
 * @author John Gaffney (john@gffny.com) Sep 30, 2012
 * 
 */
public interface IParallelCompetitionScheduler {

	/**
	 * 
	 * @param competitionRoundToParallel
	 * @return
	 */
	public ICompetitionRound scheduleParallelCompetitionRound(
			String competitionName, ICompetitionRound competitionRoundToParallel);
}

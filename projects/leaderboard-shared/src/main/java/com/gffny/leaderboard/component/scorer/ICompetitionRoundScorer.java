/**
 * 
 */
package com.gffny.leaderboard.component.scorer;

import java.util.Map;

import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public interface ICompetitionRoundScorer {

	/**
	 * 
	 * @param round
	 * @param scorecardMap
	 * @param golfCourse
	 * @return Map<GolferID, score>
	 */
	public Map<Integer, Integer> scoreCompetitionRound(ICompetitionRound round,
			Map<Integer, IScorecard> scorecardMap, IGolfCourse golfCourse);
}

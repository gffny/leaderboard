/**
 * 
 */
package com.gffny.leaderboard.component.scorer.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer;
import com.gffny.leaderboard.component.scorer.ICompetitionScorer;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetition.IGolfGroup;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class SimpleCompetitionScorer implements ICompetitionScorer,
		ICompetitionRoundScorer {

	/**
	 * @return
	 * @see com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer#scoreCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound,
	 *      java.util.Map)
	 */
	@Override
	public Map<Integer, Integer> scoreCompetitionRound(ICompetitionRound round,
			Map<Integer, IScorecard> scorecardMap, IGolfCourse golfCourse) {
		Map<Integer, Integer> golferScoreMap = new HashMap<Integer, Integer>();
		Iterator<IGolfGroup> groupItr = round.getGroupList().iterator();
		while (groupItr.hasNext()) {
			IGolfGroup group = groupItr.next();
			Iterator<IGolfer> golferItr = group.getGolferList().iterator();
			while (golferItr.hasNext()) {
				IGolfer golfer = golferItr.next();
				IScorecard scorecard = scorecardMap.get(golfer.getUserId());
				if (scorecard != null) {
					golferScoreMap.put(
							golfer.getUserId(),
							scorecard.getGrossScore()
									- scorecard.getHandicapForRound());
				} // TODO handle null scorecard in scorer
			} // end while
		} // end while
		return golferScoreMap;
	}
}

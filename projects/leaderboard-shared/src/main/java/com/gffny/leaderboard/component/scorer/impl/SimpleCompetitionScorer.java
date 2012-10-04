/**
 * 
 */
package com.gffny.leaderboard.component.scorer.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(SimpleCompetitionScorer.class);

	/**
	 * @return
	 * @see com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer#scoreCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound,
	 *      java.util.Map)
	 */
	@Override
	public Map<Integer, Integer> scoreCompetitionRound(ICompetitionRound round,
			Map<Integer, IScorecard> scorecardMap, IGolfCourse golfCourse) {
		if (round != null && scorecardMap != null && (!scorecardMap.isEmpty())
				&& golfCourse != null) {
			// create the scoreMap
			Map<Integer, Integer> golferScoreMap = new HashMap<Integer, Integer>();
			// get the iterator of groups
			Iterator<IGolfGroup> groupItr = round.getGroupList().iterator();
			while (groupItr.hasNext()) {
				// get the next group
				IGolfGroup group = groupItr.next();
				// get the iterator of golfers in a group
				Iterator<IGolfer> golferItr = group.getGolferList().iterator();
				while (golferItr.hasNext()) {
					// get the next golfer
					IGolfer golfer = golferItr.next();
					// get the scorecard for the golfer
					IScorecard scorecard = scorecardMap.get(golfer.getUserId());
					if (scorecard != null) {
						// put the scored card in the golfer score map
						golferScoreMap.put(
								golfer.getUserId(),
								scorecard.getGrossScore()
										- scorecard.getHandicapForRound());
					} else {
						// TODO handle null scorecard in scorer
						log.error("scorecard is null for golfer id "
								+ golfer.getUserId());
					}
				} // end while
			} // end while
			return golferScoreMap;
		} else {
			if (round == null) {
				log.error("competition round is null");
			}
			if (scorecardMap == null) {
				log.error("scorecardMap is null");
			} else if (scorecardMap.isEmpty()) {
				log.error("scorecard map is empty");
			}
			if (golfCourse == null) {
				log.error("golfCourse is null");
			}
			return new HashMap<Integer, Integer>();
		}
	}
}

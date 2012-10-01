/**
 * 
 */
package com.gffny.leaderboard.component.scorer.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetition.IGolfGroup;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class StablefordCompetitionScorer implements ICompetitionRoundScorer {

	/**
	 * @see com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer#scoreCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound,
	 *      java.util.Map, com.gffny.leaderboard.model.IGolfCourse)
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
					golferScoreMap.put(golfer.getUserId(),
							scoreGolferCard(scorecard, golfCourse));
					// scorecard.getGrossScore()
					// - scorecard.getHandicapForRound());
				} // TODO handle null scorecard in scorer
			} // end while
		} // end while
		return golferScoreMap;
	}

	/**
	 * 
	 * @param scorecard
	 * @param golfCourse
	 * @return
	 */
	public int scoreGolferCard(IScorecard scorecard, IGolfCourse golfCourse) {
		// course.getHole expects the hole number (i.e. 1-18)
		for (int i = 1; i <= scorecard.getNumberOfHoles(); i++) {
			// score on hole - handicap on hole -> calc points
			calcPoints(
					golfCourse.getHolePar(i),
					scorecard.getScoreOnHole(i),
					calcHandicapOnHole(golfCourse.getHole(i).getIndex(),
							scorecard.getHandicapForRound(),
							scorecard.getNumberOfHoles()));
		}
		return 0;
	}

	/**
	 * @param holePar
	 * @param scoreOnHole
	 * @param calcHandicapOnHole
	 */
	public int calcPoints(int holePar, int scoreOnHole, int calcHandicapOnHole) {
		int points = 0;
		int scoreRelativeToPar = scoreOnHole - (holePar + calcHandicapOnHole);
		if (scoreRelativeToPar > (-5) && scoreRelativeToPar < (20)) {
			switch (scoreRelativeToPar) {
			// 0pts 2 strokes or more over, or no score recorded
			// 1pt 1 stroke over
			case 1:
				points = 1;
				break;
			// 2pts Same number of strokes
			case 0:
				points = 2;
				break;
			// 3pts 1 stroke under
			case -1:
				points = 3;
				break;
			// 4pts 2 strokes under
			case -2:
				points = 4;
				break;
			// 5pts 3 strokes under
			case -3:
				points = 5;
				break;
			// 6pts 4 strokes under
			case -4:
				points = 6;
				break;
			default:
				points = 0;
				break;
			}
		} // TODO else look at fridge stableford points score
		return points;
	}

	/**
	 * @param index
	 * @param handicapForRound
	 * @return
	 */
	public int calcHandicapOnHole(int index, int handicapForRound,
			int numberOfHoles) {
		int handicapOnHole = 0;
		// handicap is less than index
		if (index < handicapForRound) {
			handicapOnHole = 0;
		}
		// handicap is equal to index
		// handicap i greater than index but less than number of holes
		// handicap 15, number of holes 18, index 12
		if (handicapForRound >= index && handicapForRound <= numberOfHoles) {
			handicapOnHole = 1;
		}
		// handicap is greater than index and great than number of holes
		if (handicapForRound > numberOfHoles) {
			handicapOnHole++;
			// little bit of recursion never hurt anyone!
			// essentially we've added one handicap point for the first
			// "number of holes", now we recalculate for the next
			// "number of holes" until we reach one of the cases above
			handicapOnHole += calcHandicapOnHole(index, handicapForRound
					- numberOfHoles, numberOfHoles);
		}
		return handicapOnHole;
	}
}

/**
 * 
 */
package com.gffny.leaderboard.component.scorer.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger
			.getLogger(StablefordCompetitionScorer.class);

	/**
	 * @see com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer#scoreCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound,
	 *      java.util.Map, com.gffny.leaderboard.model.IGolfCourse)
	 */
	@Override
	public Map<Integer, Integer> scoreCompetitionRound(ICompetitionRound round,
			Map<Integer, IScorecard> scorecardMap, IGolfCourse golfCourse) {
		// create the map to hold the scores
		Map<Integer, Integer> golferScoreMap = new HashMap<Integer, Integer>();
		// get the iterator for the groups
		Iterator<IGolfGroup> groupItr = round.getGroupList().iterator();
		while (groupItr.hasNext()) {
			IGolfGroup group = groupItr.next();
			// get the iterator for golfers in the group
			Iterator<IGolfer> golferItr = group.getGolferList().iterator();
			while (golferItr.hasNext()) {
				IGolfer golfer = golferItr.next();
				// get the scorecard for each golfer
				IScorecard scorecard = scorecardMap.get(golfer.getUserId());
				// check if the scorecard is valid
				if (scorecard != null) {
					// score the scorecard against the course and add to map
					golferScoreMap.put(golfer.getUserId(),
							scoreGolferCard(scorecard, golfCourse));
				} else {
					// TODO handle null scorecard in scorer
					log.error("scorecard is null for golferId: "
							+ golfer.getUserId());
				}
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
		int totalScore = 0;
		if (scorecard != null && golfCourse != null) {
			// course.getHole expects the hole number (i.e. 1-18)
			for (int i = 1; i <= scorecard.getNumberOfHoles(); i++) {
				// score on hole - handicap on hole -> calc points
				totalScore += calcPoints(golfCourse.getHolePar(i), // holePar
						scorecard.getScoreOnHole(i), // holeScore
						calcHandicapOnHole(golfCourse.getHole(i).getIndex(), // index
								scorecard.getHandicapForRound(), // handicap
								scorecard.getNumberOfHoles())); // numberOfHoles
			}
		} else {
			if (scorecard == null) {
				log.error("scorecard is null");
			}
			if (golfCourse == null) {
				log.error("golfCourse is null");
			}
		}
		return totalScore;
	}

	/**
	 * @param holePar
	 * @param scoreOnHole
	 * @param handicapForHole
	 */
	public int calcPoints(int holePar, int scoreOnHole, int handicapForHole) {
		int points = 0;
		if (holePar > 2 && scoreOnHole > 0 && handicapForHole >= 0) {
			int scoreRelativeToPar = scoreOnHole - (holePar + handicapForHole);
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
			} // TODO else look at fringe stableford points score
		} else {
			if (holePar < 3) {
				log.error("par for hole is less than three: " + holePar);
			}
			if (scoreOnHole < 1) {
				log.error("score on hole is less than one: " + scoreOnHole);
			}
			if (handicapForHole < 0 || handicapForHole > 5) {
				log.error("handicap for hole is either below zero or may be unreasonably high: "
						+ handicapForHole);
			}
		}
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

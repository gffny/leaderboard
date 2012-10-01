/**
 * 
 */
package com.gffny.leaderboard.util;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class CompetitionFunction {

	/**
	 * 
	 * @param competition
	 * @param round
	 * @return
	 */
	public static boolean checkCompetitionRoundType(ICompetition competition,
			ICompetitionRound round) {
		// TODO implement in meaningful way
		/*
		 * check if the competition type (individual vs team) is compatible with
		 * the round. this function will be handy for checking in the scorer and
		 * the schedulers
		 */
		return true;
	}

}

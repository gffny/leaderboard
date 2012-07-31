/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Arrays;

import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 30, 2012
 *
 */
public class Scorecard implements IScorecard {

	/**
	 * 
	 */
	public String toString() {
		return "Scorecard [teesPlayedOff="
				+ teesPlayedOff
				+ ", scoreArray="
				+ ", scoreArray="
				+ (scoreArray != null ? Arrays.asList(scoreArray).subList(0,
						Math.min(scoreArray.length, 5)) : null)
				+ ", scorecardNotes=" + scorecardNotes + ", grossScore="
				+ grossScore + ", scorecardDate="+ scorecardDate+"]";
	}

	/**
	 *
	 */
	public String getScoreOnHole(int holeNumber) {
		return scoreArray[holeNumber-1];
	}

	/**
	 *
	 */
	public String getGrossScore() {
		return grossScore;
	}

	/**
	 * 
	 */
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}

	/**
	 * 
	 */
	public int getTeesPlayedOffCode() {
		if(this.teesPlayedOff.equals(IScorecard.GOLDS_COLOUR)) {
			return IScorecard.GOLDS_CODE;
		}
		if(this.teesPlayedOff.equals(IScorecard.BLUES_COLOUR)) {
			return IScorecard.BLUES_CODE;
		}		
		if(this.teesPlayedOff.equals(IScorecard.WHITES_COLOUR)) {
			return IScorecard.WHITES_CODE;
		}
		if(this.teesPlayedOff.equals(IScorecard.REDS_COLOUR)) {
			return IScorecard.REDS_CODE;
		}
		if(this.teesPlayedOff.equals(IScorecard.GREENS_COLOUR)) {
			return IScorecard.GREENS_CODE;
		}
		return 0;
	}

	/**
	 * 
	 */
	public String getTeesPlayedOffColour() {
		return this.teesPlayedOff;
	}

	/**
	 * 
	 */
	public String getScorecardDate() {
		return this.scorecardDate;
	}

	/**
	 * @param teesPlayedOff
	 * @param scoreArray
	 * @param scorecardNotes
	 */
	public Scorecard(String teesPlayedOff, 
			String scoreHole1,
			String scoreHole2,
			String scoreHole3,
			String scoreHole4,
			String scoreHole5,
			String scoreHole6,
			String scoreHole7,
			String scoreHole8,
			String scoreHole9,
			String scoreHole10,
			String scoreHole11,
			String scoreHole12,
			String scoreHole13,
			String scoreHole14,
			String scoreHole15,
			String scoreHole16,
			String scoreHole17,
			String scoreHole18,
			String scorecardNotes,
			String scorecardDate) {
		this.teesPlayedOff = teesPlayedOff;
		this.scoreArray[0] = scoreHole1;
		this.scoreArray[1] = scoreHole2;
		this.scoreArray[2] = scoreHole3;
		this.scoreArray[3] = scoreHole4;
		this.scoreArray[4] = scoreHole5;
		this.scoreArray[5] = scoreHole6;
		this.scoreArray[6] = scoreHole7;
		this.scoreArray[7] = scoreHole8;
		this.scoreArray[8] = scoreHole9;
		this.scoreArray[9] = scoreHole10;
		this.scoreArray[10] = scoreHole11;
		this.scoreArray[11] = scoreHole12;
		this.scoreArray[12] = scoreHole13;
		this.scoreArray[13] = scoreHole14;
		this.scoreArray[14] = scoreHole15;
		this.scoreArray[15] = scoreHole16;
		this.scoreArray[16] = scoreHole17;
		this.scoreArray[17] = scoreHole18;		
		this.scorecardNotes = scorecardNotes;
		this.scorecardDate = scorecardDate;
		calcGrossScore();
	}

	private void calcGrossScore() {
		int grossScoreVal = 0;
		for(int i=0; i<scoreArray.length; i++) {
			grossScoreVal += Integer.parseInt(scoreArray[i]);
		}
		grossScore = String.valueOf(grossScoreVal);
	}

	private String teesPlayedOff = new String();
	private String[] scoreArray = new String[18];
	private String scorecardNotes = new String();
	private String grossScore = new String();
	private String scorecardDate = new String();
}

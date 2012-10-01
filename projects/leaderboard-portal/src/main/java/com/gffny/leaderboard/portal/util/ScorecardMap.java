/**
 * 
 */
package com.gffny.leaderboard.portal.util;

import java.util.HashMap;

import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 30, 2012
 *
 */
public class ScorecardMap extends HashMap<String, Object> implements IScorecard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public String getScoreOnHole(int holeNumber) {
		return this.scoreArray[holeNumber];
	}

	/**
	 * 
	 */
	public String getGrossScore() {
		return this.grossScore;
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
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}
	
	/**
	 * 
	 */
	public String getScorecardDate() {
		return scorecardDate;
	}

	/**
	 * @param scoreArray
	 * @param grossScore
	 * @param teePlayedOffColour
	 * @param scorecardNotes
	 * @param scorecardDate
	 */
	public ScorecardMap(String[] scoreArray, String grossScore,
			String teePlayedOffColour, String scorecardNotes, String scorecardDate) {
		this.put("scoreHoleOne", scoreArray[0]);
		this.put("scoreHoleTwo", scoreArray[1]);
		this.put("scoreHoleThree", scoreArray[2]);
		this.put("scoreHoleFour", scoreArray[3]);
		this.put("scoreHoleFive", scoreArray[4]);
		this.put("scoreHoleSix", scoreArray[5]);
		this.put("scoreHoleSeven", scoreArray[6]);
		this.put("scoreHoleEight", scoreArray[7]);
		this.put("scoreHoleNine", scoreArray[8]);
		this.put("scoreHoleTen", scoreArray[9]);
		this.put("scoreHoleEleven", scoreArray[10]);
		this.put("scoreHoleTwelve", scoreArray[11]);
		this.put("scoreHoleThirteen", scoreArray[12]);
		this.put("scoreHoleFourteen", scoreArray[13]);
		this.put("scoreHoleFifteen", scoreArray[14]);
		this.put("scoreHoleSixteen", scoreArray[15]);
		this.put("scoreHoleSeventeen", scoreArray[16]);
		this.put("scoreHoleEighteen", scoreArray[17]);
		this.put("scoreArray", scoreArray);
		this.put("grossScore", grossScore);
		this.put("teesPlayedOff", teePlayedOffColour);
		this.put("scorecardNotes", scorecardNotes);
		this.put("scorecardDate", scorecardDate);

		this.scoreArray = scoreArray;
		this.grossScore = grossScore;
		this.teesPlayedOff = teePlayedOffColour;
		this.scorecardNotes = scorecardNotes;
		this.scorecardDate = scorecardDate;
	}

	private String[] scoreArray = new String[18];
	private String grossScore = new String();
	private String teesPlayedOff = new String();
	private String scorecardNotes = new String();
	private String scorecardDate = new String();
}

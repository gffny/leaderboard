/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.Date;

/**
 * @author John Gaffney (john@gffny.com) Jul 27, 2012
 * 
 */
public interface IScorecard {

	public static final int HOLE_1 = 1;
	public static final int HOLE_2 = 2;
	public static final int HOLE_3 = 3;
	public static final int HOLE_4 = 4;
	public static final int HOLE_5 = 5;
	public static final int HOLE_6 = 6;
	public static final int HOLE_7 = 7;
	public static final int HOLE_8 = 8;
	public static final int HOLE_9 = 9;
	public static final int HOLE_10 = 10;
	public static final int HOLE_11 = 11;
	public static final int HOLE_12 = 12;
	public static final int HOLE_13 = 13;
	public static final int HOLE_14 = 14;
	public static final int HOLE_15 = 15;
	public static final int HOLE_16 = 16;
	public static final int HOLE_17 = 17;
	public static final int HOLE_18 = 18;

	public static final int GOLDS_CODE = 100;
	public static final int WHITES_CODE = 200;
	public static final int BLUES_CODE = 300;
	public static final int REDS_CODE = 400;
	public static final int GREENS_CODE = 500;

	public static final String GREENS_COLOUR = "GREENS_CODE";
	public static final String REDS_COLOUR = "REDS_CODE";
	public static final String WHITES_COLOUR = "WHITES_CODE";
	public static final String BLUES_COLOUR = "BLUES_CODE";
	public static final String GOLDS_COLOUR = "GOLDS_CODE";

	public int getScoreOnHole(int holeNumber);

	public int getGrossScore();

	public int getTeesPlayedOffCode();

	public String getTeesPlayedOffColour();

	public String getScorecardNotes();

	public Date getScorecardDate();

	public int getNumberOfHoles();

	public int getHandicapForRound();
}

/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.Date;

/**
 * @author John Gaffney (john@gffny.com) Jul 27, 2012
 * 
 */
public interface IScorecard extends IEntity {

	/**
	 * 
	 */
	public static final int HOLE_1 = 1;

	/**
	 * 
	 */
	public static final int HOLE_2 = 2;

	/**
	 * 
	 */
	public static final int HOLE_3 = 3;

	/**
	 * 
	 */
	public static final int HOLE_4 = 4;

	/**
	 * 
	 */
	public static final int HOLE_5 = 5;

	/**
	 * 
	 */
	public static final int HOLE_6 = 6;

	/**
	 * 
	 */
	public static final int HOLE_7 = 7;

	/**
	 * 
	 */
	public static final int HOLE_8 = 8;

	/**
	 * 
	 */
	public static final int HOLE_9 = 9;

	/**
	 * 
	 */
	public static final int HOLE_10 = 10;

	/**
	 * 
	 */
	public static final int HOLE_11 = 11;

	/**
	 * 
	 */
	public static final int HOLE_12 = 12;

	/**
	 * 
	 */
	public static final int HOLE_13 = 13;

	/**
	 * 
	 */
	public static final int HOLE_14 = 14;

	/**
	 * 
	 */
	public static final int HOLE_15 = 15;

	/**
	 * 
	 */
	public static final int HOLE_16 = 16;

	/**
	 * 
	 */
	public static final int HOLE_17 = 17;

	/**
	 * 
	 */
	public static final int HOLE_18 = 18;

	/**
	 * 
	 */
	public static final String GREENS_COLOUR = "00FF00";

	/**
	 * 
	 */
	public static final String REDS_COLOUR = "FF0000";

	/**
	 * 
	 */
	public static final String WHITES_COLOUR = "000000";

	/**
	 * 
	 */
	public static final String BLUES_COLOUR = "0000FF";

	/**
	 * 
	 */
	public static final String GOLDS_COLOUR = "FFFF00";

	/**
	 * 
	 * @param holeNumber
	 * @return
	 */
	public int getScoreOnHole(int holeNumber);

	/**
	 * 
	 * @return
	 */
	public int getGrossScore();

	/**
	 * 
	 * @return
	 */
	public String getTeesPlayedOffCode();

	/**
	 * 
	 * @return
	 */
	public String getTeesPlayedOffColour();

	/**
	 * 
	 * @return
	 */
	public String getScorecardNotes();

	/**
	 * 
	 * @return
	 */
	public Date getScorecardDate();

	/**
	 * 
	 * @return
	 */
	public int getNumberOfHoles();

	/**
	 * 
	 * @return
	 */
	public int getHandicapForRound();
}

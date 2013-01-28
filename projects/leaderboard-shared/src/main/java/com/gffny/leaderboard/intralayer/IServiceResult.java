package com.gffny.leaderboard.intralayer;

/**
 * 
 * @author John Gaffney (john@gffny.com) Oct 17, 2012
 * 
 */
public interface IServiceResult {

	public static int SAVE_COMPETITION_SUCCESS = 1001;
	public static int SAVE_COMPETITION_FAIL = 1002;

	/**
	 * 
	 * @return
	 */
	public int getResultCode();

	/**
	 * 
	 * @return
	 */
	public String getMessage();
}

/**
 * 
 */
package com.gffny.leaderboard.intralayer;

/**
 * @author John Gaffney (john@gffny.com) Jan 27, 2013
 * 
 */
public class ServiceResult implements IServiceResult {

	private int resultCode;

	private String message;

	/**
	 * 
	 * @param message
	 * @param resultCode
	 */
	public ServiceResult(String message, int resultCode) {
		this.message = message;
		this.resultCode = resultCode;
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IServiceResult#getResultCode()
	 */
	@Override
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IServiceResult#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
}

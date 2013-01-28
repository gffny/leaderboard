package com.gffny.leaderboard.intralayer;

public class DAOResult {

	private boolean successful;
	private String id;

	/**
	 * 
	 * @param successful
	 * @param id
	 */
	public DAOResult(boolean successful) {
		this.successful = successful;
		this.id = "";
	}

	/**
	 * 
	 * @param successful
	 * @param id
	 */
	public DAOResult(boolean successful, String id) {
		this.successful = successful;
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdAsInt() {
		return new Integer(id);
	}
}

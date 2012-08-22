package com.gffny.leaderboard.intralayer;

public class DAOResult {

	private boolean successful;

	public DAOResult(boolean successful) {
		this.successful = successful;
	}
	
	public boolean isSuccessful() {
		return successful;
	}
}

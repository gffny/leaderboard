package com.gffny.leaderboard.layerUtils;

public class DAOResult {

	private boolean successful;

	public DAOResult(boolean successful) {
		this.successful = successful;
	}
	
	public boolean isSuccessful() {
		return successful;
	}
}

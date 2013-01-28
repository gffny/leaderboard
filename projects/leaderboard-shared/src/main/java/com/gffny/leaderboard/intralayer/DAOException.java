package com.gffny.leaderboard.intralayer;

import java.sql.SQLException;

public class DAOException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1096440881336259454L;

	/**
	 * 
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public DAOException() {
		super();
	}
}

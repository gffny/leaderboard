/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.ICountry.IState;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public class State implements IState {

	private String name, code;

	/**
	 * 
	 * @param name
	 * @param code
	 */
	public State(String name, String code) {
		this.name = name;
		this.code = code;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICountry.IState#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICountry.IState#getCode()
	 */
	@Override
	public String getCode() {
		return code;
	}
}

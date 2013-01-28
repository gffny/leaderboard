/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.ICountry.IState;
import com.gffny.leaderboard.model.abst.SQLEntity;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public class State extends SQLEntity implements IState {

	private String name, code;

	/**
	 * 
	 * @param name
	 * @param code
	 */
	public State(String name, String code) {
		super(name, 0);
		this.code = code;
	}

	/**
	 * 
	 * @param name
	 * @param code
	 */
	public State(int stateId, String name, String code) {
		super(name, stateId);
		this.setId(stateId);
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

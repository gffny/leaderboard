/**
 * 
 */
package com.gffny.leaderboard.model.abst;

import com.gffny.leaderboard.model.INoSQLEntity;

/**
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public abstract class NoSQLEntity implements INoSQLEntity {

	private String id;
	private String name;

	/**
	 * 
	 */
	public NoSQLEntity(String name) {
		this.name = name;
		this.id = "";
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * the name to set
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
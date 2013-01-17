/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.IEntity;

/**
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public class Entity implements IEntity {

	private int id;
	private String name;

	/**
	 * 
	 */
	public Entity(String name) {
		this.name = name;
		this.id = -1;
	}

	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
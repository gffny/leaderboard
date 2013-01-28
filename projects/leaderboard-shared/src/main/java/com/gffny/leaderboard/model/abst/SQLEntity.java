/**
 * 
 */
package com.gffny.leaderboard.model.abst;

import com.gffny.leaderboard.model.IEntity;

/**
 * @author John Gaffney (john@gffny.com) Jan 23, 2013
 * 
 */
public abstract class SQLEntity extends Entity implements IEntity {

	private String name;
	private int id;

	/**
	 * @param name
	 */
	public SQLEntity(String name, int id) {
		this.name = name;
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * the id to return
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @see com.gffny.leaderboard.model.IEntity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}

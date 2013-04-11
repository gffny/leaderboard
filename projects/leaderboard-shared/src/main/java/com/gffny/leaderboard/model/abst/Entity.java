/**
 * 
 */
package com.gffny.leaderboard.model.abst;

import com.gffny.leaderboard.model.IEntity;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public abstract class Entity implements IEntity {

	/**
	 * 
	 */
	private boolean isNew = false;

	/**
	 * 
	 * @return
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * 
	 * @param value
	 */
	public void setNew(boolean value) {
		this.isNew = value;
	}
}

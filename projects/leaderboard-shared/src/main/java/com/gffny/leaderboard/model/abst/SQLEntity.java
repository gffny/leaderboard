/**
 * 
 */
package com.gffny.leaderboard.model.abst;

import java.util.Date;

import com.gffny.leaderboard.model.IEntity;
import com.gffny.leaderboard.util.DateUtils;

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
		if (id == 0) {
			this.setNew(true);
		}
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

	/**
	 * @param date
	 */
	protected Date formatDate(String date) {
		return DateUtils.parseDateOrNull(date,
				DateUtils.MYSQL_DATE_FORMAT.getPattern());
	}
}

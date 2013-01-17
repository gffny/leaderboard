/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.List;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public interface ICountry extends IEntity {

	/**
	 * 
	 * @return
	 */
	public String getCode();

	/**
	 * 
	 * @return
	 */
	public List<IState> getStateList();

	/**
	 * 
	 * @author John Gaffney (john@gffny.com) Jan 14, 2013
	 * 
	 */
	public interface IState extends IEntity {

		/**
		 * 
		 * @return
		 */
		public String getCode();
	}
}

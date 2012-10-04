/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.List;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public interface ICountry {

	public String getName();

	public String getCode();

	public List<IState> getStateList();

	public interface IState {

		public String getName();

		public String getCode();
	}
}

/**
 * 
 */
package com.gffny.leaderboard.intralayer;

import java.util.Collection;

/**
 * @author John Gaffney (john@gffny.com) Apr 22, 2013
 * 
 */
public interface IDAOResult {

	/**
	 * 
	 * @return
	 */
	public abstract boolean isSuccessful();

	/**
	 * 
	 * @return
	 */
	public abstract String getId();

	/**
	 * 
	 * @return
	 */
	public abstract int getIdAsInt();

	/**
	 * 
	 * @param subResult
	 */
	public abstract void addDAOResultToSubList(IDAOResult subResult);

	/**
	 * 
	 * @param resultList
	 */
	public abstract void addDAOResultCollectionToSubResultCollection(
			Collection<IDAOResult> resultList);

	/**
	 * 
	 * @return
	 */
	public abstract Collection<IDAOResult> getSubResultCollection();

}
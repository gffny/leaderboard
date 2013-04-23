package com.gffny.leaderboard.intralayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.gffny.leaderboard.util.CollectionUtils;

public class DAOResult implements IDAOResult {

	private boolean successful;
	private String id;
	private List<IDAOResult> subResultList = new ArrayList<IDAOResult>();

	/**
	 * 
	 * @param successful
	 * @param id
	 */
	public DAOResult(boolean successful) {
		this.successful = successful;
		this.id = "";
	}

	/**
	 * 
	 * @param successful
	 * @param id
	 */
	public DAOResult(boolean successful, String id) {
		this.successful = successful;
		this.id = id;
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IDAOResult#isSuccessful()
	 */
	@Override
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IDAOResult#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IDAOResult#getIdAsInt()
	 */
	@Override
	public int getIdAsInt() {
		return new Integer(id);
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IDAOResult#addDAOResultToSubList(com.gffny.leaderboard.intralayer.IDAOResult)
	 */
	@Override
	public void addDAOResultToSubList(IDAOResult subResult) {
		if (subResult != null && subResultList != null) {
			if (!subResult.isSuccessful()) {
				this.successful = false;
			}
			subResultList.add(subResult);
		}
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IDAOResult#addDAOResultCollectionToSubResultCollection(java.util.Collection)
	 */
	@Override
	public void addDAOResultCollectionToSubResultCollection(
			Collection<IDAOResult> resultList) {
		if (resultList != null && CollectionUtils.isNotEmpty(resultList)) {
			Iterator<IDAOResult> resultIterator = resultList.iterator();
			while (resultIterator.hasNext()) {
				addDAOResultToSubList(resultIterator.next());
			}
		}
	}

	/**
	 * @see com.gffny.leaderboard.intralayer.IDAOResult#getSubResultCollection()
	 */
	@Override
	public Collection<IDAOResult> getSubResultCollection() {
		return subResultList;
	}
}

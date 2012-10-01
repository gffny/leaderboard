/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.IGolfGroup;
import com.gffny.leaderboard.util.TimeFunction;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public class CompetitionRound implements ICompetition.ICompetitionRound {

	private int number;
	private String name;
	private Date date;
	private List<IGolfGroup> groupList;
	private Map<IGolfGroup, Date> teeTimeMap;

	/**
	 * @param name
	 * @param roundNumber
	 * @param date
	 * @param firstTeeTime
	 * @param groupList
	 * @param teeTimeMap
	 */
	public CompetitionRound(final String name, int roundNumber,
			final Date date, final List<IGolfGroup> groupList,
			final Map<IGolfGroup, Date> teeTimeMap) {
		this.name = name;
		this.date = date;
		this.number = roundNumber;
		this.groupList = groupList;
		this.teeTimeMap = teeTimeMap;
	}

	/**
	 * 
	 */
	public String getRoundName() {
		return name;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getRoundNumber()
	 */
	public int getRoundNumber() {
		return number;
	}

	/**
	 * 
	 */
	public Date getRoundDate() {
		return date;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getDateAsString()
	 */
	@Override
	public String getDateAsString() {
		return TimeFunction.formatDate(date); // TODO implement meaningfully
	};

	/**
	 * 
	 */
	public String getRoundFirstTeeTime() {
		return null; // TODO implement meaningfully
	}

	/**
	 * 
	 */
	public List<IGolfGroup> getGroupList() {
		return groupList;
	}

	/**
	 * 
	 */
	public Map<IGolfGroup, Date> getTeeTimeMap() {
		return teeTimeMap;
	}

	/**
	 * 
	 * @param groupID
	 * @return
	 */
	@Override
	public String getGroupTeeTime(int groupID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoundName(String roundName) {
		this.name = roundName;
	};

	/**
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public CompetitionRound clone() {
		CompetitionRound clone = new CompetitionRound(this.name, this.number,
				this.date, this.groupList, this.teeTimeMap);
		return clone;
	}

	/**
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final int maxLen = 2;
		return "CompetitionRound [name="
				+ name
				+ ", date="
				+ date
				+ ", groupList="
				+ (groupList != null ? toString(groupList, maxLen) : null)
				+ ", teeTimeMap="
				+ (teeTimeMap != null ? toString(teeTimeMap.entrySet(), maxLen)
						: null) + "]";
	}

	/**
	 * 
	 * @param collection
	 * @param maxLen
	 * @return
	 */
	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext()
				&& i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
}

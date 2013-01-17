/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetition.IGolfGroup;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.util.TimeFunction;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public class CompetitionRound extends Entity implements
		ICompetition.ICompetitionRound {

	private int competitionId;
	private int courseId;
	private int number;
	private int holeListLength;
	private Date date;
	private List<IGolfGroup> groupList;
	private Map<IGolfGroup, Date> teeTimeMap;
	private ICompetitionType competitionType;

	/**
	 * 
	 * @param name
	 * @param roundNumber
	 * @param date
	 * @param groupList
	 * @param teeTimeMap
	 */
	public CompetitionRound(final String name, int roundNumber,
			final Date date, int courseId, final List<IGolfGroup> groupList,
			final Map<IGolfGroup, Date> teeTimeMap) {
		super(name);
		this.date = date;
		this.number = roundNumber;
		this.courseId = courseId;
		this.groupList = groupList;
		this.teeTimeMap = teeTimeMap;
	}

	/**
	 * 
	 * @param name
	 * @param roundNumber
	 * @param date
	 * @param groupList
	 * @param teeTimeMap
	 */
	public CompetitionRound(final String name, int roundNumber,
			final Date date, int courseId) {
		super(name);
		this.date = date;
		this.number = roundNumber;
		this.courseId = courseId;
		this.groupList = new LinkedList<IGolfGroup>();
		this.teeTimeMap = new HashMap<IGolfGroup, Date>();
	}

	/**
	 * @param name
	 * @param roundNumber
	 * @param date
	 * @param firstTeeTime
	 * @param groupList
	 * @param teeTimeMap
	 */
	public CompetitionRound(int roundId, final String name, int roundNumber,
			int courseId, final Date date, final List<IGolfGroup> groupList,
			final Map<IGolfGroup, Date> teeTimeMap) {
		super(name);
		this.setId(roundId);
		this.date = date;
		this.number = roundNumber;
		this.courseId = courseId;
		this.groupList = groupList;
		this.teeTimeMap = teeTimeMap;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getCompetitionId()
	 */
	@Override
	public int getCompetitionId() {
		return competitionId;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setCompetitionId(int)
	 */
	@Override
	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}

	/**
	 * 
	 */
	@Override
	public int getRoundId() {
		return this.getId();
	}

	@Override
	public String getRoundIdAsString() {
		return String.valueOf(this.getId());
	}

	/**
	 * 
	 */
	@Override
	public String getRoundName() {
		return this.getName();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getRoundNumber()
	 */
	@Override
	public int getRoundNumber() {
		return number;
	}

	/**
	 * 
	 */
	@Override
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
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getCourseId()
	 */
	@Override
	public int getCourseId() {
		return courseId;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getCourseIdAsString()
	 */
	@Override
	public String getCourseIdAsString() {
		return String.valueOf(courseId);
	}

	/**
	 * 
	 */
	@Override
	public String getRoundFirstTeeTime() {
		return null; // TODO implement meaningfully
	}

	/**
	 * 
	 */
	@Override
	public List<IGolfGroup> getGroupList() {
		return groupList;
	}

	/**
	 * 
	 */
	@Override
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

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#addGroup(com.gffny.leaderboard.model.ICompetition.IGolfGroup,
	 *      java.util.Date)
	 */
	@Override
	public void addGroup(IGolfGroup group, Date dateTime) {
		if (groupList == null) {
			groupList = new LinkedList<IGolfGroup>();
		}
		if (teeTimeMap == null) {
			teeTimeMap = new HashMap<ICompetition.IGolfGroup, Date>();
		}
		groupList.add(group);
		teeTimeMap.put(group, dateTime);
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#clearGroups()
	 */
	@Override
	public void clearGroups() {
		if (groupList == null) {
			groupList = new LinkedList<IGolfGroup>();
		}
		if (teeTimeMap == null) {
			teeTimeMap = new HashMap<ICompetition.IGolfGroup, Date>();
		}
		groupList.clear();
		teeTimeMap.clear();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setRoundId(int)
	 */
	@Override
	public void setRoundId(int roundId) {
		this.setId(roundId);
	};

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setRoundName(java.lang.String)
	 */
	@Override
	public void setRoundName(String roundName) {
		this.setName(roundName);
	};

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getHoleListLength()
	 */
	@Override
	public int getHoleListLength() {
		return holeListLength;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setHoleListLength(int)
	 */
	@Override
	public void setHoleListLength(int holeListLength) {
		this.holeListLength = holeListLength;
	}

	/**
	 * @return the competitionType
	 */
	@Override
	public ICompetitionType getCompetitionType() {
		return competitionType;
	}

	/**
	 * @param competitionType
	 *            the competitionType to set
	 */
	@Override
	public void setCompetitionType(ICompetitionType competitionType) {
		this.competitionType = competitionType;
	}

	/**
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ICompetitionRound clone() {
		ICompetitionRound clone = new CompetitionRound(this.getRoundId(),
				this.getRoundName(), this.getRoundNumber(), this.getCourseId(),
				this.getRoundDate(), this.getGroupList(), this.getTeeTimeMap());
		// new CompetitionRound(this.getRoundId(),
		// this.getName(), this.number, this.date, this.courseId,
		// this.groupList, this.teeTimeMap);
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
				+ this.getRoundName()
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

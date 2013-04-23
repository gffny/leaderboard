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
import com.gffny.leaderboard.model.abst.SQLEntity;
import com.gffny.leaderboard.util.DateUtils;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public class CompetitionRound extends SQLEntity implements
		ICompetition.ICompetitionRound {

	private int competitionId;
	private String courseId;
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
	public CompetitionRound(String name, int roundNumber, String date,
			String courseId) {
		super(name, UNSAVED_ROUND_ID);
		this.date = formatDate(date);
		this.number = roundNumber;
		this.courseId = courseId;
		this.groupList = new LinkedList<IGolfGroup>();
		this.teeTimeMap = new HashMap<IGolfGroup, Date>();
	}

	/**
	 * 
	 * @param name
	 * @param roundNumber
	 * @param date
	 * @param groupList
	 * @param teeTimeMap
	 */
	public CompetitionRound(int roundId, String name, int roundNumber,
			String date, String courseId) {
		super(name, roundId);
		this.date = formatDate(date);
		this.number = roundNumber;
		this.courseId = courseId;
		this.groupList = new LinkedList<IGolfGroup>();
		this.teeTimeMap = new HashMap<IGolfGroup, Date>();
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getCompetitionId()
	 */
	@Override
	public int getCompetitionId() {
		return competitionId;
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
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getRoundDateAsString()
	 */
	@Override
	public String getRoundDateAsString() {
		return DateUtils.format(date, DateUtils.MYSQL_DATE_FORMAT.getPattern());
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getCourseId()
	 */
	@Override
	public String getCourseId() {
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
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#getHoleListLength()
	 */
	@Override
	public int getHoleListLength() {
		return holeListLength;
	}

	/**
	 * @return the competitionType
	 */
	@Override
	public ICompetitionType getCompetitionType() {
		return competitionType;
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
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setDate(java.util.Date)
	 */
	@Override
	public void setDate(String date) {
		this.date = formatDate(date);
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setHoleListLength(int)
	 */
	@Override
	public void setHoleListLength(int holeListLength) {
		this.holeListLength = holeListLength;
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
	 * @see com.gffny.leaderboard.model.ICompetition.ICompetitionRound#setCompetitionId(int)
	 */
	@Override
	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
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
	 * @see java.lang.Object#clone()
	 */
	public ICompetitionRound clone() {
		return new CompetitionRound(getRoundId(), getRoundName(),
				getRoundNumber(), getRoundDateAsString(), getCourseId());
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

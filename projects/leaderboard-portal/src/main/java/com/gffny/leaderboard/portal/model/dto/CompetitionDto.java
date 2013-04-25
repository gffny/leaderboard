/**
 * 
 */
package com.gffny.leaderboard.portal.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;

/**
 * @author John Gaffney (john@gffny.com) Apr 24, 2013
 * 
 */
public class CompetitionDto extends BaseDto implements ICompetition {

	private int competitionId;
	private String competitionName;
	private boolean newEntity;
	private ICompetitionType competitionScoringSystem;
	private String competitionVisibility;

	private Map<Integer, ICompetition.ICompetitionRound> roundNumberMap;
	private Map<Date, ICompetition.ICompetitionRound> roundDateMap;
	private static final int DEFAULT_ROUND_NUMBER = 4;

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ISQLEntity#setId(java.lang.Integer)
	 */
	@Override
	public void setId(Integer id) {
		this.competitionId = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#getName()
	 */
	@Override
	public String getName() {
		return competitionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.competitionName = name;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#getId()
	 */
	@Override
	public Object getId() {
		return Integer.valueOf(competitionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#setNew(boolean)
	 */
	@Override
	public void setNew(boolean value) {
		this.newEntity = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#isNew()
	 */
	@Override
	public boolean isNew() {
		return newEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionId()
	 */
	@Override
	public int getCompetitionId() {
		return this.competitionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionIdAsString()
	 */
	@Override
	public String getCompetitionIdAsString() {
		return String.valueOf(competitionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionName()
	 */
	@Override
	public String getCompetitionName() {
		return competitionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#getCompetitionScoringSystem()
	 */
	@Override
	public ICompetitionType getCompetitionScoringSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionVisibility()
	 */
	@Override
	public String getCompetitionVisibility() {
		return competitionVisibility;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#getRoundByDate(java.util.Date)
	 */
	@Override
	public ICompetitionRound getRoundByDate(Date roundDate) {
		return roundDateMap.get(roundDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getRoundByNumber(int)
	 */
	@Override
	public ICompetitionRound getRoundByNumber(int roundNumber) {
		return roundNumberMap.get(Integer.valueOf(roundNumber));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionRoundList()
	 */
	@Override
	public List<ICompetitionRound> getCompetitionRoundList() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#addCompetitionRound(com.gffny
	 * .leaderboard.model.ICompetition.ICompetitionRound)
	 */
	@Override
	public void addCompetitionRound(ICompetitionRound round) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#isIndividualCompetition()
	 */
	@Override
	public boolean isIndividualCompetition() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#addCompetitionRoundList(java
	 * .util.List)
	 */
	@Override
	public void addCompetitionRoundList(
			List<ICompetitionRound> competitionRoundList) {
		// TODO Auto-generated method stub

	}

}

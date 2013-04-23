/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.abst.SQLEntity;
import com.gffny.leaderboard.util.CollectionUtils;
import com.gffny.leaderboard.util.CompetitionFunction;

/**
 * @author John Gaffney (john@gffny.com) Aug 21, 2012
 * 
 */
public class Competition extends SQLEntity implements ICompetition {

	private ICompetitionType competitionScoringSystem;
	private String competitionVisibility;
	private Map<Integer, ICompetition.ICompetitionRound> roundNumberMap;
	private Map<Date, ICompetition.ICompetitionRound> roundDateMap;
	private static final int DEFAULT_ROUND_NUMBER = 4;

	/**
	 * @param name
	 * @param date
	 * @param firstTeeTime
	 * @param competitionScoringSystem
	 * @param roundNumberMap
	 */
	public Competition(int competitionId, String name,
			ICompetitionType competitionScoringSystem,
			String competitionVisibility) {
		super(name, competitionId);
		this.competitionScoringSystem = competitionScoringSystem;
		this.competitionVisibility = competitionVisibility;
		this.roundNumberMap = new HashMap<Integer, ICompetition.ICompetitionRound>(
				DEFAULT_ROUND_NUMBER);
		this.roundDateMap = new HashMap<Date, ICompetition.ICompetitionRound>(
				DEFAULT_ROUND_NUMBER);
	}

	/**
	 * @param name
	 * @param date
	 * @param firstTeeTime
	 * @param competitionScoringSystem
	 * @param roundNumberMap
	 */
	public Competition(String name, ICompetitionType competitionScoringSystem,
			String competitionVisibility) {
		super(name, 0);
		this.competitionScoringSystem = competitionScoringSystem;
		this.competitionVisibility = competitionVisibility;
		this.roundNumberMap = new HashMap<Integer, ICompetition.ICompetitionRound>(
				DEFAULT_ROUND_NUMBER);
		this.roundDateMap = new HashMap<Date, ICompetition.ICompetitionRound>(
				DEFAULT_ROUND_NUMBER);
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionId()
	 */
	@Override
	public int getCompetitionId() {
		return this.getId();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionIdAsString()
	 */
	@Override
	public String getCompetitionIdAsString() {
		return String.valueOf(this.getId());
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionName()
	 */
	public String getCompetitionName() {
		return this.getName();
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionScoringSystem()
	 */
	public String getCompetitionScoringSystemName() {
		return competitionScoringSystem.getName();
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionScoringSystem()
	 */
	public ICompetitionType getCompetitionScoringSystem() {
		return competitionScoringSystem;
	}

	/**
	 * @return the competitionVisibility
	 */
	public String getCompetitionVisibility() {
		return competitionVisibility;
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionRoundList()
	 */
	public List<ICompetitionRound> getCompetitionRoundList() {
		ICompetitionRound[] competitionRoundArray = new ICompetitionRound[roundNumberMap
				.size()];
		return CollectionUtils.asList(roundNumberMap.values().toArray(
				competitionRoundArray));
	}

	/**
	 * 
	 * @author John Gaffney (john@gffny.com) Aug 21, 2012
	 */
	public void addCompetitionRound(ICompetitionRound round) {
		if (round != null && getRoundNumberMap() != null
				&& getRoundDateMap() != null) {
			if (CompetitionFunction.checkCompetitionRoundType(this, round)) {
				round.setCompetitionId(getCompetitionId());
				this.roundNumberMap.put(
						getRoundIndexFromNumber(round.getRoundNumber()), round);
				this.roundDateMap.put(round.getRoundDate(), round);
			}
			// TODO handle if rounds are not compatible
		}
		// TODO handle newRound being null
		// TODO handle roundNumberMap or roundDateMap null
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#addCompetitionRoundList(java.util.List)
	 */
	public void addCompetitionRoundList(
			List<ICompetitionRound> competitionRoundList) {
		for (ICompetitionRound round : competitionRoundList) {
			addCompetitionRound(round);
		}
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition#getRoundByDate(java.util.Date)
	 */
	@Override
	public ICompetitionRound getRoundByDate(Date roundDate) {
		return roundDateMap.get(roundDate);
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition#getRoundByNumber(int)
	 */
	@Override
	public ICompetitionRound getRoundByNumber(int roundNumber) {
		return this.roundNumberMap.get(getRoundIndexFromNumber(roundNumber));
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition#isIndividualCompetition()
	 */
	@Override
	public boolean isIndividualCompetition() {
		// TODO implement meaningfully based on competition type
		// return TRUE or FALSE
		return true;
	}

	/**
	 * 
	 * @param roundNumber
	 * @return
	 */
	private int getRoundIndexFromNumber(int roundNumber) {
		// TODO error check here for numbers > 4 && < 1 and throw an error
		return roundNumber - 1;
	}

	/**
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 2;
		return "Competition [name="
				+ this.getName()
				+ ", competitionScoringSystem="
				+ competitionScoringSystem
				+ ", roundList="
				+ (roundNumberMap != null ? getCompetitionRoundList().subList(
						0, Math.min(roundNumberMap.size(), maxLen)) : null)
				+ "]";
	}

	/**
	 * @return the roundNumberMap
	 */
	private Map<Integer, ICompetition.ICompetitionRound> getRoundNumberMap() {
		// check if the map is null and handle appropriately
		if (roundNumberMap != null) {
			return roundNumberMap;
		} else {
			roundNumberMap = new HashMap<Integer, ICompetition.ICompetitionRound>();
			return roundNumberMap;
		}
	}

	/**
	 * @return the roundDateMap
	 */
	private Map<Date, ICompetition.ICompetitionRound> getRoundDateMap() {
		// check if the map is null and handle appropriately
		if (roundDateMap != null) {
			return roundDateMap;
		} else {
			roundDateMap = new HashMap<Date, ICompetition.ICompetitionRound>();
			return roundDateMap;
		}
	}
}
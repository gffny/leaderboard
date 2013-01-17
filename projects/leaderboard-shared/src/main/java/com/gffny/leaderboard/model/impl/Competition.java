/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.util.CollectionUtils;
import com.gffny.leaderboard.util.CompetitionFunction;

/**
 * @author John Gaffney (john@gffny.com) Aug 21, 2012
 * 
 */
public class Competition extends Entity implements ICompetition {

	private String competitionScoringSystem;
	private String competitionVisibility;
	private Map<Integer, ICompetition.ICompetitionRound> roundNumberMap;
	private Map<Date, ICompetition.ICompetitionRound> roundDateMap;

	/**
	 * @param name
	 * @param date
	 * @param firstTeeTime
	 * @param competitionScoringSystem
	 * @param roundNumberMap
	 */
	public Competition(int competitionId, String name,
			String competitionScoringSystem, String competitionVisibility,
			int numberOfRounds) {
		super(name);
		this.setId(competitionId);
		this.competitionScoringSystem = competitionScoringSystem;
		this.competitionVisibility = competitionVisibility;
		this.roundNumberMap = new HashMap<Integer, ICompetition.ICompetitionRound>(
				numberOfRounds);
		this.roundDateMap = new HashMap<Date, ICompetition.ICompetitionRound>(
				numberOfRounds);
	}

	/**
	 * @param name
	 * @param date
	 * @param firstTeeTime
	 * @param competitionScoringSystem
	 * @param roundNumberMap
	 */
	public Competition(String name, String competitionScoringSystem,
			String competitionVisibility, int numberOfRounds) {
		super(name);
		this.competitionScoringSystem = competitionScoringSystem;
		this.competitionVisibility = competitionVisibility;
		this.roundNumberMap = new HashMap<Integer, ICompetition.ICompetitionRound>(
				numberOfRounds);
		this.roundDateMap = new HashMap<Date, ICompetition.ICompetitionRound>(
				numberOfRounds);
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
	public String getCompetitionScoringSystem() {
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
		ICompetitionRound[] competitionRoundArray = new CompetitionRound[roundNumberMap
				.size()];
		return CollectionUtils.asList(roundNumberMap.values().toArray(
				competitionRoundArray));
	}

	/**
	 * 
	 * @author John Gaffney (john@gffny.com) Aug 21, 2012
	 */
	public void addCompetitionRound(ICompetitionRound newRound) {
		if (newRound != null && roundNumberMap != null && roundDateMap != null) {
			if (CompetitionFunction.checkCompetitionRoundType(this, newRound)) {
				this.roundNumberMap.put(
						getRoundIndexFromNumber(newRound.getRoundNumber()),
						newRound);
				this.roundDateMap.put(newRound.getRoundDate(), newRound);
			}
			// TODO handle if rounds are not compatible
		}
		// TODO handle newRound being null
		// TODO handle roundNumberMap or roundDateMap null
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
}
/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.util.CompetitionFunction;

/**
 * @author John Gaffney (john@gffny.com) Aug 21, 2012
 * 
 */
public class Competition implements ICompetition {

	private String name;
	private String competitionTypeName;
	private List<ICompetition.ICompetitionRound> roundNumberList;
	private Map<Date, ICompetition.ICompetitionRound> roundDateMap;

	/**
	 * @param name
	 * @param date
	 * @param firstTeeTime
	 * @param competitionTypeName
	 * @param roundNumberList
	 */
	public Competition(String name, String competitionTypeName,
			int numberOfRounds) {
		this.name = name;
		this.competitionTypeName = competitionTypeName;
		this.roundNumberList = new ArrayList<ICompetition.ICompetitionRound>(
				numberOfRounds);
		this.roundDateMap = new HashMap<Date, ICompetition.ICompetitionRound>(
				numberOfRounds);
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionName()
	 */
	public String getCompetitionName() {
		return name;
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionTypeName()
	 */
	public String getCompetitionTypeName() {
		return competitionTypeName;
	}

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionRoundList()
	 */
	public List<ICompetitionRound> getCompetitionRoundList() {
		return roundNumberList;
	}

	/**
	 * 
	 * @author John Gaffney (john@gffny.com) Aug 21, 2012
	 */
	public void addCompetitionRound(ICompetitionRound newRound) {
		if (newRound != null && roundNumberList != null && roundDateMap != null) {
			if (CompetitionFunction.checkCompetitionRoundType(this, newRound)) {
				this.roundNumberList.add(
						getRoundIndexFromNumber(newRound.getRoundNumber()),
						newRound);
				this.roundDateMap.put(newRound.getRoundDate(), newRound);
			}
			// TODO handle if rounds are not compatible
		}
		// TODO handle newRound being null
		// TODO handle roundNumberList or roundDateMap null
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
		return this.roundNumberList.get(getRoundIndexFromNumber(roundNumber));
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
				+ name
				+ ", competitionTypeName="
				+ competitionTypeName
				+ ", roundList="
				+ (roundNumberList != null ? roundNumberList.subList(0,
						Math.min(roundNumberList.size(), maxLen)) : null) + "]";
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionId()
	 */
	@Override
	public int getCompetitionId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
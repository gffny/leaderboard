/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.ICompetitionType;

/**
 * @author John Gaffney (john@gffny.com) Aug 21, 2012
 * 
 */
public class CompetitionType implements ICompetitionType {

	/**
	 * @param name
	 * @param groupSize
	 * @param isMultiRound
	 * @param numberOfRounds
	 */
	public CompetitionType(String name, int groupSize, boolean isMultiRound,
			int numberOfRounds) {
		this.name = name;
		this.groupSize = groupSize;
		this.isMultiRound = isMultiRound;
		this.numberOfRounds = numberOfRounds;
	}

	private String name;
	private int groupSize;
	private boolean isMultiRound;
	private int numberOfRounds;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetitionType#getCompetitionTypeName()
	 */
	@Override
	public String getCompetitionTypeName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetitionType#getGroupSize()
	 */
	@Override
	public int getGroupSize() {
		return groupSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetitionType#getGroupName()
	 */
	@Override
	public String getGroupName() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetitionType#isMultiRound()
	 */
	@Override
	public boolean isMultiRound() {

		return isMultiRound;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetitionType#getNumberOfRounds()
	 */
	@Override
	public int getNumberOfRounds() {
		return numberOfRounds;
	}

}

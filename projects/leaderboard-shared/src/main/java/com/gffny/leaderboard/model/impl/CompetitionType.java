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
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return the scheduler
	 */
	@Override
	public String getScheduler() {
		return scheduler;
	}

	/**
	 * @return the scorer
	 */
	@Override
	public String getScorer() {
		return scorer;
	}

	/**
	 * @return the isTeam
	 */
	@Override
	public boolean isTeam() {
		return isTeam;
	}

	/**
	 * @return the isPair
	 */
	@Override
	public boolean isPair() {
		return isPair;
	}

	/**
	 * @return the isIndividual
	 */
	@Override
	public boolean isIndividual() {
		return isIndividual;
	}

	/**
	 * @param name
	 * @param scheduler
	 * @param scorer
	 * @param isTeam
	 * @param isPair
	 * @param isIndividual
	 */
	public CompetitionType(String name, String scheduler, String scorer,
			boolean isTeam, boolean isPair, boolean isIndividual) {
		this.name = name;
		this.scheduler = scheduler;
		this.scorer = scorer;
		this.isTeam = isTeam;
		this.isPair = isPair;
		this.isIndividual = isIndividual;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompetitionType [name=" + name + ", scheduler=" + scheduler
				+ ", scorer=" + scorer + ", isTeam=" + isTeam + ", isPair="
				+ isPair + ", isIndividual=" + isIndividual + "]";
	}

	private String name;
	private String scheduler;
	private String scorer;
	private boolean isTeam;
	private boolean isPair;
	private boolean isIndividual;
}

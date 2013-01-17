/**
 * 
 */
package com.gffny.leaderboard.model;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 14, 2013
 * 
 */
public interface ICompetitionType extends IEntity {

	/**
	 * 
	 * @return
	 */
	public boolean isIndividual();

	/**
	 * 
	 * @return
	 */
	public boolean isPair();

	/**
	 * 
	 * @return
	 */
	public boolean isTeam();

	/**
	 * 
	 * @return
	 */
	public String getScorer();

	/**
	 * 
	 * @return
	 */
	public String getScheduler();
}

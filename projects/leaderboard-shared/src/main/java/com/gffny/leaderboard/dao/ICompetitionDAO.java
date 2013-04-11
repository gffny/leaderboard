/**
 * 
 */
package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetitionType;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public interface ICompetitionDAO {

	/**
	 * @param competitionId
	 */
	public ICompetition getCompetitionById(int competitionId);

	/**
	 * 
	 * @param competition
	 * @return
	 * @throws DAOException
	 */
	public DAOResult saveCompetition(ICompetition competition)
			throws DAOException;

	/**
	 * @param competitionRoundToSave
	 * @return
	 * @throws DAOException
	 */
	public DAOResult saveCompetitionRound(
			ICompetitionRound competitionRoundToSave) throws DAOException;

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<ICompetitionType> getCompetitionTypeList() throws DAOException;

	/**
	 * @param competitionTypeId
	 * @return
	 */
	public ICompetitionType getCompetitionTypeById(int competitionTypeId);

	/**
	 * 
	 * @param competitionTypeName
	 * @return
	 */
	public ICompetitionType getCompetitionTypeByName(String competitionTypeName);

	/**
	 * 
	 * @param competitionName
	 * @return
	 */
	public boolean isExistingCompetitionName(String competitionName);

}

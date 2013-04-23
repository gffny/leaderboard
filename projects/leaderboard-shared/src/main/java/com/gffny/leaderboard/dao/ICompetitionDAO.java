/**
 * 
 */
package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.IDAOResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;

/**
 * 
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public interface ICompetitionDAO {

	/* COMPETITION */
	/**
	 * @param competitionId
	 */
	public ICompetition getCompetitionById(int competitionId)
			throws DAOException;

	/**
	 * 
	 * @param competition
	 * @return
	 * @throws DAOException
	 */
	public IDAOResult saveCompetition(ICompetition competition)
			throws DAOException;

	/**
	 * 
	 * @param competitionName
	 * @return
	 */
	public boolean isExistingCompetitionName(String competitionName)
			throws DAOException;

	/**
	 * @param userId
	 * @return
	 */
	public List<ICompetition> getCompetitionListForUserId(String userId)
			throws DAOException;

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<ICompetition> getEnteredCompetitionListForUser(String userId)
			throws DAOException;

	/* COMPETITION TYPE */
	/**
	 * @param competitionTypeId
	 * @return
	 */
	public ICompetitionType getCompetitionTypeById(int competitionTypeId)
			throws DAOException;

	/**
	 * 
	 * @param competitionTypeName
	 * @return
	 */
	public ICompetitionType getCompetitionTypeByName(String competitionTypeName)
			throws DAOException;

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<ICompetitionType> getCompetitionTypeList() throws DAOException;
}

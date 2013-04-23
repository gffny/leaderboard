package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.IDAOResult;
import com.gffny.leaderboard.model.IGolfer;

public interface IUserDAO {

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<IGolfer> getAllGolfersList() throws DAOException;

	/**
	 * 
	 * @param societyId
	 * @return
	 * @throws DAOException
	 */
	public List<IGolfer> getAllSocietyMembersListBySocietyId(String societyId)
			throws DAOException;

	/**
	 * 
	 * @param societyMemberId
	 * @return
	 * @throws DAOException
	 */
	public IGolfer getGolferBySocietyMemberId(String societyMemberId)
			throws DAOException;

	/**
	 * 
	 * @param userName
	 * @return
	 * @throws DAOException
	 */
	public IGolfer getGolferByHandle(String profileHandle) throws DAOException;

	/**
	 * 
	 * @param intGolferId
	 * @return
	 * @throws DAOException
	 */
	public IGolfer getGolferById(int intGolferId) throws DAOException;

	/**
	 * 
	 * @param emailAddress
	 * @param profileHandle
	 * @param firstName
	 * @param lastName
	 * @return
	 * @throws DAOException
	 */
	public IDAOResult addGolfer(String emailAddress, String profileHandle,
			String firstName, String lastName) throws DAOException;

	/**
	 * 
	 * @param emailAddress
	 * @return
	 * @throws DAOException
	 */
	public boolean isRegisteredEmailAddress(String emailAddress)
			throws DAOException;

	/**
	 * 
	 * @param profileHandle
	 * @return
	 * @throws DAOException
	 */
	public boolean isRegisteredProfileHandle(String profileHandle)
			throws DAOException;

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public IDAOResult updateProfilePicture() throws DAOException;

	/**
	 * 
	 * @param userId
	 * @param question
	 * @param answer
	 * @return
	 * @throws DAOException
	 */
	public IDAOResult updateSecurityQuestion(String userId, String question,
			String answer) throws DAOException;
}

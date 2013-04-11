package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.model.IGolfer;

public interface IUserDAO {

	public List<IGolfer> getAllGolfersList() throws DAOException;

	public List<IGolfer> getAllSocietyMembersListBySocietyId(String societyId)
			throws DAOException;

	public IGolfer getGolferBySocietyMemberId(String societyMemberId)
			throws DAOException;

	public IGolfer getGolferByHandle(String userName) throws DAOException;

	public DAOResult addGolfer(String emailAddress, String profileHandle,
			String firstName, String lastName) throws DAOException;

	public boolean isRegisteredEmailAddress(String emailAddress)
			throws DAOException;

	public boolean isRegisteredProfileHandle(String profileHandle)
			throws DAOException;

}

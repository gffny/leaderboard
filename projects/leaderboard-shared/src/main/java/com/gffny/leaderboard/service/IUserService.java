package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;

public interface IUserService {

	/**
	 * 
	 * @param societyId
	 * @return
	 * @throws ServiceException
	 */
	public List<IGolfer> getAllSocietyMembers(String societyId)
			throws ServiceException;

	/**
	 * 
	 * @param societyMemberId
	 * @return
	 * @throws ServiceException
	 */
	public IGolfer getGolferBySocietyMemberId(String societyMemberId)
			throws ServiceException;

	/**
	 * @param parameter
	 * @return
	 */
	public List<IGolfer> getSocietyMemberListAssociatedWithUser(
			String societyMemberId) throws ServiceException;

	public boolean isGolferActive(int userId) throws ServiceException;

	/**
	 * @param golferId
	 * @return
	 */
	public IGolfer getGolferById(String golferId) throws ServiceException;

	/**
	 * @param userId
	 */
	public String getGolferHomeCity(String userId) throws ServiceException;

	/**
	 * @param userId
	 */
	public List<String> getGolferFavouriteCourseList(String userId)
			throws ServiceException;

	/**
	 * 
	 * @param username
	 * @return
	 */
	public IGolfer getGolferByHandle(String username) throws ServiceException;
}
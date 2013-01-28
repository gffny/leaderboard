/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.IUserDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author jgaffney02
 * 
 */
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDao;

	/**
	 * 
	 */
	private static IUserService INSTANCE = null;

	/**
	 * 
	 * @return
	 */
	public static IUserService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserService();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	private UserService() {

	}

	/**
	 *
	 */
	public List<IGolfer> getAllSocietyMembers(String societyId)
			throws ServiceException {

		// handle shtuff here!
		try {
			return userDao.getAllSocietyMembersListBySocietyId(societyId);
		} catch (DAOException e) {
			// TODO handle exception
		}
		return new ArrayList<IGolfer>();
	}

	/**
	 * @author John Gaffney (john@gffny.com)
	 * 
	 */
	public IGolfer getGolferBySocietyMemberId(String societyMemberId)
			throws ServiceException {
		// handle shtuff here!
		try {
			return userDao.getGolferBySocietyMemberId(societyMemberId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getSocietyMemberListAssociatedWithUser(java.lang.String)
	 */
	@Override
	public List<IGolfer> getSocietyMemberListAssociatedWithUser(
			String societyMemberId) throws ServiceException {
		// TODO Auto-generated method stub
		return new ArrayList<IGolfer>();
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#isGolferActive(int)
	 */
	@Override
	public boolean isGolferActive(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferById(java.lang.String)
	 */
	@Override
	public IGolfer getGolferById(String golferId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferHomeCity(java.lang.String)
	 */
	@Override
	public String getGolferHomeCity(String userId) throws ServiceException {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferFavouriteClub(java.lang.String)
	 */
	@Override
	public List<IGolfCourse> getGolferFavouriteClub(String userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return new ArrayList<IGolfCourse>();
	}
}

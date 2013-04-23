/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.IEquipmentDAO;
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

	private Logger log = Logger.getLogger(UserService.class);

	@Autowired
	private IUserDAO userDao;

	@Autowired
	private IEquipmentDAO equipmentDAO;

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
			IGolfer golfer = userDao
					.getGolferBySocietyMemberId(societyMemberId);
			golfer.setGolfBag(equipmentDAO.getGolfBagByUserId(golfer
					.getUserId()));
			return golfer;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getSocietyMemberListAssociatedWithUser(java.lang.String)
	 */
	public List<IGolfer> getSocietyMemberListAssociatedWithUser(
			String societyMemberId) throws ServiceException {
		// TODO Auto-generated method stub
		return new ArrayList<IGolfer>();
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#isGolferActive(int)
	 */
	public boolean isGolferActive(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferById(java.lang.String)
	 */
	public IGolfer getGolferById(String golferId) {
		if (golferId != null) {
			try {
				IGolfer golfer = userDao.getGolferById(Integer
						.parseInt(golferId));
				golfer.setGolfBag(equipmentDAO.getGolfBagByUserId(golfer
						.getUserId()));
				return golfer;
			} catch (DAOException daoEx) {
				log.error(daoEx.getMessage());
			} catch (NumberFormatException numFormEx) {
				log.error(numFormEx.getMessage());
			}
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferHomeCity(java.lang.String)
	 */
	public String getGolferHomeCity(String userId) throws ServiceException {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * @see com.gffny.leaderboard.service.IUserService#getGolferFavouriteClub(java.lang.String)
	 */
	public List<IGolfCourse> getGolferFavouriteClub(String userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return new ArrayList<IGolfCourse>();
	}

	/**
	 * @throws ServiceException
	 * @see com.gffny.leaderboard.service.IUserService#getGolferByHandle(java.lang.String)
	 */
	public IGolfer getGolferByHandle(String username) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			IGolfer golfer = userDao.getGolferByHandle(username);
			golfer.setGolfBag(equipmentDAO.getGolfBagByUserId(golfer
					.getUserId()));
			return golfer;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

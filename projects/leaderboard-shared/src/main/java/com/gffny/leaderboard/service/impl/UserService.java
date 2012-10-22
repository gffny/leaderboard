/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.dao.factory.DAOFactory;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author jgaffney02
 * 
 */
public class UserService implements IUserService {

	/**
	 *
	 */
	public List<IGolfer> getAllSocietyMembers(String societyId)
			throws ServiceException {

		// handle shtuff here!
		try {
			return DAOFactory.getInstance().getUserDAO()
					.getAllSocietyMembersListBySocietyId(societyId);
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
			return DAOFactory.getInstance().getUserDAO()
					.getGolferBySocietyMemberId(societyMemberId);
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
}

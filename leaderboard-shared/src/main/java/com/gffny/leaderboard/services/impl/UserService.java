/**
 * 
 */
package com.gffny.leaderboard.services.impl;

import java.util.List;

import com.gffny.leaderboard.dao.mysql.DAOFactory;
import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.layerUtils.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.services.IUserService;

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
		
		//handle shtuff here!
		try {
			return DAOFactory.getInstance().getUserDAO().getAllSocietyMembersListBySocietyId(societyId);
		} catch (DAOException e) {
			//TODO handle exception
		}
		return null;
	}

}

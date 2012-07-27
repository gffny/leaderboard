/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.IUserDAO;
import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.layerUtils.DAOResult;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.Golfer;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * @author 
 *
 */
public class UserDAO extends AbstractMySQLDAO implements IUserDAO {
	
	private static Logger log = Logger.getLogger(AbstractMySQLDAO.class);

	private PreparedStatement stmnt;

	protected UserDAO() {
		super();
	}

	/**
	 * 
	 */
	public List<IGolfer> getAllGolfersList() throws DAOException {
		List<IGolfer> golferList = new ArrayList<IGolfer>(); 
		try {
			//create statement to select all the golfer and society information from the database
			stmnt = getConnection().prepareStatement("SELECT * FROM t_user u INNER JOIN t_society_membership sm ON u.user_id = sm.user_id");
			ResultSet res = stmnt.executeQuery();
			//traverse the results creating a List of <IGolfer> to return
			while(res.next()) {
				golferList.add(
						new Golfer(res.getInt("USER_ID"), 
								res.getInt("GOLF_SOCIETY_ID"), 
								res.getString("PROFILE_HANDLE"), 
								res.getString("EMAIL_ADDRESS"), 
								res.getString("FIRST_NAME"), 
								res.getString("SURNAME"),
								res.getString("LOCATION"),
								res.getString("HANDICAP") 
								));
					
			}
		} catch (SQLException sqlEx) {
			log.error(sqlEx.getMessage());
			throw new DAOException(sqlEx.getMessage());
		}
		return golferList;
	}

	/**
	 *
	 */
	public List<IGolfer> getAllSocietyMembersListBySocietyId(String societyId) throws DAOException {
		List<IGolfer> golferList = new ArrayList<IGolfer>(); 
		try {
			//create statement to select golfer and society information from the database for golfers with a society id of societyId 
			stmnt = getConnection().prepareStatement("SELECT * FROM t_user u INNER JOIN t_society_membership sm ON u.user_id = sm.user_id WHERE sm.golf_society_id = ?");
			stmnt.setString(1, societyId);
			ResultSet res = stmnt.executeQuery();
			//traverse the results creating a List of <IGolfer> to return
			while(res.next()) {
				golferList.add(new Golfer(res.getInt("USER_ID"), 
						res.getInt("GOLF_SOCIETY_ID"), 
						res.getString("PROFILE_HANDLE"), 
						res.getString("EMAIL_ADDRESS"), 
						res.getString("FIRST_NAME"), 
						res.getString("SURNAME"),
						res.getString("LOCATION"),
						res.getString("HANDICAP") 
						));
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return golferList;
	}

	/**
	 * 
	 */
	public IGolfer getGolferBySocietyMemberId(String societyMemberId) throws DAOException {
		try {
			//create statement to select golfer and society information from the database for the with a society member id of societyMemberId 
			stmnt = getConnection().prepareStatement("SELECT * FROM t_user u INNER JOIN t_society_membership sm ON u.user_id = sm.user_id WHERE sm.society_member_id = ?");
			stmnt.setString(1, societyMemberId);
			ResultSet res = stmnt.executeQuery();
			//traverse the results creating a List of <IGolfer> to return
			if(res.next()) {
				return new Golfer(res.getInt("USER_ID"), 
						res.getInt("GOLF_SOCIETY_ID"), 
						res.getString("PROFILE_HANDLE"), 
						res.getString("EMAIL_ADDRESS"), 
						res.getString("FIRST_NAME"), 
						res.getString("SURNAME"),
						res.getString("LOCATION"),
						res.getString("HANDICAP") 
						);
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		throw new DAOException("No Golfer with those credentials was found.");
	}

	/**
	 * 
	 */
	public DAOResult addGolfer(String emailAddress, String profileHandle, String firstName, String lastName) throws DAOException {
		//TODO validate email address, profile handle, etc
		try {
			//create statement to insert a new golfer into the database
			stmnt = getConnection().prepareStatement("INSERT INTO t_user(email_address, profile_handle, first_name, surname) VALUES(?, ?, ?, ?);");
			stmnt.setString(1, emailAddress);
			stmnt.setString(2, profileHandle);
			stmnt.setString(3, firstName);
			stmnt.setString(4, lastName);
			//return if the insert is successful or not (an exception should be thrown if it's not)
			//TODO think what should be passed back here!
			return new DAOResult(stmnt.execute());
		} catch (MySQLIntegrityConstraintViolationException constraintEx) {
			//TODO Handle the error appropriately
			log.error("a constraint has been violated "+constraintEx.getMessage());
			throw new DAOException(constraintEx.getMessage());
		} catch (SQLException sqlEx) {
			log.error(sqlEx.getMessage());
			throw new DAOException(sqlEx.getMessage());
		}
	}

	/**
	 * 
	 */
	public boolean isRegisteredEmailAddress(String emailAddress) throws DAOException {
		try {
			//create statement to select the golfer with a an email address corresponding to emailAddress
			stmnt = getConnection().prepareStatement("SELECT * FROM t_user u WHERE email_address = ?");
			stmnt.setString(1, emailAddress);
			ResultSet res = stmnt.executeQuery();
			//return true if there is a result (i.e. a golfer with a corresponding email)
			if(res.next()) {
				return true;
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return false;
	}

	/**
	 * 
	 */
	public boolean isRegisteredProfileHandle(String profileHandle) throws DAOException {
		try {
			//create statement to select the golfer with a an profile handle corresponding to profileHandle
			stmnt = getConnection().prepareStatement("SELECT * FROM t_user u WHERE profile_handle = ?");
			stmnt.setString(1, profileHandle);
			ResultSet res = stmnt.executeQuery();
			//return true if there is a result (i.e. a golfer with a corresponding email)
			if(res.next()) {
				return true;
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return false;
	}
}

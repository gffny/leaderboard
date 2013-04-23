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
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.intralayer.IDAOResult;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.Golfer;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * @author
 * 
 */
public class UserDAO extends AbstractMySQLDAO implements IUserDAO {

	private static Logger log = Logger.getLogger(UserDAO.class);
	private static String USER_ID_COL = "usr_id";
	private static String GOLF_SOCIETY_ID_COL = "glf_scty_id";
	private static String PROFILE_HANDLE_COL = "prfl_nm";
	private static String EMAIL_ADDRESS_COL = "eml_addrss";
	private static String FIRST_NAME_COL = "frst_nm";
	private static String LAST_NAME_COL = "lst_nm";
	private static String lOCATION_COL = "lctn";
	private static String HANDICAP_COL = "hndcp";
	private static String SOCIETY_MEMBERSHIP_ID_COL = "scty_mmbr_id";
	private static final String HANDEDNESS_COL = "hnddnss";

	/**
	 * @see com.gffny.leaderboard.dao.IUserDAO#getGolferByHandle(java.lang.String)
	 */
	public IGolfer getGolferByHandle(String profileHandle) throws DAOException {
		try {
			// create statement to select the golfer with a an profile handle
			// corresponding to profileHandle
			PreparedStatement stmnt = getConnection().prepareStatement(
					"SELECT * FROM t_usr u WHERE " + PROFILE_HANDLE_COL
							+ " = ?");
			stmnt.setString(1, profileHandle);
			ResultSet res = stmnt.executeQuery();
			// return true if there is a result (i.e. a golfer with a
			// corresponding email)
			if (res.next()) {
				return mapGolfer(res);
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.dao.IUserDAO#getGolferById(int)
	 */
	public IGolfer getGolferById(int golferId) throws DAOException {
		try {
			// create statement to select the golfer with a an profile handle
			// corresponding to profileHandle
			PreparedStatement stmnt = getConnection().prepareStatement(
					"SELECT * FROM t_usr u WHERE " + USER_ID_COL + " = ?");
			stmnt.setInt(1, golferId);
			ResultSet res = stmnt.executeQuery();
			// return true if there is a result (i.e. a golfer with a
			// corresponding email)
			if (res.next()) {
				return mapGolfer(res);
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return null;
	}

	/**
	 * 
	 */
	public IGolfer getGolferBySocietyMemberId(String societyMemberId)
			throws DAOException {
		try {
			// create statement to select golfer and society information from
			// the database for the with a society member id of societyMemberId
			PreparedStatement stmnt = getConnection()
					.prepareStatement(
							"SELECT * FROM t_usr u INNER JOIN t_scty_mmbrshp sm ON u."
									+ USER_ID_COL + " = sm." + USER_ID_COL
									+ " WHERE sm." + SOCIETY_MEMBERSHIP_ID_COL
									+ " = ?");
			stmnt.setString(1, societyMemberId);
			ResultSet res = stmnt.executeQuery();
			// traverse the results creating a List of <IGolfer> to return
			if (res.next()) {
				return mapSocietyGolfer(res);
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		throw new DAOException("No Golfer with those credentials was found.");
	}

	/**
	 * 
	 */
	public List<IGolfer> getAllGolfersList() throws DAOException {
		List<IGolfer> golferList = new ArrayList<IGolfer>();
		try {
			// create statement to select all the golfer and society information
			// from the database
			PreparedStatement stmnt = getConnection().prepareStatement(
					"SELECT * FROM t_usr u;");
			ResultSet res = stmnt.executeQuery();
			// traverse the results creating a List of <IGolfer> to return
			while (res.next()) {
				golferList.add(mapGolfer(res));
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
	public List<IGolfer> getAllSocietyMembersListBySocietyId(String societyId)
			throws DAOException {
		List<IGolfer> golferList = new ArrayList<IGolfer>();
		try {
			// create statement to select golfer and society information from
			// the database for golfers with a society id of societyId
			PreparedStatement stmnt = getConnection().prepareStatement(
					"SELECT * FROM t_usr u INNER JOIN t_scty_mmbrshp sm ON u."
							+ USER_ID_COL + " = sm." + USER_ID_COL
							+ " WHERE sm." + GOLF_SOCIETY_ID_COL + " = ?");
			stmnt.setString(1, societyId);
			ResultSet res = stmnt.executeQuery();
			// traverse the results creating a List of <IGolfer> to return
			while (res.next()) {
				golferList.add(mapSocietyGolfer(res));
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return golferList;
	}

	/**
	 * 
	 */
	public IDAOResult addGolfer(String emailAddress, String profileHandle,
			String firstName, String lastName) throws DAOException {
		// TODO validate email address, profile handle, etc
		// TODO add methods to handle the other data and call from userService
		// here
		try {
			// create statement to insert a new golfer into the database
			PreparedStatement stmnt = getConnection().prepareStatement(
					"INSERT INTO t_usr(" + EMAIL_ADDRESS_COL + ", "
							+ PROFILE_HANDLE_COL + ", " + FIRST_NAME_COL + ", "
							+ LAST_NAME_COL + ") VALUES(?, ?, ?, ?);");
			stmnt.setString(1, emailAddress);
			stmnt.setString(2, profileHandle);
			stmnt.setString(3, firstName);
			stmnt.setString(4, lastName);
			// return if the insert is successful or not (an exception should be
			// thrown if it's not)
			// TODO think what should be passed back here!
			return new DAOResult(stmnt.execute());
		} catch (MySQLIntegrityConstraintViolationException constraintEx) {
			// TODO Handle the error appropriately
			log.error("a constraint has been violated "
					+ constraintEx.getMessage());
			throw new DAOException(constraintEx.getMessage());
		} catch (SQLException sqlEx) {
			log.error(sqlEx.getMessage());
			throw new DAOException(sqlEx.getMessage());
		}
	}

	@Override
	public IDAOResult updateSecurityQuestion(String userId, String question,
			String answer) throws DAOException {

		// TODO implement this method: updateSecurityQuestion
		return null;
	}

	@Override
	public IDAOResult updateProfilePicture() throws DAOException {

		// TODO implement this method: updateProfilePicture
		return null;
	}

	/**
	 * 
	 */
	public boolean isRegisteredEmailAddress(String emailAddress)
			throws DAOException {
		try {
			// create statement to select the golfer with a an email address
			// corresponding to emailAddress
			PreparedStatement stmnt = getConnection()
					.prepareStatement(
							"SELECT * FROM t_usr u WHERE " + EMAIL_ADDRESS_COL
									+ " = ?");
			stmnt.setString(1, emailAddress);
			ResultSet res = stmnt.executeQuery();
			// return true if there is a result (i.e. a golfer with a
			// corresponding email)
			if (res.next()) {
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
	public boolean isRegisteredProfileHandle(String profileHandle)
			throws DAOException {
		try {
			// create statement to select the golfer with a an profile handle
			// corresponding to profileHandle
			PreparedStatement stmnt = getConnection().prepareStatement(
					"SELECT * FROM t_usr u WHERE " + PROFILE_HANDLE_COL
							+ " = ?");
			stmnt.setString(1, profileHandle);
			ResultSet res = stmnt.executeQuery();
			// return true if there is a result (i.e. a golfer with a
			// corresponding email)
			if (res.next()) {
				return true;
			}
		} catch (SQLException sqlEx) {
			throw new DAOException(sqlEx.getMessage());
		}
		return false;
	}

	/**
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	private IGolfer mapSocietyGolfer(ResultSet res) throws SQLException {
		IGolfer golfer = mapGolfer(res);
		golfer.setSocietyId(res.getInt(SOCIETY_MEMBERSHIP_ID_COL));
		return golfer;
	}

	/**
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	private IGolfer mapGolfer(ResultSet res) throws SQLException {

		if (res != null) {
			return new Golfer(res.getInt(USER_ID_COL), // userId
					res.getString(FIRST_NAME_COL), // firstName
					res.getString(LAST_NAME_COL), // lastName
					res.getString(PROFILE_HANDLE_COL), // profileHandle
					res.getString(EMAIL_ADDRESS_COL), // emailAddress
					res.getString(lOCATION_COL), // location
					res.getString(HANDEDNESS_COL), // handedness
					res.getInt(HANDICAP_COL)); // handicap
		} else {
			log.error("result set is null");
			throw new DAOException("result set is null");
		}
	}
}

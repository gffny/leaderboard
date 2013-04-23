/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gffny.leaderboard.dao.IEquipmentDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfBag;
import com.gffny.leaderboard.model.IGolfEquipment;
import com.gffny.leaderboard.model.impl.GolfBag;
import com.gffny.leaderboard.model.impl.GolfClub;

/**
 * @author John Gaffney (john@gffny.com) Apr 13, 2013
 * 
 */
@Component
public class EquipmentDAO extends AbstractMySQLDAO implements IEquipmentDAO, IDatabaseNames {

	// LOGGER
	private static Logger log = Logger.getLogger(EquipmentDAO.class);

	/**
	 * @see com.gffny.leaderboard.dao.IGolfBagDAO#getGolfBagByUserId(java.lang.String)
	 */
	public IGolfBag getGolfBagByUserId(int userId) throws DAOException {
		ResultSet rs = null;
		PreparedStatement stmnt = null;
		try {
			stmnt = getConnection().prepareStatement(
					"SELECT b." + BAG_ID_COL + ", c." + MANUFACTURER_NM_COL
							+ ", c." + CLUB_NM_COL + ", c." + CLUB_TY_COL
							+ ", c." + CLUB_CTGRY_COL + ", c." + CLUB_LFT_COL
							+ ", b." + DEFAULT_COL + ", b." + NOTES_COL
							+ " FROM " + USER_TABLE + " AS u, "
							+ USER_GOLF_BAG_TABLE + " AS b, " + EQUIPMENT_TABLE
							+ " AS c " + "WHERE u." + USER_ID_COL
							+ " = ? AND b." + USER_ID_COL + " = u."
							+ USER_ID_COL + " AND b." + CLUB_ID_COL + " = c."
							+ EQUIPMENT_ID_COL + ";");
			stmnt.setInt(1, userId);
			rs = stmnt.executeQuery();
			IGolfBag golfBag = new GolfBag(userId);
			// traverse the results populating the competitionType collections
			while (rs.next()) {
				log.debug("Processing Row: " + rs.getRow());
				IGolfEquipment golfClub = new GolfClub(rs.getInt("b."
						+ BAG_ID_COL),
						rs.getString("c." + MANUFACTURER_NM_COL),
						rs.getString("c." + CLUB_NM_COL), rs.getString("c."
								+ CLUB_TY_COL), rs.getString("c."
								+ CLUB_CTGRY_COL), rs.getString("c."
								+ CLUB_LFT_COL), rs.getBoolean("b."
								+ DEFAULT_COL), rs.getString("b." + NOTES_COL));
				golfBag.addClubToBag(golfClub);
			}
			return golfBag;
		} catch (SQLException sqlEx) {
			log.error(sqlEx.getMessage());
			try {
				rs.close();
				stmnt.close();
			} catch (SQLException sqlException) {
				log.error(sqlException.getMessage());
				throw new DAOException(sqlException.getMessage());
			}
			throw new DAOException(sqlEx.getMessage());
		}
	}
}

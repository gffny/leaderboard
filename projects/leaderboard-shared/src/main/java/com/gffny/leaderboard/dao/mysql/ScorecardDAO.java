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

import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IScorecard;
import com.gffny.leaderboard.model.impl.Scorecard;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 30, 2012
 *
 */
public class ScorecardDAO extends AbstractMySQLDAO implements IScorecardDAO {

	private static Logger log = Logger.getLogger(ScorecardDAO.class);

	private PreparedStatement stmnt;
	
	/**
	 * @throws ServiceException 
	 * 
	 */
	public List<IScorecard> getScorecardListForUser(String userId) throws ServiceException {
		log.debug("getting scorecard list for user "+userId);
		List<IScorecard> scorecardList = new ArrayList<IScorecard>(); 
		try {
			stmnt = getConnection().prepareStatement("SELECT * FROM t_scorecard WHERE user_id=? ORDER BY round_date DESC");
			stmnt.setString(1, userId);
			ResultSet res = stmnt.executeQuery();
			while(res.next()) {
				scorecardList.add(new Scorecard(
						res.getString("tee_played_off"), //String teesPlayedOff, 
						res.getString("hole_one_shots"), //String scoreHole1,
						res.getString("hole_two_shots"), //String scoreHole2,
						res.getString("hole_three_shots"), //String scoreHole3,
						res.getString("hole_four_shots"), //String scoreHole4,
						res.getString("hole_five_shots"), //String scoreHole5,
						res.getString("hole_six_shots"), //String scoreHole6,
						res.getString("hole_seven_shots"), //String scoreHole7,
						res.getString("hole_eight_shots"), //String scoreHole8,
						res.getString("hole_nine_shots"), //String scoreHole9,
						res.getString("hole_ten_shots"), //String scoreHole10,
						res.getString("hole_eleven_shots"), //String scoreHole11,
						res.getString("hole_twelve_shots"), //String scoreHole12,
						res.getString("hole_thirteen_shots"), //String scoreHole13,
						res.getString("hole_fourteen_shots"), //String scoreHole14,
						res.getString("hole_fifteen_shots"), //String scoreHole15,
						res.getString("hole_sixteen_shots"), //String scoreHole16,
						res.getString("hole_seventeen_shots"), //String scoreHole17,
						res.getString("hole_eighteen_shots"), //String scoreHole18,
						res.getString("round_notes"), //String scorecardNotes
						res.getString("round_date")
					));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return scorecardList;
	}

	/**
	 * 
	 */
	public List<IScorecard> getLatestScorecardForUser(String userId) throws ServiceException {
		log.debug("getting scorecard list for user "+userId);
		List<IScorecard> scorecardList = new ArrayList<IScorecard>(); 
		try {
			stmnt = getConnection().prepareStatement("SELECT * FROM t_scorecard WHERE user_id=? ORDER BY round_date DESC");
			stmnt.setString(1, userId);
			ResultSet res = stmnt.executeQuery();
			boolean firstScorecard = true;
			while(res.next() && firstScorecard) {
				scorecardList.add(new Scorecard(
						res.getString("tee_played_off"), //String teesPlayedOff, 
						res.getString("hole_one_shots"), //String scoreHole1,
						res.getString("hole_two_shots"), //String scoreHole2,
						res.getString("hole_three_shots"), //String scoreHole3,
						res.getString("hole_four_shots"), //String scoreHole4,
						res.getString("hole_five_shots"), //String scoreHole5,
						res.getString("hole_six_shots"), //String scoreHole6,
						res.getString("hole_seven_shots"), //String scoreHole7,
						res.getString("hole_eight_shots"), //String scoreHole8,
						res.getString("hole_nine_shots"), //String scoreHole9,
						res.getString("hole_ten_shots"), //String scoreHole10,
						res.getString("hole_eleven_shots"), //String scoreHole11,
						res.getString("hole_twelve_shots"), //String scoreHole12,
						res.getString("hole_thirteen_shots"), //String scoreHole13,
						res.getString("hole_fourteen_shots"), //String scoreHole14,
						res.getString("hole_fifteen_shots"), //String scoreHole15,
						res.getString("hole_sixteen_shots"), //String scoreHole16,
						res.getString("hole_seventeen_shots"), //String scoreHole17,
						res.getString("hole_eighteen_shots"), //String scoreHole18,
						res.getString("round_notes"), //String scorecardNotes
						res.getString("round_date")
					));
				firstScorecard = false;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (DAOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return scorecardList;
	}

	/**
	 * 
	 */
	public List<IScorecard> getLastXScorecardListForUser(String userId,
			int xNumberOfScorecards) throws ServiceException {
		log.debug("getting scorecard list for user "+userId);
		List<IScorecard> scorecardList = new ArrayList<IScorecard>(); 
		try {
			stmnt = getConnection().prepareStatement("SELECT * FROM t_scorecard WHERE user_id=? ORDER BY round_date DESC");
			stmnt.setString(1, userId);
			ResultSet res = stmnt.executeQuery();
			int scorecardCount = 0;
			while(res.next() && scorecardCount  < xNumberOfScorecards) {
				scorecardList.add(new Scorecard(
						res.getString("tee_played_off"), //String teesPlayedOff, 
						res.getString("hole_one_shots"), //String scoreHole1,
						res.getString("hole_two_shots"), //String scoreHole2,
						res.getString("hole_three_shots"), //String scoreHole3,
						res.getString("hole_four_shots"), //String scoreHole4,
						res.getString("hole_five_shots"), //String scoreHole5,
						res.getString("hole_six_shots"), //String scoreHole6,
						res.getString("hole_seven_shots"), //String scoreHole7,
						res.getString("hole_eight_shots"), //String scoreHole8,
						res.getString("hole_nine_shots"), //String scoreHole9,
						res.getString("hole_ten_shots"), //String scoreHole10,
						res.getString("hole_eleven_shots"), //String scoreHole11,
						res.getString("hole_twelve_shots"), //String scoreHole12,
						res.getString("hole_thirteen_shots"), //String scoreHole13,
						res.getString("hole_fourteen_shots"), //String scoreHole14,
						res.getString("hole_fifteen_shots"), //String scoreHole15,
						res.getString("hole_sixteen_shots"), //String scoreHole16,
						res.getString("hole_seventeen_shots"), //String scoreHole17,
						res.getString("hole_eighteen_shots"), //String scoreHole18,
						res.getString("round_notes"), //String scorecardNotes
						res.getString("round_date")
					));
				scorecardCount++;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (DAOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return scorecardList;
	}
}

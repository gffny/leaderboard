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
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 * 
 */
public class ScorecardDAO extends AbstractMySQLDAO implements IScorecardDAO,
		IDatabaseNames {

	/**
	 *
	 */
	private static final int MAX_NUM_OF_HOLES = 18;

	/**
	 * 
	 */
	static Logger log = Logger.getLogger(ScorecardDAO.class);

	/**
	 * 
	 */
	private PreparedStatement stmnt;

	/**
	 * @see com.gffny.leaderboard.dao.IScorecardDAO#getScorecardListForCompetitionByUserId(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<IScorecard> getScorecardListForCompetitionByUserId(
			String competitionId, String userId) throws DAOException {
		/*
		 * SELECT * FROM t_scrcrd s WHERE s.scrr_id = '1' AND s.cmpttn_rnd_id IN
		 * ( SELECT r.cmpttn_rnd_id FROM t_cmpttn c INNER JOIN t_cmpttn_rnd r ON
		 * c.cmpttn_id = r.cmpttn_id WHERE c.cmpttn_id = '2' );
		 */
		log.debug("getting scorecard list for user " + userId);
		List<IScorecard> scorecardList = new ArrayList<IScorecard>();
		try {
			stmnt = getConnection().prepareStatement(
					"SELECT * FROM " + SCORECARD_TABLE + " s WHERE s."
							+ MARKER_ID_COL + " = ? AND s."
							+ COMPETITION_ROUND_ID_COL + " IN ( SELECT r."
							+ COMPETITION_ROUND_ID_COL + " FROM "
							+ COMPETITION_TABLE + " c INNER JOIN "
							+ COMPETITION_ROUND_TABLE + " r ON c."
							+ COMPETITION_ID_COL + " = r." + COMPETITION_ID_COL
							+ " WHERE c." + COMPETITION_ID_COL + " = ? )");
			stmnt.setInt(1, Integer.parseInt(userId));
			stmnt.setInt(2, Integer.parseInt(competitionId));
			ResultSet res = stmnt.executeQuery();
			while (res.next()) {
				// load an instance of scorecard!
				scorecardList.add(mapScorecard(res));
			}
			return scorecardList;
		} catch (NumberFormatException nfe) {
			log.error(nfe.getMessage());
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws ServiceException
	 * 
	 */
	public List<IScorecard> getScorecardListForUser(String userId)
			throws DAOException {
		log.debug("getting scorecard list for user " + userId);
		List<IScorecard> scorecardList = new ArrayList<IScorecard>();
		try {
			stmnt = getConnection()
					.prepareStatement(
							"SELECT * FROM t_scorecard WHERE user_id=? ORDER BY round_date DESC");
			stmnt.setString(1, userId);
			ResultSet res = stmnt.executeQuery();
			while (res.next()) {

			}
		} catch (SQLException sqlEx) {
			log.error(sqlEx.getMessage());
			throw new DAOException(sqlEx.getMessage());
		}
		return scorecardList;
	}

	/**
	 * 
	 */
	public List<IScorecard> getLatestScorecardForUser(String userId)
			throws DAOException {
		log.debug("getting scorecard list for user " + userId);
		return null;
	}

	/**
	 * 
	 */
	public List<IScorecard> getLastXScorecardListForUser(String userId,
			int xNumberOfScorecards) throws DAOException {
		log.debug("getting scorecard list for user " + userId);
		List<IScorecard> scorecardList = new ArrayList<IScorecard>();
		try {
			stmnt = getConnection()
					.prepareStatement(
							"SELECT * FROM t_scorecard WHERE user_id=? ORDER BY round_date DESC");
			stmnt.setString(1, userId);
			ResultSet res = stmnt.executeQuery();
			int scorecardCount = 0;
			while (res.next() && scorecardCount < xNumberOfScorecards) {

				scorecardCount++;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return scorecardList;
	}

	/**
	 * @see com.gffny.leaderboard.dao.IScorecardDAO#submitScorecardForCompetitionRound(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	@Override
	public void submitScorecardForCompetitionRound(String competitionRoundId,
			String userId, String[] scoreArray) throws DAOException {
		try {
			stmnt = getConnection().prepareStatement("");

		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}

	/* HELPER METHODS */
	/**
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	private IScorecard mapScorecard(ResultSet res) {
		// new Scorecard(teesPlayedOff, handicap, grossScoreArray,
		// scorecardNotes, scorecardDate)
		return new Scorecard(getInt(res, SCORECARD_ID_COL), getInt(res,
				COMPETITION_ROUND_ID_COL), getInt(res, GOLFER_ID_COL), getInt(
				res, MARKER_ID_COL), getString(res, TEE_CLR_CD_COL), getInt(
				res, HANDICAP_COL), getScorecardHoleScoreArray(res), getString(
				res, NOTES_COL), getString(res, SCORECARD_DATE_COL));
	}

	/**
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	private int[] getScorecardHoleScoreArray(ResultSet res) {
		int hole = 1, holeScore = 0;
		boolean scorecardEndReached = false;
		int[] scoreArray = new int[MAX_NUM_OF_HOLES];
		while (hole <= MAX_NUM_OF_HOLES && !scorecardEndReached) {

			holeScore = getInt(res, "hl_" + hole);
			if (holeScore > 0) {
				scoreArray[(hole - 1)] = holeScore;
				hole++;
			} else {
				log.debug("scorecard value for hole " + hole + ": " + holeScore);
				scorecardEndReached = true;
			}

		}
		return scoreArray;
	}
}

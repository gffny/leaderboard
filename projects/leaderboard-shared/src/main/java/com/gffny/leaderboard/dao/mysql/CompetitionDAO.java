/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gffny.leaderboard.dao.ICompetitionDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.intralayer.IDAOResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.CompetitionType;
import com.gffny.leaderboard.util.DateUtils;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
@Component
public class CompetitionDAO extends AbstractMySQLDAO implements
		ICompetitionDAO, IDatabaseNames {

	private static Logger log = Logger.getLogger(CompetitionDAO.class);

	private Map<String, ICompetitionType> competitionTypeMap;

	private Map<Integer, ICompetitionType> competitionTypeIdMap;

	private List<ICompetitionType> competitionTypeList;

	/**
	 * 
	 */
	public CompetitionDAO() {
		competitionTypeMap = new HashMap<String, ICompetitionType>();
		competitionTypeIdMap = new HashMap<Integer, ICompetitionType>();
		competitionTypeList = new ArrayList<ICompetitionType>();
	}

	/* COMPETITION */
	/**
	 * 
	 * @throws SQLException
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#getCompetitionById(int)
	 */
	@Override
	public ICompetition getCompetitionById(int competitionId) {
		ResultSet rs = null;
		PreparedStatement stmnt = null;
		try {
			stmnt = getConnection().prepareStatement(
					"SELECT * FROM " + COMPETITION_TABLE + " WHERE "
							+ COMPETITION_ID_COL + " = ?;");
			stmnt.setInt(1, competitionId);
			rs = stmnt.executeQuery();

			// traverse the results populating the competitionType collections
			if (rs.next()) {
				log.debug("Processing Row: " + rs.getRow());
				ICompetition competition = mapCompetition(rs);
				// service layer will populate the round list from the database
				return competition;
			}
		} catch (DAOException daoEx) {
			try {
				rs.close();
				stmnt.close();
			} catch (SQLException sqlEx) {
			}
			return logErrorReturnEmptyClass(daoEx, log, Competition.class);
		} catch (SQLException e) {
			return logErrorReturnEmptyClass(e, log, Competition.class);
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#saveCompetition()
	 */
	@Override
	public IDAOResult saveCompetition(ICompetition competition)
			throws DAOException {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		if (competition != null && competition.isNew()) {
			try {
				stmnt = getConnection().prepareStatement(
						"INSERT INTO " + COMPETITION_TABLE + " ("
								+ COMPETITION_NM_COL + ", "
								+ COMPETITION_TY_ID_COL + ", "
								+ COMPETITION_VISIBILITY_COL
								+ ") VALUES (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				stmnt.setString(1, competition.getName());
				stmnt.setInt(2, ((Integer) competition
						.getCompetitionScoringSystem().getId()).intValue());
				stmnt.setString(3, competition.getCompetitionVisibility());
				stmnt.execute();
				rs = stmnt.getGeneratedKeys();
				rs.next();
				saveCompetitionRoundList(competition.getCompetitionId(),
						competition.getCompetitionRoundList());
				return new DAOResult(true, rs.getString(1));
			} catch (SQLException e) {
				log.error(e.getMessage());
				try {
					stmnt.close();
					rs.close();
				} catch (SQLException sqlEx) {
					log.error("cannot close statement or result set");
				}
				return new DAOResult(false);
			}
		} else if (competition != null && !competition.isNew()) {
			return updateCompetition(competition);
		} else {
			log.error("competition was null or competition name already existed in the table");
			return new DAOResult(false);
		}
	}

	/**
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#isExistingCompetitionName(java.lang.String)
	 */
	@Override
	public boolean isExistingCompetitionName(String competitionName) {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			stmnt = getConnection().prepareStatement(
					"SELECT EXISTS(SELECT * FROM " + COMPETITION_TABLE
							+ " WHERE " + COMPETITION_NM_COL + " = ?)");
			stmnt.setString(1, competitionName);
			rs = stmnt.executeQuery();
			rs.next();
			result = rs.getBoolean(1);
			stmnt.close();
			rs.close();
			return result;
		} catch (DAOException e) {
			log.error(e.getMessage());
			try {
				rs.close();
				stmnt.close();
			} catch (SQLException sqlEx) {
			}
			return result;
		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				rs.close();
				stmnt.close();
			} catch (SQLException sqlEx) {
			}
			return result;
		}
	}

	/**
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#getCompetitionListForUserId(java.lang.String)
	 */
	@Override
	public List<ICompetition> getCompetitionListForUserId(String userId) {

		/*
		 * Get all the competitions which the user created (competition.usrId =
		 * userId) Get all the competitions to which the user has been invited
		 * or entered (competitionEntry.golferId = userId)
		 */
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		try {
			List<ICompetition> competitionList = new ArrayList<ICompetition>();
			stmnt = getConnection().prepareStatement(
					"SELECT c.* FROM " + COMPETITION_ENTRY_TABLE + " e, "
							+ COMPETITION_TABLE + " c WHERE c."
							+ COMPETITION_ID_COL + " = e." + COMPETITION_ID_COL
							+ " AND e." + GOLFER_ID_COL + " = ?;");
			stmnt.setString(1, userId);
			rs = stmnt.executeQuery();
			while (rs.next()) {
				competitionList.add(mapCompetition(rs));
			}
			return competitionList;
		} catch (DAOException e) {
			log.error(e.getMessage());
			try {
				rs.close();
				stmnt.close();
			} catch (SQLException sqlEx) {
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				rs.close();
				stmnt.close();
			} catch (SQLException sqlEx) {
			}
		}
		return new ArrayList<ICompetition>();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#getEnteredCompetitionListForUser(java.lang.String)
	 */
	public List<ICompetition> getEnteredCompetitionListForUser(String userId)
			throws DAOException {
		return null;
	}

	/* COMPETITION TYPE */
	/**
	 * 
	 * @param competitionTypeId
	 * @return
	 */
	@Override
	public ICompetitionType getCompetitionTypeById(int competitionTypeId) {
		if (competitionTypeMap == null || competitionTypeList.isEmpty()) {
			populateCompetitionTypeCollections();
		}
		return competitionTypeIdMap.get(competitionTypeId);
	}

	/**
	 * 
	 * @param competitionTypeName
	 * @return
	 */
	@Override
	public ICompetitionType getCompetitionTypeByName(String competitionTypeName) {
		if (competitionTypeMap == null || competitionTypeList.isEmpty()) {
			populateCompetitionTypeCollections();
		}
		return competitionTypeMap.get(competitionTypeName.toLowerCase());
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#getCompetitionTypeList()
	 */
	@Override
	public List<ICompetitionType> getCompetitionTypeList() {

		// check if the competitionTypeList is empty
		if (competitionTypeList == null || competitionTypeList.isEmpty()) {
			populateCompetitionTypeCollections();
		}
		return competitionTypeList;
	}

	// select e.*, u.prfl_nm, c.cmpttn_nm from t_cmpttn_entry e, t_usr u,
	// t_cmpttn c WHERE u.usr_id = e.glfr_id AND e.is_entry = 'Y';

	/* HELPER METHODS */
	/**
	 * 
	 * @param competitionId
	 * @param competitionRoundList
	 */
	private List<IDAOResult> saveCompetitionRoundList(int competitionId,
			List<ICompetitionRound> competitionRoundList) {
		ArrayList<IDAOResult> roundDAOResultList = new ArrayList<IDAOResult>();
		if (competitionId > 0 && !competitionRoundList.isEmpty()) {
			for (ICompetitionRound competitionRound : competitionRoundList) {
				roundDAOResultList.add(saveCompetitionRound(competitionRound));
			}
		}
		return roundDAOResultList;
	}

	/**
	 * @param competitionToSave
	 * @return
	 */
	private IDAOResult updateCompetition(ICompetition competitionToSave)
			throws DAOException {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		List<IDAOResult> subResultList = new ArrayList<IDAOResult>();
		try {
			stmnt = getConnection().prepareStatement(
					"UPDATE " + COMPETITION_TABLE + " SET "
							+ COMPETITION_NM_COL + " = ?, "
							+ COMPETITION_TY_ID_COL + " = ?" + " WHERE "
							+ COMPETITION_ID_COL + " = ?",
					Statement.RETURN_GENERATED_KEYS);
			stmnt.setString(1, competitionToSave.getName());
			stmnt.setInt(2, ((Integer) competitionToSave
					.getCompetitionScoringSystem().getId()).intValue());
			stmnt.setInt(3, competitionToSave.getCompetitionId());
			boolean result = stmnt.execute();
			rs = stmnt.getGeneratedKeys();
			rs.next();
			List<ICompetitionRound> roundList = competitionToSave
					.getCompetitionRoundList();
			for (int i = 0; i < roundList.size(); i++) {
				subResultList.add(saveCompetitionRound(roundList.get(i)));
			}
			return new DAOResult(result, rs.getString(1));
		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				stmnt.close();
				rs.close();
			} catch (SQLException sqlEx) {
				log.error("cannot close statement or result set");
			}
			return new DAOResult(false);
		}
	}

	/**
	 * @param competitionRoundToSave
	 */
	private DAOResult saveCompetitionRound(
			ICompetitionRound competitionRoundToSave) {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		if (competitionRoundToSave != null) {
			if (competitionRoundToSave.isNew()) {
				// Save Competition Round
				try {
					stmnt = getConnection().prepareStatement(
							"INSERT INTO " + COMPETITION_ROUND_TABLE + " ("
									+ ROUND_NM_COL + ", " + COMPETITION_ID_COL
									+ ", " + ROUND_D_COL + ", " + COURSE_ID_COL
									+ ", " + ROUND_NMBR_COL + ", "
									+ HOLE_QUANTITY_COL + ", "
									+ COMPETITION_TY_ID_COL
									+ ") VALUES (?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
					stmnt.setString(1, competitionRoundToSave.getName());
					stmnt.setString(2, String.valueOf(competitionRoundToSave
							.getCompetitionId()));
					stmnt.setString(3, DateUtils.format(
							competitionRoundToSave.getRoundDate(),
							DateUtils.MYSQL_DATE_FORMAT.getPattern()));
					boolean result = stmnt.execute();
					rs = stmnt.getGeneratedKeys();
					rs.next();
					return new DAOResult(result, rs.getString(1));
				} catch (SQLException e) {
					log.error(e.getMessage());
					try {
						stmnt.close();
						if (rs != null) {
							rs.close();
						}
					} catch (SQLException sqlEx) {
						log.error("cannot close statement or result set");
					} catch (NullPointerException npEx) {

					}
				}
			} else {
				// Update Competition Round
				return updateCompetitionRound(competitionRoundToSave);
			}
		}
		return new DAOResult(false);
	}

	/**
	 * @param iCompetitionRound
	 */
	private DAOResult updateCompetitionRound(
			ICompetitionRound competitionRoundToUpdate) {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		// make sure the competitionRoundToUpdate is valid
		if (competitionRoundToUpdate != null
				&& !competitionRoundToUpdate.isNew()
				&& competitionRoundToUpdate.getRoundId() > 0) {
			try {
				stmnt = getConnection().prepareStatement(
						"UPDATE " + COMPETITION_ROUND_TABLE + " SET "
								+ ROUND_NM_COL + "=?, " + COMPETITION_ID_COL
								+ "=?, " + ROUND_D_COL + "=?, " + COURSE_ID_COL
								+ "=?, " + ROUND_NMBR_COL + "=?, "
								+ HOLE_QUANTITY_COL + "=?, "
								+ COMPETITION_TY_ID_COL + "=?" + "WHERE "
								+ COMPETITION_ROUND_ID_COL + "=?",
						Statement.RETURN_GENERATED_KEYS);
				stmnt.setString(1, competitionRoundToUpdate.getName());
				stmnt.setString(2, String.valueOf(competitionRoundToUpdate
						.getCompetitionId()));
				stmnt.setString(3, DateUtils.format(
						competitionRoundToUpdate.getRoundDate(),
						DateUtils.MYSQL_DATE_FORMAT.getPattern()));
				stmnt.setString(4,
						competitionRoundToUpdate.getRoundIdAsString());
				boolean result = stmnt.execute();
				rs = stmnt.getGeneratedKeys();
				rs.next();
				return new DAOResult(result, rs.getString(1));
			} catch (SQLException e) {
				log.error(e.getMessage());
				try {
					stmnt.close();
					rs.close();
				} catch (SQLException sqlEx) {
					log.error("cannot close statement or result set");
				}
				return new DAOResult(false);
			}
		} else {
			return new DAOResult(false);
		}
	}

	/**
	 * 
	 */
	private void populateCompetitionTypeCollections() {
		// create statement to select all the golfer and society information
		// from the database
		if (competitionTypeList == null) {
			this.competitionTypeList = new ArrayList<ICompetitionType>();
		}
		if (competitionTypeMap == null) {
			this.competitionTypeMap = new HashMap<String, ICompetitionType>();
		}
		if (competitionTypeIdMap == null) {
			this.competitionTypeIdMap = new HashMap<Integer, ICompetitionType>();
		}
		try {
			PreparedStatement stmnt = getConnection().prepareStatement(
					"SELECT * FROM " + COMPETITION_TY_TABLE + ";");
			ResultSet res = stmnt.executeQuery();

			// traverse the results populating the competitionType collections
			while (res.next()) {
				log.debug("Processing Row: " + res.getRow());
				addCompetitionTypeToCollections(new CompetitionType(getInt(res,
						TYPE_ID_COL), // id
						getString(res, TYPE_NM_COL), // name
						getString(res, TYPE_SCHEDULER_COL), // scheduler
						getString(res, TYPE_SCORER_COL), // scorer
						getBoolean(res, TYPE_TEAM_COL), // isTeam
						getBoolean(res, TYPE_PAIR_COL), // isPair
						getBoolean(res, TYPE_INDIVIDUAL_COL) // isIndividual
				));
			}
		} catch (DAOException e) {
			log.error("Unable to populate the competitionType collections; "
					+ e.getMessage());
		} catch (SQLException e) {
			log.error("Unable to populate the competitionType collections; "
					+ e.getMessage());
		}
	}

	/**
	 * @param competitionType
	 * @param competitionTypeList
	 * @param competitionTypeMap
	 */
	private void addCompetitionTypeToCollections(CompetitionType competitionType) {
		if (competitionType != null) {
			competitionTypeList.add(competitionType);
			competitionTypeMap.put(competitionType.getName().toLowerCase(),
					competitionType);
			competitionTypeIdMap.put(competitionType.getId(), competitionType);
		}
	}

	/**
	 * @param resultSet
	 * @return
	 */
	private Competition mapCompetition(ResultSet resultSet) {
		return new Competition(
				getInt(resultSet, COMPETITION_ID_COL),// id,
				getString(resultSet, COMPETITION_NM_COL),// name,
				getCompetitionTypeById(getInt(resultSet, COMPETITION_TY_ID_COL)),// competitionScoringSystem,
				getString(resultSet, COMPETITION_VISIBILITY_COL)// rs.getString("cmpttn_"),//competitionVisibility,
		);
	}
}

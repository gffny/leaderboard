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
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.CompetitionType;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
@Component
public class CompetitionDAO extends AbstractMySQLDAO implements ICompetitionDAO {

	private static Logger log = Logger.getLogger(CompetitionDAO.class);

	private Map<String, ICompetitionType> competitionTypeMap;

	private Map<Integer, ICompetitionType> competitionTypeIdMap;

	private List<ICompetitionType> competitionTypeList;

	private static ICompetitionDAO INSTANCE = null;

	/**
	 * 
	 * @return
	 */
	public static ICompetitionDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CompetitionDAO();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	private CompetitionDAO() {
		competitionTypeMap = new HashMap<String, ICompetitionType>();
		competitionTypeIdMap = new HashMap<Integer, ICompetitionType>();
		competitionTypeList = new ArrayList<ICompetitionType>();
	}

	/**
	 * @throws SQLException
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
	 * @param competitionTypeName
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
	 * @see com.gffny.leaderboard.dao.ICompetitionDAO#isExistingCompetitionName(java.lang.String)
	 */
	@Override
	public boolean isExistingCompetitionName(String competitionName) {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			stmnt = getConnection()
					.prepareStatement(
							"SELECT EXISTS(SELECT * FROM t_cmpttn WHERE cmpttn_nm = ?)");
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
					"SELECT * FROM t_cmpttn_ty WHERE cmpttn_id = ?;");
			stmnt.setInt(1, competitionId);
			rs = stmnt.executeQuery();

			// traverse the results populating the competitionType collections
			if (rs.next()) {
				log.debug("Processing Row: " + rs.getRow());
				return new Competition(rs.getInt("cmpttn_id"),// id,
						rs.getString("cmpttn_nm"),// name,
						getCompetitionTypeById(rs.getInt("cmpttn_ty_id")),// competitionScoringSystem,
						null,// rs.getString("cmpttn_"),//competitionVisibility,
						0// numberOfRounds
				);
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
	public DAOResult saveCompetition(ICompetition competitionToSave)
			throws DAOException {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		if (competitionToSave != null
				&& !isExistingCompetitionName(competitionToSave.getName())) {
			try {
				stmnt = getConnection()
						.prepareStatement(
								"INSERT INTO t_cmpttn (cmpttn_nm, cmpttn_ty_id) VALUES (?, ?)",
								Statement.RETURN_GENERATED_KEYS);
				stmnt.setString(1, competitionToSave.getName());
				stmnt.setInt(2, ((Integer) competitionToSave
						.getCompetitionScoringSystem().getId()).intValue());
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
			log.error("competition was null or competition name already existed in the table");
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
					"SELECT * FROM t_cmpttn_ty;");
			ResultSet res = stmnt.executeQuery();

			// traverse the results populating the competitionType collections
			while (res.next()) {
				log.debug("Processing Row: " + res.getRow());
				addCompetitionTypeToCollections(new CompetitionType(
						res.getInt("ty_id"), // id
						res.getString("ty_nm"), // name
						res.getString("ty_schdlr"), // scheduler
						res.getString("ty_scrr"), // scorer
						res.getBoolean("ty_tm"), // isTeam
						res.getBoolean("ty_pr"), // isPair
						res.getBoolean("ty_indvdl") // isIndividual
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
}

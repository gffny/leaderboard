/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.IDAOFactory;
import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.dao.IUserDAO;

/**
 * @author jgaffney02
 *
 */
public class DAOFactory implements IDAOFactory {

	private static Logger log = Logger.getLogger(DAOFactory.class);
	private static IDAOFactory instance = null;
	private UserDAO userDaoInstance = null;
	private ScorecardDAO scorecardDaoInstance = null;
	private GolfCourseDAO golfCourseDaoInstance;
	
	private DAOFactory() {
		
	}

	public static synchronized IDAOFactory getInstance() {
		if(instance == null) {
			log.info("creating new instance of DAOFactory");
			instance = new DAOFactory();
		}
		return instance;
	}

	public IUserDAO getUserDAO() {
		if(userDaoInstance == null) {
			log.info("creating instance of UserDAO in DAOFactory");
			userDaoInstance = new UserDAO();
		}
		log.info("retrieving instance of UserDAO from DAOFactory");
		return userDaoInstance;
	}
	
	public IScorecardDAO getScorecardDAO() {
		if(scorecardDaoInstance == null) {
			log.info("creating instance of ScorecardDAO from DAOFactory");
			scorecardDaoInstance  = new ScorecardDAO();
		}
		return scorecardDaoInstance;
	}

	public IGolfCourseDAO getGolfCourseDAO() {
		if(golfCourseDaoInstance == null) {
			log.info("creating instance of GolfCourseDAO from DAOFactory");
			golfCourseDaoInstance  = new GolfCourseDAO();
		}
		return golfCourseDaoInstance;
	}

	public Object clone() throws CloneNotSupportedException {
		log.info("should not be calling clone() on DAOFactory");
		throw new CloneNotSupportedException();
	}
}

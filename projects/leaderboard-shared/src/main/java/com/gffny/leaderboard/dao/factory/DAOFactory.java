/**
 * 
 */
package com.gffny.leaderboard.dao.factory;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.IDAOFactory;
import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.dao.IUserDAO;
import com.gffny.leaderboard.dao.mongodb.GolfCourseDAO;
import com.gffny.leaderboard.dao.mysql.ScorecardDAO;
import com.gffny.leaderboard.dao.mysql.UserDAO;

/**
 * @author jgaffney02
 *
 */
public class DAOFactory implements IDAOFactory {

	private static Logger log = Logger.getLogger(DAOFactory.class);
	private static IDAOFactory instance = null;
	private UserDAO userDaoInstance = null;
	private ScorecardDAO scorecardDaoInstance = null;
	private GolfCourseDAO golfCourseDaoInstance = null;
	
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
			//TODO LOOK AT CLASS LOADING OR SPING LOADING THE DAO!
			userDaoInstance = new UserDAO(); 
		}
		log.info("retrieving instance of UserDAO from DAOFactory");
		return userDaoInstance;
	}
	
	public IScorecardDAO getScorecardDAO() {
		if(scorecardDaoInstance == null) {
			log.info("creating instance of ScorecardDAO from DAOFactory");
			//TODO LOOK AT CLASS LOADING OR SPING LOADING THE DAO!
			scorecardDaoInstance  = new ScorecardDAO();
		}
		return scorecardDaoInstance;
	}

	public IGolfCourseDAO getGolfCourseDAO() {
		if(golfCourseDaoInstance == null) {
			log.info("creating instance of GolfCourseDAO from DAOFactory");
			//TODO LOOK AT CLASS LOADING OR SPING LOADING THE DAO!
			golfCourseDaoInstance  = new GolfCourseDAO();
		}
		return golfCourseDaoInstance;
	}

	public Object clone() throws CloneNotSupportedException {
		log.info("should not be calling clone() on DAOFactory");
		throw new CloneNotSupportedException();
	}
}

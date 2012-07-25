/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.IDAOFactory;

/**
 * @author jgaffney02
 *
 */
public class DAOFactory implements IDAOFactory {

	private static Logger log = Logger.getLogger(DAOFactory.class);
	private static IDAOFactory instance = null;
	private UserDAO userDaoInstance = null;
	
	private DAOFactory() {
		
	}

	public static synchronized IDAOFactory getInstance() {
		if(instance == null) {
			log.info("creating new instance of DAOFactory");
			instance = new DAOFactory();
		}
		return instance;
	}

	public UserDAO getUserDAO() {
		if(userDaoInstance == null) {
			log.info("creating instance of UserDAO in DAOFactory");
			userDaoInstance = new UserDAO();
		}
		log.info("retrieving instance of UserDAO from DAOFactory");
		return userDaoInstance;
	}

	public Object clone() throws CloneNotSupportedException {
		log.info("should not be calling clone() on DAOFactory");
		throw new CloneNotSupportedException();
	}
}

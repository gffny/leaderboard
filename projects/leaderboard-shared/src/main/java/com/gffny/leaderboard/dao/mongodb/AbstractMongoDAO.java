/**
 * 
 */
package com.gffny.leaderboard.dao.mongodb;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.abst.AbstractDAO;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * @author John Gaffney (john@gffny.com) Aug 14, 2012
 * 
 */
public class AbstractMongoDAO extends AbstractDAO {

	/**
	 *
	 */
	static final String COURSE_COLLECTION = "courseList";

	/**
	 *
	 */
	static final String GOLF_CLUB_COLLECTION = "clubList";

	/**
	 *
	 */
	protected static final String COURSE_MAP_IMAGE_COLLECTION = "fresh-pond";

	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(AbstractMongoDAO.class);

	/**
	 * 
	 */
	private DB database;

	/**
	 * 
	 */
	protected AbstractMongoDAO() {
		try {
			// connect to the database
			Mongo mongo = new Mongo("localhost", 27017);
			// get tutorials database
			database = mongo.getDB("ldrbrd");
		} catch (UnknownHostException ex) {
			log.error(ex.getMessage());
		} catch (MongoException ex) {
			log.error(ex.getMessage());
		}
	}

	/**
	 * 
	 * @param collectionName
	 * @return
	 */
	public DBCollection getCollection(String collectionName) {
		return database.getCollection(collectionName);
	}

	/**
	 * 
	 * @return
	 */
	protected DB getDatabase() {
		return database;
	}
}

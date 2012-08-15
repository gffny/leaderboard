/**
 * 
 */
package com.gffny.leaderboard.dao.mongodb;

import java.util.List;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 14, 2012
 *
 */
public class GolfCourseDAO extends AbstractMongoDAO implements IGolfCourseDAO {

	private static Logger log = Logger.getLogger(GolfCourseDAO.class);

	/**
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getCourseById(java.lang.String)
	 */
	public List<IGolfCourse> getCourseById(String courseId) throws DAOException {
		// TODO Auto-generated method stub
		log.error("courseId: "+courseId);
		System.out.println("courseId: "+courseId);
		DBCollection user = getCollection("user");
		System.out.println(user.count());
		DBCursor cursor = user.find();
		while(cursor.hasNext()) {
			DBObject dbo = cursor.next();
			System.out.println((String)dbo.get("name"));
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getCourseNameListByClub(java.lang.String)
	 */
	public List<String> getCourseNameListByClub(String clubName)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getCourseByClubAndCourseName(java.lang.String, java.lang.String)
	 */
	public List<IGolfCourse> getCourseByClubAndCourseName(String clubName,
			String courseName) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getGolfClubNameListByCountry(java.lang.String, java.lang.String)
	 */
	public List<String> getGolfClubNameListByCountryAndState(String country, String state)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}

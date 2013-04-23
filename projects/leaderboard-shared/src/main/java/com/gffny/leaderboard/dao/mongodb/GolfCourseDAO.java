/**
 * 
 */
package com.gffny.leaderboard.dao.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.mongodb.dbo.ClubDBO;
import com.gffny.leaderboard.dao.mongodb.dbo.CourseDBO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * @author John Gaffney (john@gffny.com) Aug 14, 2012
 * 
 */
public class GolfCourseDAO extends AbstractMongoDAO implements IGolfCourseDAO {

	/**
	 * 
	 */
	private static Logger log = Logger.getLogger(GolfCourseDAO.class);

	/**
	 * 
	 */
	private DBCollection clubCollection = null;

	/**
	 * 
	 */
	private DBCollection courseCollection = null;

	/**
	 * 
	 */
	private static GolfCourseDAO INSTANCE = null;

	/**
	 * 
	 * @return
	 */
	public static GolfCourseDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GolfCourseDAO();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	private GolfCourseDAO() {
		super();
		clubCollection = getCollection(GOLF_CLUB_COLLECTION);
		courseCollection = getCollection(COURSE_COLLECTION);
	}

	/**
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getCourseByIdAndTeeColour(java.lang.String)
	 * 
	 * @param courseId
	 * @param teeColour
	 * @return
	 * @throws DAOException
	 */
	@Override
	public List<IGolfCourse> getCourseByIdAndTeeColour(String courseId,
			String teeColour) throws DAOException {
		log.debug("courseId: " + courseId + ", teeColour: " + teeColour);
		return Arrays.asList(getCourseDBO(courseId).getGolfCourse(teeColour));
	}

	/**
	 * 
	 * @param clubName
	 * @return
	 */
	@Override
	public List<String> getCourseNameListByClubName(String clubName)
			throws DAOException {
		log.debug("club name: " + clubName);
		return getClubDBO(clubName).getCourseNameList();
	}

	/**
	 * 
	 * @param clubName
	 * @param courseName
	 * @return
	 * @throws DAOException
	 */
	@Override
	public List<String> getTeeColourListByClubNameAndCourseName(
			String clubName, String courseName) throws DAOException {
		log.debug("club name: " + clubName + ", courseName: " + courseName);
		return getClubDBO(clubName).getCourse(courseName).getTeeColourList();
	}

	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws DAOException
	 */
	@Override
	public List<String> getTeeColourListByCourseId(String courseId)
			throws DAOException {
		log.debug("courseId: " + courseId);
		return getCourseDBO(courseId).getTeeColourList();
	}

	/**
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getAllGolfCourseList()
	 */
	@Override
	public List<IGolfCourse> getAllGolfCourseList() throws DAOException {
		log.debug("all courses");
		DBCursor courseListCursor = courseCollection.find();
		List<IGolfCourse> golfCourseList = new ArrayList<IGolfCourse>();
		while (courseListCursor.hasNext()) {
			CourseDBO courseDbo = new CourseDBO(courseListCursor.next());
			Iterator<String> courseTeeColourItr = courseDbo.getTeeColourList()
					.iterator();
			while (courseTeeColourItr.hasNext()) {
				IGolfCourse course = courseDbo.getGolfCourse(courseTeeColourItr
						.next());
				if (course != null) {
					golfCourseList.add(course);
				}
			}
		}
		return golfCourseList;
	}

	/**
	 * @param clubName
	 * @return
	 */
	private ClubDBO getClubDBO(String clubName) throws DAOException {
		return new ClubDBO(clubCollection.findOne(new BasicDBObject("name",
				clubName)));
	}

	/**
	 * @param courseId
	 * @return
	 */
	private CourseDBO getCourseDBO(String courseId) {
		return new CourseDBO(courseCollection.findOne(new BasicDBObject("_id",
				new ObjectId(courseId))));
	}
}

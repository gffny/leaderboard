/**
 * 
 */
package com.gffny.leaderboard.dao.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

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
	private GridFS courseMapFS = null;

	/**
	 * 
	 */
	private static AbstractMongoDAO INSTANCE = null;

	/**
	 * 
	 * @return
	 */
	public static AbstractMongoDAO getInstance() {
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
		courseMapFS = new GridFS(getDatabase(), COURSE_MAP_IMAGE_COLLECTION);
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
		if (courseId != null && teeColour != null) {
			return Arrays.asList(getCourseDBO(courseId)
					.getGolfCourse(teeColour));
		}
		return new LinkedList<IGolfCourse>();
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
		if (clubName != null) {
			return getClubDBO(clubName).getCourseNameList();
		}
		return new LinkedList<String>();
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
		if (clubName != null && courseName != null) {
			return getClubDBO(clubName).getCourse(courseName)
					.getTeeColourList();
		}
		return new LinkedList<String>();
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
		if (courseId != null) {
			return getCourseDBO(courseId).getTeeColourList();
		}
		return new LinkedList<String>();
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
	 * 
	 * return List<GridFSDBFile> @see {@link GridFSDBFile}
	 * 
	 * @see com.gffny.leaderboard.dao.IGolfCourseDAO#getHoleMapList()
	 */
	@Override
	public List<Object> getHoleMapList(String courseId) throws DAOException {

		log.debug("getting map for course " + courseId);
		// get the list of file names
		Map<String, String> courseImageNameMap = getCourseDBO(courseId)
				.getHoleImageList();

		// create a list to house the map images
		List<Object> imageFileList = new LinkedList<Object>();
		for (int i = 1; i <= courseImageNameMap.size(); i++) {
			imageFileList.add(courseMapFS.findOne(courseImageNameMap
					.get(getImageMapKey(i))));
		}
		return imageFileList;
	};

	/**
	 * 
	 * @param courseId
	 * @param holeNumber
	 * @return
	 * @throws DAOException
	 */
	public Object getHoleMapImage(String courseId, int holeNumber)
			throws DAOException {
		log.debug("getting map for hole" + holeNumber + "course " + courseId);
		if (courseId != null && holeNumber > 0 && holeNumber < 19) {
			return getCourseDBO(courseId).getHoleImageList().get(
					getImageMapKey(holeNumber));
		}
		return null; // TODO make this a blankImage;
	}

	/**
	 * @param holeNumber
	 * @return
	 */
	private String getImageMapKey(int holeNumber) {
		return "h" + holeNumber;
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

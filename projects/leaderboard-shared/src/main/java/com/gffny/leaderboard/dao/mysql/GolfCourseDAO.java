/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.impl.GolfCourse;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 31, 2012
 *
 */
public class GolfCourseDAO extends AbstractMySQLDAO implements IGolfCourseDAO {

	private PreparedStatement stmnt = null;

	/**
	 * 
	 * @param golfCourseId
	 */
	public List<IGolfCourse> getCourseById(String courseId)
			throws DAOException {
		List <IGolfCourse> returnList = new ArrayList<IGolfCourse>();
		try {
			stmnt  = getConnection().prepareStatement("select * from t_club_course c INNER JOIN t_golf_club g ON c.golf_club_id=g.golf_club_id WHERE course_id=?");
			stmnt.setString(1, courseId);
			ResultSet res = stmnt.executeQuery();
			while(res.next()) {
				String[] holeParArray = {
						res.getString("par_hole_1"),
						res.getString("par_hole_2"),
						res.getString("par_hole_3"),
						res.getString("par_hole_4"),
						res.getString("par_hole_5"),
						res.getString("par_hole_6"),
						res.getString("par_hole_7"),
						res.getString("par_hole_8"),
						res.getString("par_hole_9"),
						res.getString("par_hole_10"),
						res.getString("par_hole_11"),
						res.getString("par_hole_12"),
						res.getString("par_hole_13"),
						res.getString("par_hole_14"),
						res.getString("par_hole_15"),
						res.getString("par_hole_16"),
						res.getString("par_hole_17"),
						res.getString("par_hole_18")};
				String[] holeIndex = {
						res.getString("index_hole_1"),
						res.getString("index_hole_2"),
						res.getString("index_hole_3"),
						res.getString("index_hole_4"),
						res.getString("index_hole_5"),
						res.getString("index_hole_6"),
						res.getString("index_hole_7"),
						res.getString("index_hole_8"),
						res.getString("index_hole_9"),
						res.getString("index_hole_10"),
						res.getString("index_hole_11"),
						res.getString("index_hole_12"),
						res.getString("index_hole_13"),
						res.getString("index_hole_14"),
						res.getString("index_hole_15"),
						res.getString("index_hole_16"),
						res.getString("index_hole_17"),
						res.getString("index_hole_18")};
				String[] teeDistance = {
						res.getString("distance_hole_1"),
						res.getString("distance_hole_2"),
						res.getString("distance_hole_3"),
						res.getString("distance_hole_4"),
						res.getString("distance_hole_5"),
						res.getString("distance_hole_6"),
						res.getString("distance_hole_7"),
						res.getString("distance_hole_8"),
						res.getString("distance_hole_9"),
						res.getString("distance_hole_10"),
						res.getString("distance_hole_11"),
						res.getString("distance_hole_12"),
						res.getString("distance_hole_13"),
						res.getString("distance_hole_14"),
						res.getString("distance_hole_15"),
						res.getString("distance_hole_16"),
						res.getString("distance_hole_17"),
						res.getString("distance_hole_18")};
				returnList.add(new GolfCourse(
						res.getString("course_name"),// String name;
						res.getString("course_par"),// String par;
						res.getString("club_location"),// String location;
						res.getString("tee_colour"), //String teeColour;
						holeParArray,// String[] holePar;
						holeIndex,// String[] holeIndex;
						teeDistance // String[] goldTeeDistance;
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
		return returnList;
	}

	@Override
	public List<String> getCourseNameListByClub(String clubName)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IGolfCourse> getCourseByClubAndCourseName(String clubName,
			String courseName) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getGolfClubNameListByCountryAndState(String country,
			String state) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}

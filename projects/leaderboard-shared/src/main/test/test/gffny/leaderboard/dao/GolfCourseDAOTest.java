package test.gffny.leaderboard.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.mongodb.GolfCourseDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfCourse;

/**
 * 
 */

/**
 * @author John Gaffney (john@gffny.com) Jul 31, 2012
 * 
 */
public class GolfCourseDAOTest {

	@Autowired
	private IGolfCourseDAO golfCourseDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(this, "golfCourseDao",
				GolfCourseDAO.getInstance());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}
	 * 
	 */
	@Test
	public final void getHoleMapList() {
		try {
			List<Object> gcList = golfCourseDao
					.getHoleMapList("50fdb6681cd88454b3b083f9");
			gcList.size();
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetGolfCourseById() {
		try {
			golfCourseDao.getCourseByIdAndTeeColour("50fdb6681cd88454b3b083f9",
					"white");
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetTeeColourListByCourseId() {
		try {
			golfCourseDao
					.getTeeColourListByCourseId("50fdb6681cd88454b3b083f9");
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetTeeColourListByClubNameAndCourseName() {
		try {
			golfCourseDao.getTeeColourListByClubNameAndCourseName(
					"St. Andrew's Links", "Old Course");
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}
	 * 
	 */
	@Test
	public final void testGetCourseListByClubName() {
		try {
			golfCourseDao.getCourseNameListByClubName("St. Andrew's Links");
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}
	 * 
	 */
	@Test
	public final void testGetAllGolfCourseList() {
		try {
			List<IGolfCourse> gcList = golfCourseDao.getAllGolfCourseList();
			gcList.size();
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}
}

package test.gffny.leaderboard.dao;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.dao.mysql.DAOFactory;
import com.gffny.leaderboard.layerUtils.DAOException;

/**
 * 
 */

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 31, 2012
 *
 */
public class GolfCourseDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.dao.mysql.GolfCourseDAO#getGolfCourseById(java.lang.String)}.
	 */
	@Test
	public final void testGetGolfCourseById() {
		try {
			System.out.println(DAOFactory.getInstance().getGolfCourseDAO().getCourseById("1").toString());
			System.out.println(DAOFactory.getInstance().getGolfCourseDAO().getCourseById("1").get(0).getHole(7).toString());
		} catch (DAOException e) {
			fail();
			e.printStackTrace();
		}
	}
}

/**
 * 
 */
package test.gffny.leaderboard.dao;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.intralayer.ServiceException;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 * 
 */
public class ScorecardDAOTest {

	@Autowired
	private IScorecardDAO scorecardDao;

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
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getScorecardListForUser(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetScorecardListForUser() {
		try {
			scorecardDao.getScorecardListForUser("1");
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getLatestScorecardForUser(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetLatestScorecardForUser() {
		try {
			System.out.println(scorecardDao.getScorecardListForUser("1")
					.toString());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getLastXScorecardListForUser(java.lang.String, int)}
	 * .
	 */
	@Test
	public final void testGetLastXScorecardListForUser() {
		try {
			System.out.println(scorecardDao.getScorecardListForUser("1")
					.toString());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

}

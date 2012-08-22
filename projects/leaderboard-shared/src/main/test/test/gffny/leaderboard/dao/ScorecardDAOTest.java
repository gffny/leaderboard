/**
 * 
 */
package test.gffny.leaderboard.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.dao.factory.DAOFactory;
import com.gffny.leaderboard.intralayer.ServiceException;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 30, 2012
 *
 */
public class ScorecardDAOTest {

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
	 * Test method for {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getScorecardListForUser(java.lang.String)}.
	 */
	@Test
	public final void testGetScorecardListForUser() {
		try {
			System.out.println(DAOFactory.getInstance().getScorecardDAO().getScorecardListForUser("1").toString());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getLatestScorecardForUser(java.lang.String)}.
	 */
	@Test
	public final void testGetLatestScorecardForUser() {
		try {
			System.out.println(DAOFactory.getInstance().getScorecardDAO().getScorecardListForUser("1").toString());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getLastXScorecardListForUser(java.lang.String, int)}.
	 */
	@Test
	public final void testGetLastXScorecardListForUser() {
		try {
			System.out.println(DAOFactory.getInstance().getScorecardDAO().getScorecardListForUser("1").toString());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

}

/**
 * 
 */
package test.gffny.leaderboard.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.IScorecardDAO;
import com.gffny.leaderboard.dao.mysql.ScorecardDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IScorecard;
import com.gffny.leaderboard.util.CollectionUtils;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 * 
 */
public class ScorecardDAOTest {

	@Autowired
	private IScorecardDAO scorecardDao = new ScorecardDAO();

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
		} catch (DAOException e) {
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
		} catch (DAOException e) {
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
		} catch (DAOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * {@link com.gffny.leaderboard.dao.mysql.ScorecardDAO#getScorecardListForCompetitionByUserId(java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testGetScorecardListForCompetitionByUserId() {
		try {
			List<IScorecard> scorecardList = scorecardDao
					.getScorecardListForCompetitionByUserId("1", "1");
			assertTrue("List is not empty",
					CollectionUtils.isNotEmpty(scorecardList));
		} catch (DAOException e) {
			e.printStackTrace();
			fail();
		}
	}
}

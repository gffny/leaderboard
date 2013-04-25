/**
 * 
 */
package test.gffny.leaderboard.dao;

import java.sql.SQLException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.ICompetitionDAO;
import com.gffny.leaderboard.dao.mysql.CompetitionDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.IDAOResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.impl.Competition;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public class CompetitionDAOTest extends TestCase {

	@Autowired
	private ICompetitionDAO competitionDao = new CompetitionDAO();

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
	 * {@link com.gffny.leaderboard.dao.mysql.CompetitionDAO#getCompetitionTypeList()}
	 * .
	 * 
	 * @throws SQLException
	 * @throws DAOException
	 */
	@Test
	public final void testGetCompetitionTypeList() throws DAOException,
			SQLException {
		List<ICompetitionType> competitionTypeList = competitionDao
				.getCompetitionTypeList();
		assertEquals(competitionTypeList.isEmpty(), false);
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.CompetitionDAO#saveCompetition()}.
	 * 
	 * @throws DAOException
	 */
	@Test
	public final void testSaveCompetition() throws DAOException {
		ICompetition competitionToSave = new Competition("test",
				competitionDao.getCompetitionTypeByName("stableford"),
				ICompetition.PRIVATE_VISIBILITY);
		try {
			IDAOResult result = competitionDao
					.saveCompetition(competitionToSave);

			assertTrue(result.getIdAsInt() > 0);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.CompetitionDAO#saveCompetition()}.
	 * 
	 * @throws DAOException
	 */
	@Test
	public final void testGetCompetitionListForUserId() throws DAOException {
		try {
			List<ICompetition> competitionList = competitionDao
					.getCompetitionListForUserId("1");
			assertTrue(CollectionUtils.isNotEmpty(competitionList));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.CompetitionDAO#isExistingCompetition()}
	 * .
	 * 
	 * @throws DAOException
	 */
	@Test
	public final void testIsExistingCompetition() throws DAOException {
		assertTrue("", competitionDao.isExistingCompetitionName("test"));
	}
}

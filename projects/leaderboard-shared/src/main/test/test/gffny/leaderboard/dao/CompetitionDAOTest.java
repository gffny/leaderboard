/**
 * 
 */
package test.gffny.leaderboard.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.dao.ICompetitionDAO;
import com.gffny.leaderboard.dao.mysql.CompetitionDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.CompetitionRound;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public class CompetitionDAOTest extends TestCase {

	private ICompetitionDAO competitionDao = CompetitionDAO.getInstance();

	private int competitionId;

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
	 */
	@Test
	public final void testSaveCompetition() {
		ICompetition competitionToSave = new Competition("test",
				competitionDao.getCompetitionTypeByName("stableford"),
				ICompetition.PRIVATE_VISIBILITY, 1);
		try {
			DAOResult result = competitionDao
					.saveCompetition(competitionToSave);
			competitionId = result.getIdAsInt();

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	@Test
	public final void testSaveCompetitionRound() throws DAOException {
		ICompetitionRound competitionRound = new CompetitionRound("test round",
				2, new Date(), "asdfa");
		competitionRound.setCompetitionId(competitionId);
		competitionDao.saveCompetitionRound(competitionRound);
	}

	public final void testIsExistingCompetition() {
		assertTrue("", competitionDao.isExistingCompetitionName("test"));
	}
}

/**
 * 
 */
package test.gffny.leaderbaord.service;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public class CompetitionServiceTest extends TestCase {

	/**
	 * 
	 */
	@Autowired
	private ICompetitionService competitionService;

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
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#getCompetitionScheduler(int)}
	 * .
	 */
	@Test
	public final void testGetCompetitionScheduler() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#getCompetition(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#getCompetitionListForUserId(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetCompetitionListForUserId() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#getCompetitorListForCompetition(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetCompetitorListForCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#getCompetitionRound(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetCompetitionRound() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#scoreRound(java.lang.String)}
	 * .
	 */
	@Test
	public final void testScoreRound() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#scoreCompetition(java.lang.String)}
	 * .
	 */
	@Test
	public final void testScoreCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#createCompetition(java.lang.String, java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public final void testCreateCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#saveCompetition(com.gffny.leaderboard.model.ICompetition)}
	 * .
	 */
	@Test
	public final void testSaveCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#getCompetitionScoringSystemList()}
	 * .
	 */
	@Test
	public final void testGetCompetitionScoringSystemList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.impl.CompetitionService#createCompetitionRound(int, java.lang.String, java.util.Date, int, java.lang.String)}
	 * .
	 * 
	 * @throws ServiceException
	 */
	@Test
	public final void testCreateCompetitionRound() throws ServiceException {
		competitionService.createCompetitionRound(0, "", new Date(), 0, "");
	}

}

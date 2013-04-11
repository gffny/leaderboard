/**
 * 
 */
package test.gffny.leaderboard.component.scorer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.gffny.leaderboard.utilities.TestUtilities;

import com.gffny.leaderboard.component.scorer.impl.StablefordCompetitionScorer;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.IScorecard;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class TestStablefordCompetitionScorer {

	private StablefordCompetitionScorer scs;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		scs = new StablefordCompetitionScorer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scorer.impl.StablefordCompetitionScorer#scoreCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound, java.util.Map, com.gffny.leaderboard.model.IGolfCourse)}
	 * .
	 */
	@Test
	public final void testScoreCompetitionRound() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scorer.impl.StablefordCompetitionScorer#scoreGolferCard(com.gffny.leaderboard.model.IScorecard, com.gffny.leaderboard.model.IGolfCourse)}
	 * .
	 */
	@Test
	public final void testScoreGolferCard() {
		ICompetitionRound testRound = TestUtilities.getTestRound(1);
		System.out.println("Scorecard Map");
		Map<Integer, IScorecard> testScoreCardMap = TestUtilities
				.getTestScoreCardMap();
		IGolfCourse testGolfCourse = TestUtilities.getTestGolfCourse(18, 72);
		System.out.println("\nGolf Course");
		System.out.println(testGolfCourse.toString());
		Map<Integer, Integer> scoreMap = scs.scoreCompetitionRound(testRound,
				testScoreCardMap, testGolfCourse);
		System.out.println("\nStableford Scores");
		List<IGolfer> golferList = TestUtilities.getTestGolferList();
		for (int i = 0; i < golferList.size(); i++) {
			IGolfer golfer = golferList.get(i);
			System.out.println(golfer.getLastName() + "|"
					+ scoreMap.get(golfer.getUserId()));
		}
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scorer.impl.StablefordCompetitionScorer#calcHandicapOnHole(java.lang.String, int, int)}
	 * .
	 */
	@Test
	public final void testCalcPoints() {
		// case: zero handicap
		// subcase: 2 over
		assertEquals(0, scs.calcPoints(4, 6, 0));
		// subcase: 1 over
		assertEquals(1, scs.calcPoints(5, 6, 0));
		// subcase: 0 over
		assertEquals(2, scs.calcPoints(4, 4, 0));
		// subcase: 1 under
		assertEquals(3, scs.calcPoints(4, 3, 0));
		// subcase: 2 under
		assertEquals(4, scs.calcPoints(5, 3, 0));
		// subcase: 3 under
		assertEquals(5, scs.calcPoints(5, 2, 0));
		// subcase: 4 under
		assertEquals(6, scs.calcPoints(5, 1, 0));
		// case: non-zero handicap
		// subcase: 2 over
		assertEquals(0, scs.calcPoints(4, 7, 1));
		// subcase: 1 over
		assertEquals(1, scs.calcPoints(4, 7, 2));
		// subcase: 0 over
		assertEquals(2, scs.calcPoints(4, 7, 3));
		// subcase: 1 under
		assertEquals(3, scs.calcPoints(4, 4, 1));
		// subcase: 2 under
		assertEquals(4, scs.calcPoints(4, 4, 2));
		// subcase: 3 under
		assertEquals(5, scs.calcPoints(4, 4, 3));
		// subcase: 4 under
		assertEquals(6, scs.calcPoints(4, 4, 4));
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scorer.impl.StablefordCompetitionScorer#calcHandicapOnHole(java.lang.String, int, int)}
	 * .
	 */
	@Test
	public final void testCalcHandicapOnHole() {
		// handicap is less than index
		assertEquals(0, scs.calcHandicapOnHole(5, 4, 18));
		// handicap is equal to index
		assertEquals(1, scs.calcHandicapOnHole(5, 5, 18));
		// handicap is greater than index but less than number of holes
		assertEquals(1, scs.calcHandicapOnHole(5, 10, 18));
		// handicap is greater than index and great than number of holes
		assertEquals(2, scs.calcHandicapOnHole(5, 23, 18));
	}
}

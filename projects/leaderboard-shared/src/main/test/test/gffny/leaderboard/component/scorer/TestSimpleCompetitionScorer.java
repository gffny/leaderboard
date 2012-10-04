/**
 * 
 */
package test.gffny.leaderboard.component.scorer;

import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.utilities.TestUtilities;

import com.gffny.leaderboard.component.scorer.ICompetitionRoundScorer;
import com.gffny.leaderboard.component.scorer.impl.SimpleCompetitionScorer;
import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class TestSimpleCompetitionScorer {

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
	 * {@link com.gffny.leaderboard.component.scorer.impl.SimpleCompetitionScorer#scoreCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound, java.util.Map)}
	 * .
	 */
	@Test
	public final void testScoreCompetitionRound() {
		ICompetitionRoundScorer competitionScorer = new SimpleCompetitionScorer();
		Map<Integer, Integer> roundScores = competitionScorer
				.scoreCompetitionRound(TestUtilities.getTestRound(1),
						TestUtilities.getTestScoreCardMap(),
						TestUtilities.getTestGolfCourse(18, 72));
		Iterator<IGolfer> golferItr = TestUtilities.getTestGolferList()
				.iterator();
		while (golferItr.hasNext()) {
			IGolfer golfer = golferItr.next();
			System.out.println(golfer.getFirstName() + " "
					+ golfer.getLastName() + "|Score "
					+ roundScores.get(golfer.getUserId()));
		}
		fail("Not yet implemented");
	}
}

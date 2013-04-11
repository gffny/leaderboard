/**
 * 
 */
package test.gffny.leaderboard.component.scheduler;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.gffny.leaderboard.utilities.TestUtilities;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.component.scheduler.impl.SimpleCompetitionScheduler;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public class TestSimpleCompetitionScheduler {
	private List<IGolfer> testList;
	private ICompetitionScheduler testScheduler;
	private String[] testTeeTimeArray;
	private Date testDate;
	private IGolfCourse testGolfCourse;
	private ICompetition testCompetition;
	private int groupSize = 4;
	private int holeSize = 18;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testList = TestUtilities.getTestGolferList();
		testScheduler = SimpleCompetitionScheduler.getInstance();
		testTeeTimeArray = TestUtilities.getTestTeeTimeArray("12:10", "10", 5);
		testDate = new Date(System.currentTimeMillis());
		testGolfCourse = TestUtilities.getTestGolfCourse(18, 72);
		testCompetition = TestUtilities.getTestCompetition();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scheduler.impl.SimpleCompetitionScheduler#SimpleCompetitionScheduler(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public final void testSimpleCompetitionScheduler() {
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scheduler.impl.SimpleCompetitionScheduler#scheduleCompetitionRound(java.util.Date, com.gffny.leaderboard.model.IGolfCourse, java.lang.String[], java.util.List, int, int)}
	 * .
	 */
	@Test
	public final void testScheduleCompetitionRound() {
		testScheduler
				.scheduleCompetitionRound(testCompetition.getCompetitionName(),
						1, testDate, testGolfCourse, testTeeTimeArray,
						testList, groupSize, holeSize);
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scheduler.AbstractCompetitionScheduler#getRoundByDate(java.util.Date)}
	 * .
	 */
	@Test
	public final void testGetRoundByDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
		try {
			Date testDate = sdf.parse("12/12/12");

			System.out.println(sdf.format(testDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scheduler.AbstractCompetitionScheduler#getRoundByID(int)}
	 * .
	 */
	@Test
	public final void testGetRoundByID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scheduler.AbstractCompetitionScheduler#getCompetitionName()}
	 * .
	 */
	@Test
	public final void testGetCompetitionName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.component.scheduler.AbstractCompetitionScheduler#isIndividualCompetition()}
	 * .
	 */
	@Test
	public final void testIsIndividualCompetition() {
		fail("Not yet implemented");
	}

}

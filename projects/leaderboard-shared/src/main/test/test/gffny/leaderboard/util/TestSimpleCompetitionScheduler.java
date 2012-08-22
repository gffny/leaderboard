/**
 * 
 */
package test.gffny.leaderboard.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.gffny.leaderboard.model.impl.CompetitionType;

import com.gffny.leaderboard.util.impl.SimpleCompetitionScheduler;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.Golfer;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 21, 2012
 *
 */
public class TestSimpleCompetitionScheduler {

	private SimpleCompetitionScheduler schedulerUnderTest;
	private List<IGolfer> golferList; 
	private ICompetitionType testType; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.schedulerUnderTest = new SimpleCompetitionScheduler();
		this.golferList = new ArrayList<IGolfer>();
		golferList.add(new Golfer(1, 1, "Test 1", "test1@leaderboard.com", "Test", "1", "Test Suite", "1"));
		golferList.add(new Golfer(2, 1, "Test 2", "test2@leaderboard.com", "Test", "2", "Test Suite", "2"));
		golferList.add(new Golfer(3, 1, "Test 3", "test3@leaderboard.com", "Test", "3", "Test Suite", "3"));
		golferList.add(new Golfer(4, 1, "Test 4", "test4@leaderboard.com", "Test", "4", "Test Suite", "4"));
		testType = new CompetitionType("Presidents Day Cup", 3, true, 4);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.util.impl.SimpleCompetitionScheduler#generateAndScheduleCompetition(java.util.List, java.lang.String, java.lang.String, java.lang.String, com.gffny.leaderboard.model.ICompetitionType, int, boolean)}.
	 */
	@Test
	public final void testGenerateAndScheduleCompetition() {
		ICompetition testCompetition = schedulerUnderTest.generateAndScheduleCompetition(golferList, "Test Comp1", "03/01/1983", "11:00", testType, 2, false);
		System.out.println(testCompetition.toString());
		System.out.println(testCompetition.getCompetitionRoundList().size());
		if(testCompetition.getCompetitionRoundList()==null || testCompetition.getCompetitionRoundList().size()==0) {
			fail("No rounds organised!");
		}
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.util.impl.SimpleCompetitionScheduler#regenerateAndScheduleCompetition(com.gffny.leaderboard.model.ICompetition, java.util.List)}.
	 */
	@Test
	public final void testRegenerateAndScheduleCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.util.impl.SimpleCompetitionScheduler#extendCompetition(com.gffny.leaderboard.model.ICompetition, java.util.List)}.
	 */
	@Test
	public final void testExtendCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.util.impl.SimpleCompetitionScheduler#generateAndScheduleCorrespondantCompetition(com.gffny.leaderboard.model.ICompetition, java.lang.String, com.gffny.leaderboard.model.ICompetitionType)}.
	 */
	@Test
	public final void testGenerateAndScheduleCorrespondantCompetition() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.util.impl.SimpleCompetitionScheduler#scheduleNextRound(com.gffny.leaderboard.model.ICompetition, java.lang.String, java.lang.String, java.util.List, int, boolean)}.
	 */
	@Test
	public final void testScheduleNextRound() {
		fail("Not yet implemented"); // TODO
	}

}

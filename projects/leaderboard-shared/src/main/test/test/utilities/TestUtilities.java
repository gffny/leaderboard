/**
 * 
 */
package test.utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.component.scheduler.impl.SimpleCompetitionScheduler;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.IScorecard;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.Golfer;
import com.gffny.leaderboard.model.impl.Scorecard;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class TestUtilities {

	public static ICompetitionScheduler getTestScheduler() {
		return SimpleCompetitionScheduler.getInstance();
	}

	public static List<IGolfer> getTestGolferList() {
		List<IGolfer> testList = new ArrayList<IGolfer>();
		testList.add(new Golfer(11, "John", "Gaffney", "gffny"));
		testList.add(new Golfer(12, "Eoin", "Barry", "eoindeb"));
		testList.add(new Golfer(13, "Mick", "Lyons", "leo"));
		testList.add(new Golfer(14, "Colm", "Caffrey", "mink"));
		testList.add(new Golfer(15, "Niall", "O'Connor", "nile"));
		testList.add(new Golfer(16, "Mick", "O'Connor", "big bear"));
		testList.add(new Golfer(17, "Dave", "Hughes", "dub"));
		testList.add(new Golfer(18, "John", "Wyrcherly", "hair"));
		testList.add(new Golfer(19, "Joe", "Regan", "chairman"));
		testList.add(new Golfer(111, "Lar", "Barden", "bardo"));
		testList.add(new Golfer(112, "Kenny", "McCarthy", "kenny"));
		testList.add(new Golfer(113, "Mike", "Canny", "canny"));
		testList.add(new Golfer(114, "Jeff", "Saunders", "jeff"));
		testList.add(new Golfer(115, "John", "Crowley", "jc"));
		testList.add(new Golfer(116, "Theo", "Foley", "theo"));
		testList.add(new Golfer(117, "Mark", "Cahillane", "markie"));
		testList.add(new Golfer(118, "Mike", "Moynihan", "mikey"));
		testList.add(new Golfer(119, "John", "Foley", "foley"));
		testList.add(new Golfer(1111, "Graham", "MacDowell", "gmac"));
		testList.add(new Golfer(1112, "Rory", "McIlroy", "roy"));
		return testList;
	}

	public static Map<Integer, IGolfer> getTestGolferMap() {
		Iterator<IGolfer> golferItr = getTestGolferList().iterator();
		Map<Integer, IGolfer> golferIdMap = new HashMap<Integer, IGolfer>();
		while (golferItr.hasNext()) {
			IGolfer golfer = golferItr.next();
			golferIdMap.put(golfer.getUserId(), golfer);
		}
		return golferIdMap;
	}

	public static String[] getTestTeeTimeArray(String firstTime,
			String interval, int number) {
		String[] testTeeTimeArray = { "12:00", "12:10", "12:20", "12:30",
				"12:40" };
		return testTeeTimeArray;
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	public static IGolfCourse getTestGolfCourse(int numberOfHoles, int par) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public static ICompetition getTestCompetition() {
		return new Competition("President's Day Cup 2012",
				"President's Day Cup 2012", 4);
	}

	/**
	 * @return
	 */
	public static ICompetitionRound getTestRound() {
		return getTestScheduler().scheduleCompetitionRound(
				getTestCompetition().getCompetitionName(), 1,
				new Date(System.currentTimeMillis()),
				getTestGolfCourse(18, 72),
				getTestTeeTimeArray("12:00", "10", 5), getTestGolferList(), 4,
				18);
	}

	/**
	 * @return
	 */
	public static Map<Integer, IScorecard> getTestScoreCardMap() {
		Map<Integer, IScorecard> scorecardMap = new HashMap<Integer, IScorecard>();
		Iterator<IGolfer> golferIterator = getTestGolferList().iterator();
		IGolfer golfer = null;
		while (golferIterator.hasNext()) {
			golfer = golferIterator.next();
			IScorecard scorecard = getTestScorecard();
			scorecardMap.put(golfer.getUserId(), scorecard);
			System.out.println(golfer.getFirstName() + " "
					+ golfer.getLastName() + "|" + scorecard.toString());
		}
		return scorecardMap;
	}

	/**
	 * @return
	 */
	public static IScorecard getTestScorecard() {
		int[] scorearray = new int[18];
		for (int i = 0; i < 18; i++) {

			scorearray[i] = randomInt(3, 10);
		}
		return new Scorecard(Scorecard.BLUES_COLOUR, randomInt(9, 33),
				scorearray, "", "01/10/2012");
	}

	/**
	 * @return
	 */
	private static int randomInt(int min, int max) {
		// http://stackoverflow.com/questions/363681/generating-random-number-in-a-range-with-java
		// Min + (int)(Math.random() * ((Max - Min) + 1)
		return min + (int) (Math.random() * ((max - min) + 1));
	}
}

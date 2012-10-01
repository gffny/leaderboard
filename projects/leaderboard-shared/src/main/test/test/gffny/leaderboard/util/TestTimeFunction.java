/**
 * 
 */
package test.gffny.leaderboard.util;

import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.util.TimeFunction;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class TestTimeFunction {

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
	 * {@link com.gffny.leaderboard.util.TimeFunction#parseString(java.lang.String)}
	 * .
	 */
	@Test
	public final void testParseString() {
		try {
			TimeFunction.parseString("01/12/2012");
			System.out.println(TimeFunction.parseString("01/12/2012")
					.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			fail("parse exception");
		}

	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.util.TimeFunction#formatDate(java.util.Date)}
	 * .
	 */
	@Test
	public final void testFormatDate() {
		System.out.println(TimeFunction.formatDate(TimeFunction.getCurrent()));
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.util.TimeFunction#getCurrent()}.
	 */
	@Test
	public final void testGetCurrent() {
		fail("Not yet implemented"); // TODO
	}

}

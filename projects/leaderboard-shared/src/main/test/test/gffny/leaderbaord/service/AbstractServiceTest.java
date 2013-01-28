/**
 * 
 */
package test.gffny.leaderbaord.service;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.service.AbstractService;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public class AbstractServiceTest extends TestCase {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(AbstractServiceTest.class);

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private AbstractService serviceUnderTest = new AbstractService();

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.service.AbstractService#logErrorReturnEmptyList(com.gffny.leaderboard.intralayer.DAOException, org.apache.log4j.Logger, java.lang.Class)}
	 * .
	 */
	@Test
	public final void testLogErrorReturnEmptyList() {
		// List<Object> blah = serviceUnderTest.logErrorReturnEmptyList(
		// new DAOException(), log, Object.class);
		// assertEquals(blah.isEmpty(), true);
	}
}

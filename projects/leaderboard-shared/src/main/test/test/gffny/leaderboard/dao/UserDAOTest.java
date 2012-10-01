/**
 * 
 */
package test.gffny.leaderboard.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.dao.IUserDAO;
import com.gffny.leaderboard.dao.factory.DAOFactory;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.model.IGolfer;

/**
 * @author jgaffney02
 * 
 */
public class UserDAOTest {

	private static Logger log = Logger.getLogger(UserDAOTest.class);

	private IUserDAO userDao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userDao = DAOFactory.getInstance().getUserDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test method for
	 * {@link test.gffny.leaderboard.dao.mysql.UserDAO#getAllGolfersList()}.
	 */
	@Test
	public void testGetAllGolfersList() {
		if (userDao == null) {
			fail();
		}
		// TODO Need to test for non-valid characters, email addresses, etc and
		// assert if they pass or not
		List<IGolfer> res;
		try {
			res = userDao.getAllGolfersList();
			log.info("testGetAllGolfersList");
			for (int i = 0; i < res.size(); i++) {
				log.info(res.get(i).toString());
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link test.gffny.leaderboard.dao.mysql.UserDAO#getAllSocietyMembersListBySocietyId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetAllSocietyMembersListBySocietyId() {
		if (userDao == null) {
			fail();
		}
		// TODO Need to test for non-valid characters, email addresses, etc and
		// assert if they pass or not
		List<IGolfer> res;
		try {
			res = userDao.getAllSocietyMembersListBySocietyId("0000000001");
			log.info("testGetAllSocietyMembersListBySocietyId");
			for (int i = 0; i < res.size(); i++) {
				log.info(res.get(i).toString());
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link test.gffny.leaderboard.dao.mysql.UserDAO#getGolferBySocietyMemberId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetGolferBySocietyMemberId() {
		if (userDao == null) {
			fail();
		}
		log.info("testGetGolferBySocietyMemberId");
		// TODO Need to test for non-valid characters, email addresses, etc and
		// assert if they pass or not
		IGolfer res;
		try {
			res = userDao.getGolferBySocietyMemberId("0000000001");
			log.info(res.toString());
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link test.gffny.leaderboard.dao.mysql.UserDAO#getGolferBySocietyMemberId(java.lang.String)}
	 * .
	 */
	@Test
	public void testAddGolfer() {
		if (userDao == null) {
			fail();
		}
		log.info("testAddGolfer");
		// TODO Need to test for non-valid characters, email addresses, etc and
		// assert if they pass or not
		DAOResult res;
		try {
			res = userDao.addGolfer("john@isobar.com", "gffny", "John",
					"Gaffney");
			if (res == null || res.isSuccessful() == false) {
				fail();
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsRegisteredEmailAddress() {
		if (userDao == null) {
			fail();
		}
		log.info("testIsRegisteredEmailAddress");
		// TODO Need to test for non-valid characters, email addresses, etc and
		// assert if they pass or not
		boolean res = false;
		try {
			res = userDao.isRegisteredEmailAddress("john@isobar.com");
			if (res == true) {
				fail();
			}
			res = userDao.isRegisteredEmailAddress("gaffney.ie@gmail.com");
			if (res == false) {
				fail();
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsRegisteredProfileHandle() {
		if (userDao == null) {
			fail();
		}
		log.info("testIsRegisteredEmailAddress");
		// TODO Need to test for non-valid characters, email addresses, etc and
		// assert if they pass or not
		boolean res = false;
		try {
			res = userDao.isRegisteredProfileHandle("gaffney");
			if (res == true) {
				fail();
			}
			res = userDao.isRegisteredProfileHandle("gffny");
			if (res == false) {
				fail();
			}
		} catch (DAOException e) {
			fail(e.getMessage());
		}
	}

}

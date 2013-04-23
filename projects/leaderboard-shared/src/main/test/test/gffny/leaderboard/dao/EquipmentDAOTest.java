/**
 * 
 */
package test.gffny.leaderboard.dao;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.dao.mysql.EquipmentDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.model.IGolfBag;

/**
 * @author John Gaffney (john@gffny.com) Apr 13, 2013
 * 
 */

public class EquipmentDAOTest extends TestCase {

	@Autowired
	private EquipmentDAO equipmentDao = new EquipmentDAO();

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.gffny.leaderboard.dao.mysql.EquipmentDAO#getGolfBagByUserId(int)}
	 * .
	 * 
	 * @throws DAOException
	 * @throws NumberFormatException
	 */
	@Test
	public final void testGetGolfBagByUserId() throws NumberFormatException,
			DAOException {
		IGolfBag golfBag = equipmentDao.getGolfBagByUserId(Integer
				.parseInt("1"));
		assertNotNull(golfBag);
	}

}

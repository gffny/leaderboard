/**
 * 
 */
package test.gffny.leaderboard.dao;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gffny.leaderboard.dao.IGolfCourseDAO;
import com.gffny.leaderboard.dao.factory.DAOFactory;
import com.gffny.leaderboard.intralayer.DAOException;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 14, 2012
 *
 */
public class TestAbstractMongoDAO {
	
	private IGolfCourseDAO mongo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mongo  = DAOFactory.getInstance().getGolfCourseDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.gffny.leaderboard.dao.mongodb.AbstractMongoDAO#getCollection(java.lang.String)}.
	 */
	@Test
	public final void testGetCollection() {
		try {
			mongo.getCourseById("blah");
		} catch (DAOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}

/**
 * 
 */
package utility.gffny.leaderboard.courseimages;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John Gaffney (john@gffny.com) Apr 29, 2013
 * 
 */
public class ImageLoaderTest extends TestCase {

	private ImageLoader imageLoader;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		imageLoader = ImageLoader.getInstance();
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
	 * {@link utility.gffny.leaderboard.courseimages.ImageLoader#loadImage(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testLoadImage() {
		imageLoader
				.loadImage(
						"C:\\dev\\leaderboard\\projects\\leaderboard-mobile\\WebContent\\resources\\images\\course\\fresh-pond\\",
						"fresh-pond-h1");
		imageLoader
				.loadImage(
						"C:\\dev\\leaderboard\\projects\\leaderboard-mobile\\WebContent\\resources\\images\\course\\fresh-pond\\",
						"fresh-pond-h2");
	}

	/**
	 * Test method for
	 * {@link utility.gffny.leaderboard.courseimages.ImageLoader#getImage(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetImage() {
		fail("Not yet implemented"); // TODO
	}

}

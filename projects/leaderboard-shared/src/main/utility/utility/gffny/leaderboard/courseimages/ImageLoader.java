/**
 * 
 */
package utility.gffny.leaderboard.courseimages;

import java.io.File;
import java.io.IOException;

import com.gffny.leaderboard.dao.mongodb.AbstractMongoDAO;
import com.mongodb.DBCursor;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * @author John Gaffney (john@gffny.com) Apr 29, 2013
 * 
 */
public class ImageLoader extends AbstractMongoDAO {

	/**
	 *
	 */
	private static final String GIF = ".gif";

	public static ImageLoader getInstance() {
		return new ImageLoader();
	}

	/**
	 * 
	 */
	protected ImageLoader() {

	}

	/**
	 * 
	 */
	public GridFSDBFile getImage(String imageName) {
		// create a "photo" namespace
		GridFS gfsPhoto = new GridFS(getDatabase(), "fresh-pond");

		// get image file by it's filename
		GridFSDBFile imageForOutput = gfsPhoto.findOne(imageName);
		return imageForOutput;
	}

	/**
	 * 
	 * @param imageLocation
	 */
	public void loadImage(String imageFolder, String fileName) {
		try {
			File imageFile = new File(imageFolder + fileName + ImageLoader.GIF);

			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(getDatabase(),
					COURSE_MAP_IMAGE_COLLECTION);

			// get image file from local drive
			GridFSInputFile gfsFile;

			gfsFile = gfsPhoto.createFile(imageFile);

			// set a new filename for identify purpose
			gfsFile.setFilename(fileName);

			// save the image file into mongoDB
			gfsFile.save();

			// print the result
			DBCursor cursor = gfsPhoto.getFileList();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			// get image file by it's filename
			// GridFSDBFile imageForOutput = gfsPhoto.findOne("test");

			// save it into a new image file
			// imageForOutput.writeTo("c:\\java.jpg");

			// remove the image file from mongoDB
			// gfsPhoto.remove(gfsPhoto.findOne(newFileName));

			System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

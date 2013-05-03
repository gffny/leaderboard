/**
 * 
 */
package com.gffny.leaderboard.dao.mongodb.dbo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.impl.GolfCourse;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author John Gaffney (john@gffny.com) Jan 21, 2013
 * 
 */
public class CourseDBO {
	// FILL IN WITH USEFUL METHODS
	// getGolfCourse
	// getGolfHoles

	/**
	 * 
	 */
	private BasicDBObject courseObject;

	/**
	 * 
	 * @param dbObject
	 */
	public CourseDBO(DBObject dbObject) {
		courseObject = (BasicDBObject) dbObject;
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return courseObject.getString("_id");
	}

	/**
	 * 
	 * @return
	 */
	public String getCourseName() {
		return courseObject.getString("name");
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return courseObject.getString("description");
	}

	/**
	 * 
	 * @return
	 */
	public String getSlopeIndex() {
		return courseObject.getString("slope");
	}

	/**
	 * 
	 * @param teeColour
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getHoleImageList() {
		return (LinkedHashMap<String, String>) courseObject.get("holeMapList");
	}

	/**
	 * 
	 * @param teeColour
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> getHoleMap(String teeColour) {
		return (LinkedHashMap<String, Map<String, String>>) courseObject
				.get(teeColour);
	}

	/**
	 * 
	 * @param teeColour
	 * @return
	 */
	public IGolfCourse getGolfCourse(String teeColour) {

		Map<String, Map<String, String>> holeMap = getHoleMap(teeColour);
		if (holeMap != null) {
			int[] teeDistanceArray = new int[holeMap.size()];
			int[] holeIndexArray = new int[holeMap.size()];
			int[] holeParArray = new int[holeMap.size()];
			int coursePar = 0;
			for (int i = 0; i < holeMap.size(); i++) {
				Map<String, String> hole = holeMap.get("h" + (i + 1));
				if (hole == null) {
					// TODO handle this more appropriately
					return null;
				}
				try {
					teeDistanceArray[i] = Integer
							.parseInt(hole.get("distance"));
					holeIndexArray[i] = Integer.parseInt(hole.get("index"));
					holeParArray[i] = Integer.parseInt(hole.get("par"));
					coursePar += holeParArray[i];
				} catch (NumberFormatException e) {
					// TODO handle this more appropriately
					return null;
				}
			}
			return new GolfCourse(getId(), getCourseName(), coursePar, "blah",
					teeColour, holeParArray, holeIndexArray, teeDistanceArray);
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getTeeColourList() {
		try {
			List<String> teeColourList = (List<String>) courseObject
					.get("teeColourList");
			return teeColourList;
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}
}
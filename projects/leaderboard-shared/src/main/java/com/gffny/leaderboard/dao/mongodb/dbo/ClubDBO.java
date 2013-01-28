/**
 * 
 */
package com.gffny.leaderboard.dao.mongodb.dbo;

import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author John Gaffney (john@gffny.com) Jan 23, 2013
 * 
 */
public class ClubDBO {

	/**
	 *
	 */
	public static final String LOCATION_ADDR_LINE_ONE = "addrLn1";
	/**
	 *
	 */
	public static final String LOCATION_ADDR_LINE_TWO = "addrLn2";
	/**
	 *
	 */
	public static final String LOCATION_CITY = "city";
	/**
	 *
	 */
	public static final String LOCATION_STATE = "state";
	/**
	 *
	 */
	public static final String LOCATION_COUNTRY = "country";
	/**
	 *
	 */
	public static final String LOCATION_ZIP = "zip";
	/**
	 *
	 */
	public static final String LOCATION_FORMATTED_ALL = "formattedAll";
	/**
	 * 
	 */
	public static final String LONGITUDE = "longitude";
	/**
	 * 
	 */
	public static final String LATITUDE = "latitude";

	/*
	 * 
	 */
	private BasicDBObject clubObject;
	private Map<String, String> locationMap;
	private Map<String, String> gpsMap;

	/**
	 * 
	 * @param dbObject
	 */
	@SuppressWarnings("unchecked")
	public ClubDBO(DBObject dbObject) {
		clubObject = (BasicDBObject) dbObject;
		locationMap = (Map<String, String>) clubObject.get("location");
		gpsMap = ((Map<String, Map<String, String>>) clubObject.get("location"))
				.get("gps");
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return clubObject.getString("_id");
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return clubObject.getString("name");
	}

	/**
	 * @see {@link ClubDBO.LONGITUDE}, {@link ClubDBO.LATITUDE}
	 * @param coordinateType
	 * @return
	 */
	public String getGPSCoordinate(String coordinateType) {
		String gpsCoordinate = "";
		if (gpsMap != null) {
			if (coordinateType.compareTo(ClubDBO.LONGITUDE) == 0) {
				gpsCoordinate = gpsMap.get("long");
			} else if (coordinateType.compareTo(ClubDBO.LATITUDE) == 0) {
				gpsCoordinate = gpsMap.get("lat");
			} else {
				gpsCoordinate = gpsMap.get("lat") + ", " + gpsMap.get("long");
			}
		} else {
			// TODO handle an error state here!
		}
		return gpsCoordinate;
	}

	/**
	 * 
	 * @return
	 */
	public String getWebsite() {
		return clubObject.getString("website");
	}

	/**
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return clubObject.getString("phone");
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return clubObject.getString("description");
	}

	/**
	 * 
	 * @param addressType
	 * @return
	 */
	public String getAddress(String addressType) {
		String address = "";
		if (locationMap != null) {
			if (addressType.compareTo(ClubDBO.LOCATION_ADDR_LINE_ONE) == 0) { // adrLn1
				address = locationMap.get("street one");
			} else if (addressType.compareTo(ClubDBO.LOCATION_ADDR_LINE_TWO) == 0) { // adrLn2
				address = locationMap.get("street two");
			} else if (addressType.compareTo(ClubDBO.LOCATION_CITY) == 0) { // city
				address = locationMap.get("city");
			} else if (addressType.compareTo(ClubDBO.LOCATION_STATE) == 0) { // state
				address = locationMap.get("state");
			} else if (addressType.compareTo(ClubDBO.LOCATION_COUNTRY) == 0) { // country
				address = locationMap.get("country");
			} else if (addressType.compareTo(ClubDBO.LOCATION_ZIP) == 0) { // zip
				address = locationMap.get("zip");
			} else if (addressType.compareTo(ClubDBO.LOCATION_FORMATTED_ALL) == 0) { // formattedAll
				address = locationMap.get("street one");
				address += locationMap.get("street two");
				address += locationMap.get("city");
				address += locationMap.get("state");
				address += locationMap.get("country");
				address += locationMap.get("zip");
			}
		} else {
			// TODO handle an error state here!
		}
		return address;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getCourseNameList() {
		return (List<String>) clubObject.get("courseNameList");
	}

	/**
	 * @param courseName
	 * @return
	 */
	public CourseDBO getCourse(String courseName) {
		BasicDBObject basicDBO = (BasicDBObject) ((BasicDBObject) clubObject
				.get("courseList")).get(courseName);
		return new CourseDBO(basicDBO);
	}
}

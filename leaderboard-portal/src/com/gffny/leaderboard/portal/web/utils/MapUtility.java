package com.gffny.leaderboard.portal.web.utils;

import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.web.model.GolferMap;

public class MapUtility {

	public static GolferMap convertGolfer(IGolfer golfer) {
		GolferMap golferMap = new GolferMap();
		golferMap.setFirstName(golfer.getFirstName());
		golferMap.setLastName(golfer.getLastName());
		golferMap.setProfileHandle(golfer.getProfileHandle());
		golferMap.setLocation(golfer.getLocation());
		golferMap.setHandicap(golfer.getHandicap());
		golferMap.setEmailAddress(golfer.getEmailAddress());
		return golferMap;
	}
}

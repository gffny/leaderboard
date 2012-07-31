package com.gffny.leaderboard.portal.web.utils;

import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.IScorecard;
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

	public static List<ScorecardMap> convertScorecardList(
			List<IScorecard> scorecardList) {
		List<ScorecardMap> scorecardMapList = new ArrayList<ScorecardMap>();
		for(int i=0; i<scorecardList.size(); i++) {
			IScorecard currentScorecard = scorecardList.get(i);
			String[] scoreArray = {currentScorecard.getScoreOnHole(IScorecard.HOLE_1), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_2),
					currentScorecard.getScoreOnHole(IScorecard.HOLE_3), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_4), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_5), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_6), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_7), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_8), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_9), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_10), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_11),
					currentScorecard.getScoreOnHole(IScorecard.HOLE_12),
					currentScorecard.getScoreOnHole(IScorecard.HOLE_13), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_14), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_15), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_16), 
					currentScorecard.getScoreOnHole(IScorecard.HOLE_17),
					currentScorecard.getScoreOnHole(IScorecard.HOLE_18)};
			scorecardMapList.add(new ScorecardMap(
					scoreArray,//String[] scoreArray
					currentScorecard.getGrossScore(),//String grossScore
					currentScorecard.getTeesPlayedOffColour(),//String teesPlayedOff
					currentScorecard.getScorecardNotes(),//String scorecardNotes
					currentScorecard.getScorecardDate()//String scorecardDate
					));
		}
		return scorecardMapList;
	}
}

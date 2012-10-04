/**
 * 
 */
package com.gffny.leaderboard.component.scheduler.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.component.scheduler.IParallelCompetitionScheduler;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetition.IGolfGroup;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.CompetitionRound;
import com.gffny.leaderboard.model.impl.GolfGroup;
import com.gffny.leaderboard.util.TimeFunction;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public class SimpleCompetitionScheduler implements ICompetitionScheduler,
		IParallelCompetitionScheduler {

	private static ICompetitionScheduler INSTANCE = null;
	private static Logger log = Logger
			.getLogger(SimpleCompetitionScheduler.class);

	/**
	 * Privately override constructor to facilitate factory pattern
	 */
	private SimpleCompetitionScheduler() {

	}

	/**
	 * Factory method
	 * 
	 * @return
	 */
	public static ICompetitionScheduler getInstance() {
		if (INSTANCE == null) {
			log.debug("creating instance of SimpleCompetitionScheduler()");
			INSTANCE = new SimpleCompetitionScheduler();
		}
		return INSTANCE;
	}

	/**
	 * @see com.gffny.leaderboard.component.scheduler.AbstractCompetitionScheduler#scheduleCompetitionRound(java.util.Date,
	 *      com.gffny.leaderboard.model.IGolfCourse, java.lang.String[],
	 *      java.util.List, int, int)
	 */
	@Override
	public ICompetitionRound scheduleCompetitionRound(String competitionName,
			int roundNumber, Date roundDate, IGolfCourse roundCourse,
			String[] teeTimeArray, List<IGolfer> competitorList,
			int playerGroupSize, int numberOfHoles) {
		// Generate a round name based off competitionName
		String roundName = "";
		// Great a new list to handle the groups
		List<IGolfGroup> groupList = new ArrayList<IGolfGroup>();
		Map<IGolfGroup, Date> teeTimeMap = new HashMap<ICompetition.IGolfGroup, Date>();
		if (competitionName != null && roundNumber > 0) {
			roundName = getRoundName(competitionName, roundNumber);
			// TODO set the round name
		} else {
			// TODO else create competition round name/date or throw an error
			if (competitionName == null) {
				log.error("competitionName is null");
			}
			if (roundNumber < 1) {
				log.error("round number is less that 1");
			}
		}
		// Generate the groupings for the round
		if (competitorList != null && playerGroupSize > 0
				&& playerGroupSize < 5) {
			if (competitorList.size() % playerGroupSize == 0) {
				// Shuffle the competitor list
				Collections.shuffle(competitorList);
				// get the competitorList iterator
				Iterator<IGolfer> competitorItr = competitorList.iterator();
				int groupNumber = 0;
				// loop through the competitors and assign them to groups
				while (competitorItr.hasNext()) {
					// create golf group
					IGolfGroup scheduleGroup = new GolfGroup(getGroupName(
							roundName, groupNumber), playerGroupSize);
					for (int i = 0; i < playerGroupSize; i++) {
						// add golfer to group
						scheduleGroup.addGolfer(competitorItr.next());
					}
					// add group to the competition group list
					groupList.add(scheduleGroup);
					if (groupNumber <= teeTimeArray.length) {
						try {
							teeTimeMap.put(scheduleGroup, TimeFunction
									.parseTeeTimeDate(roundDate,
											teeTimeArray[groupNumber]));
						} catch (ParseException e) {
							// TODO handle bad tee time data
							log.error("parse error on tee time array: "
									+ teeTimeArray.toString());
						}
					} else {
						// TODO handle size of teetimearray is incorrect for
						log.error("not enough tee times ("
								+ teeTimeArray.length
								+ ") for number of groups (" + groupNumber
								+ ")");
					}
					// number of golfers
					groupNumber++;
				}
			} else {
				// TODO else throw an error or handle irregular sized groups
				log.error("odd size competitor list length ("
						+ competitorList.size() + ") for group size ("
						+ playerGroupSize + ")");
			}
		} else {
			if (competitorList == null) {
				log.error("competitorList is null");
			}
			if (playerGroupSize < 1) {
				log.error("player group size is less than one");
			}
			if (playerGroupSize > 4) {
				log.error("player group size is greater than four");
			}
		}
		ICompetitionRound competitionRound = new CompetitionRound(roundName,
				roundNumber, roundDate, roundCourse.getCourseId(), groupList,
				teeTimeMap);
		log.debug("Competition Round: " + competitionRound.toString());
		return competitionRound;
	}

	/**
	 * @see com.gffny.leaderboard.component.scheduler.IParallelCompetitionScheduler#scheduleParallelCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound)
	 */
	@Override
	public ICompetitionRound scheduleParallelCompetitionRound(
			String competitionName, ICompetitionRound competitionRoundToParallel) {
		ICompetitionRound parallelRound = competitionRoundToParallel.clone();
		parallelRound.setRoundName(getRoundName(competitionName,
				competitionRoundToParallel.getRoundNumber()));
		return parallelRound;
	}

	/**
	 * @param roundName
	 * @param groupNumber
	 * @return
	 */
	@Override
	public String getGroupName(String roundName, int groupNumber) {
		return roundName + "|group " + groupNumber;
	}

	/**
	 * 
	 * @param competitionName
	 * @param roundNumber
	 * @return
	 */
	@Override
	public String getRoundName(String competitionName, int roundNumber) {
		return competitionName + "|round " + roundNumber;
	}
}

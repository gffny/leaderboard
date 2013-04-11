/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.intralayer.ActionType;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.utils.StaticAssetTool;
import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;
import com.gffny.leaderboard.service.IUserService;
import com.gffny.leaderboard.util.ApplicationConfiguration;
import com.gffny.leaderboard.util.DateUtils;
import com.gffny.leaderboard.util.StringUtils;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController extends AbstractController {

	private static Logger log = Logger.getLogger(CompetitionController.class);

	@Autowired
	private IAuthorisationService authorisationService;
	@Autowired
	private ICompetitionService competitionService;
	@Autowired
	private IGolfCourseService golfCourseService;
	@Autowired
	private IScorecardService scorecardService;
	@Autowired
	private IUserService userService;

	// Map<roundNumber, [roundName, roundDate, groupSize, courseId]
	private static int ROUND_NAME_POSTITION = 0;
	private static int ROUND_DATE_POSTITION = 1;
	private static int GROUP_SIZE_POSTITION = 2;
	private static int COURSE_ID_POSTITION = 3;

	private static String ROUND_NAME_HANDLE = "rndNm";
	private static String ROUND_DATE_HANDLE = "rndDt";
	private static String GROUP_SIZE_HANDLE = "rndGrpSz";
	private static String COURSE_ID_HANDLE = "rndCrs";

	/*
	 * Controller methods to create a competition #############################
	 * 1. create/inital #######################################################
	 * 2. create/rounds #######################################################
	 * 3. create/confirm ######################################################
	 * 4. create/save #########################################################
	 * 
	 * Work Flow - User enters competition name, number of rounds, scoring
	 * system, and visibility on the Initial screen. The user then dictates the
	 * rounds in the Rounds screen. The final controller method persists the
	 * round data. Any confirmation screen should be handled outside of a
	 * controller call.
	 */

	/**
	 * return the initial screen to create a company. the screen shall request
	 * the name, visibility, and the number of rounds of a competition
	 * 
	 * @return
	 */
	@RequestMapping("/create/initial")
	public ModelAndView competitionInitial(@RequestBody String body,
			HttpSession session, HttpServletRequest request) {

		String userId = getServletData().getGolferId();

		ModelAndView model = null;
		if (userId != null) {

			if (authorisationService.isPermitted(userId,
					ActionType.CREATE_COMPETITION)) {
				try {
					// create the model and populate with the required info
					model = new ModelAndView("competition/competitioncreate");
					model.addObject("competitionVisibility",
							ApplicationConfiguration
									.getCompetitionVisibilityList());
					model.addObject("competitionScoringSystemList",
							competitionService
									.getCompetitionScoringSystemList());
					model.addObject("staticAsset", new StaticAssetTool());
				} catch (ServiceException e) {
					return getErrorModel(e);
				}
			} else { // handle non authorised user requests
				return getErrorModel("User is not authorised for this action");
			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId
			return getErrorModel("Null value for userId. Contact the application administrator");
		}
		return model;
	}

	// Initial should have parameters that allow the UI to display courses
	// specific to a locality, or default to a users preferred location
	// public ModelAndView competitionRounds(String.. state)
	// public ModelAndView competitionRounds(String geoLat, String geoLong)
	// public ModelAndView competitionRounds(String clubName)
	// public ModelAndView competitionRounds()
	/**
	 * Default UserCourse List
	 * 
	 * @return
	 */
	@RequestMapping("/create/rounds")
	public ModelAndView competitionRounds(
			@RequestParam("competitionName") String competitionName,
			@RequestParam("competitionRounds") String competitionRounds,
			@RequestParam("competitionVisibility") String competitionVisibility,
			@RequestParam("competitionScoringSystem") String competitionScoringSystem) {

		String userId = getServletData().getGolferId();

		ModelAndView model = null;
		if (userId != null && competitionName != null
				&& competitionScoringSystem != null
				&& competitionRounds != null && competitionVisibility != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.CREATE_COMPETITION)) {
				try {
					// get courses related to the user's default location
					List<IGolfCourse> userCourseList = golfCourseService
							.getAllGolfCoursesList();
					// get the user's favourite course
					List<IGolfCourse> favouriteList = userService
							.getGolferFavouriteClub(userId);
					// scrub the list
					for (int i = 0; i < favouriteList.size(); i++) {
						if (!userCourseList.contains(favouriteList.get(i))) {
							userCourseList.add(favouriteList.get(i));
						}
					}
					model = new ModelAndView("competition/competitionrounds");
					int numberOfRounds = Integer.parseInt(competitionRounds);
					ICompetition competition = competitionService
							.createCompetition(competitionName,
									competitionScoringSystem,
									competitionVisibility, numberOfRounds);
					competitionService.saveCompetition(competition);
					model.addObject("competition", competition);
					model.addObject("numberOfRounds", numberOfRounds);
					model.addObject("golferList", userService
							.getSocietyMemberListAssociatedWithUser(userId));
					model.addObject("courseList", userCourseList);
				} catch (ServiceException e) {
					getErrorModel(e);
				}
			} else { // handle non authorised user requests
				return getErrorModel("User is not authorised for this action");
			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId
			return getErrorModel("Null value for userId. Contact the application administrator");
		}
		return model;
	}

	/**
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping("/create/persist")
	public ModelAndView competitionPesist(
			@RequestParam("competitionId") String competitionId) {

		String userId = getServletData().getGolferId();
		Map<String, String[]> parameterMap = getServletData().getParameterMap();
		// Map<roundNumber, [roundName, roundDate, groupSize, courseId]
		Map<String, String[]> roundMap = new HashMap<String, String[]>();
		Iterator<String> parameterKeyItr = parameterMap.keySet().iterator();
		while (parameterKeyItr.hasNext()) {
			String parameterKey = parameterKeyItr.next();
			roundMap = processCompetitionRoundParameter(parameterKey,
					parameterMap.get(parameterKey), roundMap);
		}

		ModelAndView model = null;
		if (userId != null && roundMap != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.CREATE_COMPETITION)) {
				try {
					model = new ModelAndView(
							"competition/competitionconfirmation");
					Iterator<String> roundItr = roundMap.keySet().iterator();
					ICompetition competition = competitionService
							.getCompetition(competitionId);
					if (competition != null) {
						while (roundItr.hasNext()) {
							String roundNumber = roundItr.next();
							String[] roundDetails = roundMap.get(roundNumber);
							if (roundDetails != null) {
								// get the round date
								Date roundDate = DateUtils.parseDateOrNull(
										roundDetails[ROUND_DATE_POSTITION],
										DateUtils.CHART_DATE_FORMAT
												.getPattern());
								// create the round
								ICompetitionRound competitionRound = competitionService
										.createCompetitionRound(
												Integer.parseInt(roundNumber),
												roundDetails[ROUND_NAME_POSTITION],
												roundDate,
												Integer.parseInt(roundDetails[GROUP_SIZE_POSTITION]),
												roundDetails[COURSE_ID_POSTITION]);
								// add the round to the competition
								competition
										.addCompetitionRound(competitionRound);
							}
						}
						// save the round
						competitionService.saveCompetition(competition);
						model.addObject("competition", competition);
						if (competition.getCompetitionVisibility()
								.equalsIgnoreCase(
										ICompetition.PUBLIC_VISIBILITY)) {
							model.addObject("competitionUrl",
									createCompetitionUrl(competition));
						}
					} else {
						return getErrorModel("no competition being returned for id: "
								+ competitionId);
					}
				} catch (ServiceException e) {
					return getErrorModel(e);
				}
			} else { // handle non authorised user requests
				return getErrorModel("User is not authorised for this action");
			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId
			return getErrorModel("Null value for userId. Contact the application administrator");
		}
		return model;
	}

	/* ****************************** HELPER METHODS ******************** */

	/**
	 * @param competition
	 * @return
	 */
	private String createCompetitionUrl(ICompetition competition) {
		// TODO move this method to a utility class for greater
		// reuse potential
		return "http://localhost:8080/leaderboard/dashboard/competition/"
				+ competition.getCompetitionId();
	}

	/**
	 * 
	 * @param parameterKey
	 * @param parameterValue
	 * @param roundMap
	 * @return
	 */
	private Map<String, String[]> processCompetitionRoundParameter(
			String parameterKey, String[] parameterValue,
			Map<String, String[]> roundMap) {
		// parameterKey should have the format
		// competitionId.roundNumber.inputName
		if (parameterKey != null && parameterValue != null
				&& parameterValue[0] != null && roundMap != null) {

			int firstMarkerLocation = parameterKey.indexOf(".");
			int secondMarkerLocation = parameterKey.indexOf(".",
					firstMarkerLocation + 1);
			if (firstMarkerLocation != -1 && secondMarkerLocation != -1) {
				// String competitionId = parameterKey.substring(0,
				// firstMarkerLocation);
				String roundNumber = parameterKey.substring(
						firstMarkerLocation + 1, secondMarkerLocation);
				String parameterType = parameterKey
						.substring(secondMarkerLocation + 1);
				String[] roundDetails = roundMap.get(roundNumber);
				if (roundDetails == null) {
					roundDetails = new String[4];
				}
				if (parameterType.equalsIgnoreCase(ROUND_NAME_HANDLE)) {
					roundDetails[ROUND_NAME_POSTITION] = parameterValue[0];
				} else if (parameterType.equalsIgnoreCase(ROUND_DATE_HANDLE)) {
					roundDetails[ROUND_DATE_POSTITION] = parameterValue[0];
				} else if (parameterType.equalsIgnoreCase(GROUP_SIZE_HANDLE)) {
					roundDetails[GROUP_SIZE_POSTITION] = parameterValue[0];
				} else if (parameterType.equalsIgnoreCase(COURSE_ID_HANDLE)) {
					roundDetails[COURSE_ID_POSTITION] = parameterValue[0];
				}
				roundMap.put(roundNumber, roundDetails);
			}
		}
		return roundMap;
	}

	/**
	 * 
	 * @param parameterName
	 * @param scoreValue
	 * @param scoreMap
	 * @return
	 */
	private Map<String, String[]> processScoreParameter(String parameterName,
			String scoreValue, Map<String, String[]> scoreMap) {
		// if the scoreMap is null, create a new instance of HashMap
		if (scoreMap == null) {
			scoreMap = new HashMap<String, String[]>();
		}
		// check if the parameters are valid
		if (scoreValue != null && parameterName != null
				&& StringUtils.isNotBlank(scoreValue)
				&& parameterName.indexOf('.') > 0) {
			// break the parameterName into its constituent parts
			// golferId.holeNumber
			String golferId = parameterName.substring(0,
					parameterName.indexOf('.'));
			// get the scorecard from the score map
			String[] golferScorecard = scoreMap.get(golferId);
			// check if the scorecard is valid
			if (golferScorecard == null) {
				// if not, instantiate the scorecard
				golferScorecard = new String[18];
			}
			try {
				// get the holeNumber
				int holeNumber = Integer.valueOf(parameterName
						.substring(parameterName.indexOf('.') + 1));
				if (holeNumber >= 1 && holeNumber <= 18) {
					golferScorecard[(holeNumber - 1)] = scoreValue;
				} else {
					golferScorecard[(holeNumber - 1)] = "-1";
				}
			} catch (NumberFormatException e) {
				// TODO handle the number format exception
				logger.error("error formating "
						+ parameterName.substring(parameterName.indexOf('.') + 1)
						+ " as an integer");
			}
			scoreMap.put(golferId, golferScorecard);
		}
		return scoreMap;
	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping("/scorecard/select")
	public ModelAndView selectCompetitionRound() throws ServiceException {

		String userId = getServletData().getGolferId();
		ModelAndView model = null;
		if (userId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.ENTER_SCORECARD)) {
				// potentially get the userId from the servlet data
				model = new ModelAndView("competition/scorecardselect");
				// Get the competition list
				List<ICompetition> competitionList = competitionService
						.getCompetitionListForUserId(userId);
				model.addObject("competitionList", competitionList);
				return model;
			} else { // handle non authorised user requests
				log.error("user is not permitted to ENTER SCORECARD");
			}
		} else { // handle issue with null or invalid userid/competitonid
			if (userId == null) {
				log.error("userId is null");
			}
		}
		return model;
	}

	/**
	 * 
	 * @param competitionRoundId
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping("/scorecard/enter")
	public ModelAndView enterScorecard(
			@RequestParam("competitionRoundId") String competitionRoundId,
			@RequestParam("userId") String userId) throws ServiceException {

		ModelAndView model = null;
		if (userId != null && competitionRoundId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.ENTER_SCORECARD)) {
				model = new ModelAndView("competition/scorecardenter");
				ICompetitionRound competitionRound = competitionService
						.getCompetitionRound(competitionRoundId);
				List<IGolfer> competitorList = competitionService
						.getCompetitorListForCompetition(String
								.valueOf(competitionRound.getCompetitionId()));
				List<IGolfCourse> golfCourse = golfCourseService
						.getGolfCourseById(String.valueOf(competitionRound
								.getCourseId()));
				model.addObject("competitionRound", competitionRound);
				model.addObject("competitorList", competitorList);
				if (!golfCourse.isEmpty()) {
					model.addObject("golfCourse", golfCourse.get(0));
				}
			} else { // handle non authorised user requests

			}
		} else { // handle issue with null or invalid
					// userid/competitonid/competitionRoundId

		}
		return model;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/scorecard/save")
	public ModelAndView saveScorecard() {

		String userId = getServletData().getGolferId();
		String competitionRoundId = getServletData().getCompetitionRoundId();

		ModelAndView model = null;
		if (userId != null && competitionRoundId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.ENTER_SCORECARD)) {
				Map<String, String[]> parameterMap = getServletData()
						.getRequest().getParameterMap();
				Enumeration<String> parameterNameEnum = getServletData()
						.getRequest().getParameterNames();
				Map<String, String[]> scoreMap = new HashMap<String, String[]>();
				while (parameterNameEnum.hasMoreElements()) {
					String parameterName = parameterNameEnum.nextElement();
					scoreMap = processScoreParameter(parameterName,
							parameterMap.get(parameterName)[0], scoreMap);
				}
				try {
					scorecardService.submitScorecardMapForCompetitionRound(
							competitionRoundId, scoreMap);
				} catch (ServiceException e) {
					// TODO handle service exception
					e.printStackTrace();
				}
			} else { // handle non authorised user requests

			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId

		}
		return model;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/score/select")
	public ModelAndView selectScoreCompetition() {

		String userId = getServletData().getGolferId();

		ModelAndView model = null;
		if (userId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.SCORE_COMPETITION)) {
				try {
					model = new ModelAndView("competition/scoreselect");
					model.addObject("competitionList", competitionService
							.getCompetitionListForUserId(userId));
				} catch (ServiceException e) {
					// TODO handle service exception
					e.printStackTrace();
				}
			} else { // handle non authorised user requests

			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId

		}
		return model;
	}

	/**
	 * 
	 * @param competitionRoundId
	 * @return
	 */
	@RequestMapping("/score/round")
	public ModelAndView scoreRound(
			@RequestParam("competitionRoundId") String competitionRoundId) {

		String userId = getServletData().getGolferId();

		ModelAndView model = null;
		if (userId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.SCORE_COMPETITION)) {
				try {
					model = new ModelAndView("competition/roundresults");
					model.addObject("resultMap",
							competitionService.scoreRound(competitionRoundId));
				} catch (ServiceException e) {
					// TODO handle service exception
					e.printStackTrace();
				}
			} else { // handle non authorised user requests

			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId

		}
		return model;
	}

	/**
	 * 
	 * @param competitionId
	 * @return
	 */
	@RequestMapping("/score/competition")
	public ModelAndView scoreCompetition(
			@RequestParam("competitionId") String competitionId) {

		String userId = getServletData().getGolferId();

		ModelAndView model = null;
		if (userId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.SCORE_COMPETITION)) {
				try {
					model = new ModelAndView("competition/competitionresults");

					for (ICompetitionRound competitionRound : competitionService
							.getCompetition(competitionId)
							.getCompetitionRoundList()) {
						model.addObject(competitionRound.getRoundIdAsString(),
								competitionService.scoreRound(competitionRound
										.getRoundIdAsString()));
					}
					model.addObject("resultMap",
							competitionService.scoreCompetition(competitionId));
				} catch (ServiceException e) {
					// TODO handle service exception
					e.printStackTrace();
				}
			} else { // handle non authorised user requests

			}
		} else {
			// handle issue with null or invalid
			// userid/competitonid/competitionRoundId

		}
		return model;
	}
}
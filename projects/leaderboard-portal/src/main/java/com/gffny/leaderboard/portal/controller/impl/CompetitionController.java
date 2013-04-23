/**
 * 
 */
package com.gffny.leaderboard.portal.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.intralayer.ActionType;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.JSONable;
import com.gffny.leaderboard.portal.controller.ICompetitionCompetitiorController;
import com.gffny.leaderboard.portal.controller.ICompetitionManagementController;
import com.gffny.leaderboard.portal.controller.abst.AbstractController;
import com.gffny.leaderboard.portal.model.dto.CompetitionOptionsDto;
import com.gffny.leaderboard.portal.model.ui.JsonResponse;
import com.gffny.leaderboard.portal.utils.StaticAssetTool;
import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;
import com.gffny.leaderboard.service.IUserService;
import com.gffny.leaderboard.util.ApplicationConfiguration;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController extends AbstractController implements
		ICompetitionManagementController, ICompetitionCompetitiorController {

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

	/**
	 * URL: /competition/
	 */
	@RequestMapping(value = "/")
	public ModelAndView competitionDefault() {

		return competitionDashboard();
	}

	/**
	 * URL: /competition/dashboard
	 */
	@RequestMapping(value = "/dashboard")
	public ModelAndView competitionDashboard() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * URL: /competition/create
	 * 
	 */
	@RequestMapping(value = "/create")
	public ModelAndView createCompetitionDialog() {
		String userId = getServletData().getGolferId();

		/*
		 * Intend to return a ModelAndView that has fragment or an HTML page
		 * that uses angluar.js to query the asynchronous methods to fulfill
		 * functionality
		 */
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

	@Override
	public ResponseEntity<JsonResponse<JSONable>> createCompetition(
			String body, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<JsonResponse<JSONable>> createCompetitionRound(
			String body, HttpSession session, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param body
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "/options")
	public ResponseEntity<JsonResponse<JSONable>> getCompetitionOptions(
			String body, HttpSession session, HttpServletRequest request) {
		try {
			return returnSuccess(
					new CompetitionOptionsDto(
							competitionService
									.getCompetitionScoringSystemList(),
							ApplicationConfiguration
									.getCompetitionVisibilityList()),
					HttpStatus.OK);
		} catch (ServiceException e) {
			return returnError(e.getMessage(), HttpStatus.OK);
		}
	}
}
/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import test.gffny.leaderboard.service.mock.MockServiceFactory;

import com.gffny.leaderboard.intralayer.ActionType;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController extends AbstractController {

	private static Logger log = Logger.getLogger(CompetitionController.class);

	private IAuthorisationService authorisationService = MockServiceFactory
			.getInstance().getAuthorisationService();
	private ICompetitionService competitionService = MockServiceFactory
			.getInstance().getCompetitionService();
	private IGolfCourseService golfCourseService = MockServiceFactory
			.getInstance().getGolfCourseService();

	@RequestMapping("/scorecard/select")
	public ModelAndView selectCompetitionRound(
			@RequestParam("competitionId") String competitionId,
			@RequestParam("userId") String userId) throws ServiceException {

		log.debug("selectCompetitionRound");

		ModelAndView model = null;
		if (competitionId != null && userId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.ENTER_SCORECARD)) {
				model = new ModelAndView("competition/select");
				ICompetition competition = competitionService
						.getCompetition(competitionId);
				model.addObject("competition", competition);
				return model;
			} else { // handle non authorised user requests

			}
		} else { // handle issue with null or invalid userid/competitonid

		}
		return model;
	}

	@RequestMapping("/scorecard/enter")
	public ModelAndView enterScorecard(
			@RequestParam("competitionRoundId") String competitionRoundId,
			@RequestParam("userId") String userId) throws ServiceException {

		ModelAndView model = null;
		if (userId != null && competitionRoundId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.ENTER_SCORECARD)) {
				model = new ModelAndView("competition/enter");
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

	@RequestMapping("/scorecard/save")
	public ModelAndView saveScorecard(
			@RequestParam("competitionRoundId") String competitionRoundId,
			@RequestParam("userId") String userId) {

		ModelAndView model = null;
		if (userId != null && competitionRoundId != null) {
			if (authorisationService.isPermitted(userId,
					ActionType.ENTER_SCORECARD)) {
				Map<String, String[]> parameterMap = getServletData()
						.getRequest().getParameterMap();
				Enumeration<String> paramNameEnum = getServletData()
						.getRequest().getParameterNames();
				Map<String, String[]> userLabValues = new HashMap<String, String[]>();
				while (paramNameEnum.hasMoreElements()) {
					String paramName = paramNameEnum.nextElement();
					// userLabValues = processParameter(paramName,
					// parameterMap.get(paramName)[0], userLabValues);
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
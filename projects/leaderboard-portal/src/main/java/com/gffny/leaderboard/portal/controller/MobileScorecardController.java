/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IScorecardService;
import com.gffny.leaderboard.util.CompetitionFunction;

/**
 * @author John Gaffney (john@gffny.com) Aug 8, 2012
 * 
 */
@Controller
@RequestMapping("/mobilescorecard")
public class MobileScorecardController extends AbstractController {

	// Service Declaration
	private IGolfCourseService golfCourseService;
	private ICompetitionService competitionService;
	private IScorecardService scorecardService;

	private static Logger log = Logger
			.getLogger(MobileScorecardController.class);

	@RequestMapping("/menu")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("mobilescorecard/menu");
		return model;
	}

	@RequestMapping("/competitionround")
	public ModelAndView competition() {
		ModelAndView model = new ModelAndView("mobilescorecard/competition");
		return model;
	}

	@RequestMapping("/practiceround")
	public ModelAndView practice() {
		ModelAndView model = new ModelAndView("mobilescorecard/practice");
		try {
			model.addObject("courseList",
					golfCourseService.getGolfCourseShortListByUserId("userId"));
			// TODO add meaningful userId
			model.addObject("countryList",
					golfCourseService.getSupportedCountryList());
			model.addObject("course", golfCourseService.getGolfCourseById(""));
			return model;
		} catch (ServiceException se) {
			return new ModelAndView("exception/serviceError");
			// TODO SERVICE EXCEPTION implement this in a more meaningful way!
		}
	}

	@RequestMapping("/asynch/competitionlist")
	public @ResponseBody
	List<ICompetition> getCompetitionListForUser(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		// get the users id
		try {
			return competitionService.getCompetitionListForUserId(request
					.getParameter("userId"));
		} catch (ServiceException ex) {
			log.error("service error in MobileScorecardController getCompetitionListForUser");
		}
		return new ArrayList<ICompetition>();
	}

	@RequestMapping("/asynch/coursedetail")
	public @ResponseBody
	List<IGolfCourse> getCourseById(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		// get the users id
		try {
			return golfCourseService.getGolfCourseById(request
					.getParameter("courseId"));
		} catch (ServiceException ex) {
			log.error("service error in MobileScorecardController getCompetitionListForUser");
		}
		return new ArrayList<IGolfCourse>();
	}

	@RequestMapping("/asynch/scorecardsubmission")
	public @ResponseBody
	String submitScorecard(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		// Take the data from the request, check the combined score vs the
		// individual score
		int golferCount = 0;
		try {
			String competitionRoundId = request
					.getParameter("competitionRoundId");
			golferCount = Integer.parseInt(request.getParameter("golferCount"));
			for (int i = 0; i < golferCount; i++) {
				String userId = request.getParameter("golfer[" + i
						+ "][userId]");
				String strokes = request.getParameter("golfer[" + i
						+ "][strokes]");
				String[] scoreArray = request.getParameterValues("golfer[" + i
						+ "][scorecard][]");
				if (CompetitionFunction.strokesMatchesScoreArray(strokes,
						scoreArray)) {
					scorecardService.submitScorecardForCompetitionRound(
							competitionRoundId, userId, scoreArray);
				}
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (ServiceException se) {
			se.printStackTrace();
		}
		return "success";
	}
}

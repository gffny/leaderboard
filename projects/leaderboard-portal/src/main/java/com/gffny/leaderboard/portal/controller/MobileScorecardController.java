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

import test.gffny.leaderboard.service.mock.MockServiceFactory;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IUserService;

/**
 * @author John Gaffney (john@gffny.com) Aug 8, 2012
 * 
 */
@Controller
@RequestMapping("/mobilescorecard")
public class MobileScorecardController {

	// Service Declaration
	private IUserService userService = MockServiceFactory.getInstance()
			.getUserService();
	private IGolfCourseService golfService = MockServiceFactory.getInstance()
			.getGolfCourseService();
	private ICompetitionService competitionService = MockServiceFactory
			.getInstance().getCompetitionService();

	private static Logger log = Logger
			.getLogger(MobileScorecardController.class);

	@RequestMapping("/main")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("mobilescorecard/main");
		return model;
	}

	@RequestMapping("/app")
	public ModelAndView application() {
		ModelAndView model = new ModelAndView("mobilescorecard/app");
		return model;
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
			return golfService.getGolfCourseById(request
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
		request.getParameterValues("scorecard");
		return "success";
	}
}

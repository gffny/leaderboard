/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.gffny.leaderboard.service.mock.MockServiceFactory;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.ICompetitionService;
import com.gffny.leaderboard.service.IGolfCourseService;
import com.gffny.leaderboard.service.IUserService;
import com.gffny.leaderboard.service.impl.ServiceFactory;

/**
 * @author John Gaffney (john@gffny.com) Aug 6, 2012
 * 
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractController {

	// Service Declaration
	private IUserService mockUserService = MockServiceFactory.getInstance()
			.getUserService();
	private IGolfCourseService mockGolfService = MockServiceFactory
			.getInstance().getGolfCourseService();
	private ICompetitionService mockCompetitionService = MockServiceFactory
			.getInstance().getCompetitionService();
	private IUserService userService = ServiceFactory.getInstance()
			.getUserService();

	private static Logger log = Logger.getLogger(DashboardController.class);

	/* PROFILE */
	@RequestMapping("/profile")
	public ModelAndView profile() {
		ModelAndView model = new ModelAndView("dashboard/profile/view");
		return model;
	}

	@RequestMapping("/profile/manage")
	public ModelAndView updateProfile() {
		ModelAndView model = new ModelAndView("dashboard/profile/manage");
		return model;
	}

	/* SCORECARD */
	@RequestMapping("/scorecard")
	public ModelAndView scorecard() {
		ModelAndView model = new ModelAndView("dashboard/scorecard/overview");
		return model;
	}

	@RequestMapping("/scorecard/{id}")
	public ModelAndView scorecardDetail(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("dashboard/scorecard/detail");
		return model;
	}

	@RequestMapping("/scorecard/{id}/manage")
	public ModelAndView scorecardManage(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("dashboard/scorecard/manage");
		return model;
	}

	@RequestMapping("/scorecard/add")
	public ModelAndView scorecardAdd() {
		ModelAndView model = new ModelAndView("dashboard/scorecard/add");
		return model;
	}

	/* SOCIETY */
	@RequestMapping("/society/{id}")
	public ModelAndView societyDetail(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("dashboard/society/detail");
		return model;
	}

	@RequestMapping("/society/{id}/manage")
	public ModelAndView societyManage(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("dashboard/society/manage");
		return model;
	}

	@RequestMapping("/society/create")
	public ModelAndView societyAdd() {
		ModelAndView model = new ModelAndView("dashboard/society/create");
		return model;
	}

	/* COMPETITION */
	@RequestMapping("/competition/{id}")
	public ModelAndView competitionDetail(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("dashboard/competition/detail");
		return model;
	}

	@RequestMapping("/competition/{id}/manage")
	public ModelAndView competitionManage(@PathVariable Long id) {
		ModelAndView model = new ModelAndView("dashboard/competition/manage");
		return model;
	}

	@RequestMapping("/competition/create")
	public ModelAndView competitionAdd() {
		// get golfers from users network (society | friend list)

		ModelAndView model = null;
		try {
			model = new ModelAndView("dashboard/competition/create");
			model.addObject("golferList",
					mockUserService.getAllSocietyMembers("MOCK"));
			model.addObject("countryList",
					mockGolfService.getSupportedCountryList());
			log.error("adding golfer list to the response");
			System.out.println("adding golfer to the response");
			return model;
		} catch (ServiceException e) {
			log.error("service error from user service in dashboard controller/competition/create");
			model = new ModelAndView("error");
		}
		return model;
	}

	@RequestMapping("/asynch/competitorlist")
	public @ResponseBody
	List<IGolfer> getCompetitionListForUser(HttpServletRequest request,
			HttpServletResponse response) throws ServiceException {
		log.info("getting list of potential competitors for ");
		// get the users id
		try {
			return mockUserService
					.getSocietyMemberListAssociatedWithUser(request
							.getParameter("userId"));
		} catch (ServiceException ex) {
			log.error("service error in MobileScorecardController getCompetitionListForUser");
		}
		return new ArrayList<IGolfer>();
	}

	@RequestMapping("/competition/create/schedule")
	public ModelAndView competitionSchedule(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		// String beginDate = request.getParameter("competitionBeginDate");
		// String endDate = request.getParameter("competitionBeginDate");
		// String teeTime = request.getParameter("teeTime");
		String[] teeTimeArray = new String[2];
		teeTimeArray[0] = "12:00";
		teeTimeArray[1] = "12:10";
		List<IGolfer> competitorList = new ArrayList<IGolfer>();
		List<String> golferIdList = Arrays.asList(request
				.getParameterValues("golferList"));
		try {
			IGolfCourse golfCourse = mockGolfService.getGolfCourseById(
					request.getParameter("golfCourse")).get(0);
			for (int i = 0; i < golferIdList.size(); i++) {
				competitorList.add(mockUserService
						.getGolferBySocietyMemberId(golferIdList.get(i)));
			}
			ICompetitionScheduler competitionScheduler = mockCompetitionService
					.getCompetitionScheduler(ICompetitionService.STROKEPLAY);

			model = new ModelAndView("dashboard/competition/show");
			model.addObject("competitionRound", competitionScheduler
					.scheduleCompetitionRound("President's Day Cup", 1,
							new Date(), golfCourse, teeTimeArray,
							competitorList, 4, 18));
			return model;
		} catch (ServiceException e) {
			log.error("service error from sevice in dashboard controller/competition/schedule");
			model = new ModelAndView("error");
		}
		return model;
	}
}

/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author John Gaffney (john@gffny.com) Aug 6, 2012
 * 
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

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
		ModelAndView model = new ModelAndView("dashboard/competition/create");
		return model;
	}
}

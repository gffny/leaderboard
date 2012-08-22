/**
 * 
 */
package com.gffny.leaderboard.portal.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.portal.web.utils.MapUtility;
import com.gffny.leaderboard.portal.web.utils.ScorecardMap;
import com.gffny.leaderboard.service.impl.GolfService;
import com.gffny.leaderboard.service.impl.UserService;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 26, 2012
 *
 */
public class ProfileController extends ApplicationController {

	/**
	 *
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
/*
		String userId = request.getParameter("userId");
		if(userId == null) {
        	this.setErrorCode("No UserId was passed to the controller.");
        	return new ModelAndView("error", "pageCode", this.getPageCode());
		}
		
		UserService userService = new UserService();
		GolfService golfService = new GolfService();

		//get the profile template
        ModelAndView profile = new ModelAndView("profile");

        //get the user info
        try {
        	profile.addObject("golfer", MapUtility.convertGolfer(userService.getGolferBySocietyMemberId(userId)));
        	List<ScorecardMap> scorecardMapList = MapUtility.convertScorecardList(golfService.getAllScorecardByUserId(userId));
        	profile.addObject("scorecardMapList", scorecardMapList);
        } catch (ServiceException ex) {
        	this.setErrorCode(ex.getMessage());
        	return new ModelAndView("error", "pageCode", this.getPageCode());
        }
		return profile;
		*/
		return new ModelAndView();
	}
}

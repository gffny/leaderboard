/**
 * 
 */
package com.gffny.leaderboard.portal.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.layerUtils.ServiceException;
import com.gffny.leaderboard.portal.web.utils.MapUtility;
import com.gffny.leaderboard.services.impl.UserService;

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
		
		String userId = request.getParameter("userId");
		if(userId == null) {
        	this.setErrorCode("No UserId was passed to the controller.");
        	return new ModelAndView("error", "pageCode", this.getPageCode());
		}
		
		UserService userService = new UserService();

		//get the profile template
        ModelAndView profile = new ModelAndView("profile");

        //get the user info
        try {
        	profile.addObject("golfer", MapUtility.convertGolfer(userService.getGolferBySocietyMemberId(userId)));
        } catch (ServiceException ex) {
        	this.setErrorCode(ex.getMessage());
        	return new ModelAndView("error", "pageCode", this.getPageCode());
        }
        /* to be done later 
        //get the last 5 rounds
        Map<String, Object> roundOneCourse = new HashMap<String, Object>();
        roundOneCourse.put("name", "Fresh Pond GC");
        roundOneCourse.put("holes", "9");
        roundOneCourse.put("par", "35");

        Map<String, Object> roundOne = new HashMap<String, Object>();
        roundOne.put("date", "John Gaffney");
        roundOne.put("course", roundOneCourse);
        roundOne.put("score", "53");

        Map<String, Object> roundList = new HashMap<String, Object>();
        roundList.put("roundOne", roundOne);
        profile.addObject("roundList", roundList);
        */
		return profile;
	}

}

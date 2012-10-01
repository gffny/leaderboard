/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author John Gaffney (john@gffny.com) Aug 6, 2012
 * 
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("auth/login");
		return model;
	}

	@RequestMapping("/process")
	public ModelAndView process() {
		System.out.println("process");

		return new ModelAndView("auth/process");
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView model = new ModelAndView("auth/logout");
		return model;
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		System.out.println("BLAH!");
		ModelAndView model = new ModelAndView("auth/register");
		return model;
	}
}

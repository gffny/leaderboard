/**
 * 
 */
package com.gffny.leaderboard.portal.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 6, 2012
 *
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@RequestMapping("/login")
	public ModelAndView login() {
		System.out.println("login");
		ModelAndView model = new ModelAndView("auth/login");
		return model;
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

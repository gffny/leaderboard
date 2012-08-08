/**
 * 
 */
package com.gffny.leaderboard.portal.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 8, 2012
 *
 */
@Controller
@RequestMapping("/mobilescorecard")
public class MobileScorecardController {
	
	@RequestMapping("/main")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("mobilescorecard/main");
		return model;
	}
}

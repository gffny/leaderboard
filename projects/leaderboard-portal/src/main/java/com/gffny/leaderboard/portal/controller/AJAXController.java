/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author John Gaffney (john@gffny.com) Jul 26, 2012
 * 
 */
@Controller
@RequestMapping("/asynch")
public class AJAXController extends AbstractController {

	/**
	 * 
	 * @param item
	 * @param id
	 * @param viewType
	 * @return
	 */
	@RequestMapping("/golfcourse/{country}/{state}")
	public ModelAndView publish(@PathVariable String country,
			@PathVariable String state) {

		return null;
	}
}

/**
 * 
 */
package com.gffny.leaderboard.portal.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.portal.controller.abst.AbstractController;

/**
 * @author John Gaffney (john@gffny.com) Jul 26, 2012
 * 
 */
@Controller
@RequestMapping("/")
public class ApplicationController extends AbstractController {

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("welcome");
		return model;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/help")
	public ModelAndView help() {
		ModelAndView model = new ModelAndView("help");
		return model;
	}

	/**
	 * 
	 * @param item
	 * @param id
	 * @param viewType
	 * @return
	 */
	@RequestMapping("/publish/{item}/{id}/{viewType}")
	public ModelAndView publish(@PathVariable String item,
			@PathVariable Long id, @PathVariable String viewType) {
		ModelAndView model = new ModelAndView("publish/" + item);
		// ModelAndView model = new ModelAndView("publish/"+item);
		model.addObject(id);
		return model;
	}
}

package com.gffny.leaderboard.portal.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.model.ui.RequestContext;
import com.gffny.leaderboard.portal.model.ui.ServletData;

public abstract class AbstractController extends WebContentGenerator {

	public IGolfer getUser() {
		return RequestContext.get().getUser();
	}

	public ServletData getServletData() {
		return RequestContext.get().getServletData();
	}

	protected ModelAndView getErrorModel(ServiceException serEx) {
		// TODO Auto-generated method stub
		return getErrorModel(serEx.getMessage());
	}

	protected ModelAndView getErrorModel(String errorMessage) {
		// TODO Auto-generated method stub
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorString", errorMessage);
		return mv;
	}
}

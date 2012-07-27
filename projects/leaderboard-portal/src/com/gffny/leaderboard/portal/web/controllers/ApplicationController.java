/**
 * 
 */
package com.gffny.leaderboard.portal.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 26, 2012
 *
 */
public abstract class ApplicationController implements Controller {

	private static final String RETURN_MESSAGE = "returnMessage";
	private static final String PAGE_CODE = "pageCode";

	private Map<String, Object> returnCode = new HashMap<String, Object>();

	/** 
	 * 
	 */
	public abstract ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	protected void setErrorCode(String msg) {
		this.returnCode.put(PAGE_CODE, "-1");
		this.returnCode.put(RETURN_MESSAGE, msg);
	}

	protected Map<String, Object> getPageCode() {
		return returnCode;
	}
}

/**
 * 
 */
package com.gffny.leaderboard.portal.controller.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.leaderboard.model.JSONable;
import com.gffny.leaderboard.portal.model.ui.JsonResponse;
import com.gffny.leaderboard.service.impl.UserService;

/**
 * @author John Gaffney (john@gffny.com) Jul 26, 2012
 * 
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends ApplicationController {

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param body
	 * @param session
	 * @param request
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/", headers = "Accept=application/json")
	public ResponseEntity<JsonResponse<JSONable>> getProfile(
			@RequestBody String body, HttpSession session,
			HttpServletRequest request) throws JsonParseException,
			JsonMappingException, IOException {
		if (userService != null) {
			// TODO figure out how to adequately get the userId!
			return returnSuccess(userService.getGolferById("1"), HttpStatus.OK);
		}
		return returnError("no user service", HttpStatus.METHOD_FAILURE);
	}
}

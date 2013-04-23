/**
 * 
 */
package com.gffny.leaderboard.portal.controller.impl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.model.JSONable;
import com.gffny.leaderboard.portal.controller.abst.AbstractController;
import com.gffny.leaderboard.portal.manager.impl.UserManager;
import com.gffny.leaderboard.portal.model.dto.InitResponseDto;
import com.gffny.leaderboard.portal.model.ui.JsonResponse;

/**
 * @author John Gaffney (john@gffny.com) Aug 6, 2012
 * 
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController extends AbstractController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserManager userManager;

	@RequestMapping("/")
	public ModelAndView noUrl() {
		return login();
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("auth/login");
		return model;
	}

	@RequestMapping(value = "/asychprocess", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<JsonResponse<JSONable>> asynchprocess(
			@RequestBody String body, HttpSession session,
			HttpServletRequest request) throws JsonParseException,
			JsonMappingException, IOException {

		return loginInternal("gffny", "passphrase", session, request);
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST, headers = "Accept=application/json")
	public ModelAndView process(@RequestBody String body, HttpSession session,
			HttpServletRequest request) throws JsonParseException,
			JsonMappingException, IOException {
		if (loginInternal("gffny", "passphrase", session, request)
				.getStatusCode().equals(HttpStatus.OK)) {
			return getDefaultView();
		} else {
			return getLoginView();
		}
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

	private ResponseEntity<JsonResponse<JSONable>> loginInternal(
			String username, String password, HttpSession session,
			HttpServletRequest request) {
		Date startTime = new Date();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		token.setDetails(new WebAuthenticationDetails(request));
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			InitResponseDto initDto = userManager.getInit(username);
			this.setUserSessionState(session, initDto);
			initDto.setLoadtime(new Date().getTime() - startTime.getTime()
					+ " ms");
			return returnSuccess(initDto, HttpStatus.OK);
		} catch (BadCredentialsException bae) {
			session.invalidate();
			String message = "Invalid credentials";
			// if (user != null) {
			// message += ": Login Attempt "
			// + (user.getFailedLoginCount() + 1) + " of "
			// + ConfigManager.MAX_LOGIN_ATTEMPTS + " for username: "
			// + username;
			// }
			return returnError(message, HttpStatus.UNAUTHORIZED);
		}
	}
}

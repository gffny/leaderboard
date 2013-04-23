/**
 * 
 */
package com.gffny.leaderboard.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.gffny.leaderboard.model.JSONable;
import com.gffny.leaderboard.portal.model.ui.JsonResponse;

/**
 * @author John Gaffney (john@gffny.com) Apr 22, 2013
 * 
 */
public interface ICompetitionManagementController {

	/* PAGE RETURN METHODS */

	/**
	 * 
	 * @return
	 */
	public ModelAndView competitionDefault();

	/**
	 * 
	 * @return
	 */
	public ModelAndView competitionDashboard();

	/**
	 * 
	 * @return
	 */
	public ModelAndView createCompetitionDialog();

	/* ASYNCH METHODS */

	/**
	 * submits the competition details to the service layer to persist the
	 * competition
	 * 
	 * @param body
	 *            contains the competitionName, date,
	 * @param session
	 * @param request
	 * @return
	 */
	public ResponseEntity<JsonResponse<JSONable>> createCompetition(
			@RequestBody String body, HttpSession session,
			HttpServletRequest request);

	/**
	 * submits the competition round details or competition round details array
	 * to the service layer to persist the competition
	 * 
	 * @param body
	 *            contains the competitionName, date,
	 * @param session
	 * @param request
	 * @return
	 */
	public ResponseEntity<JsonResponse<JSONable>> createCompetitionRound(
			@RequestBody String body, HttpSession session,
			HttpServletRequest request);

	/**
	 * 
	 * @param body
	 * @param session
	 * @param request
	 * @return
	 */
	public ResponseEntity<JsonResponse<JSONable>> getCompetitionOptions(
			@RequestBody String body, HttpSession session,
			HttpServletRequest request);

}

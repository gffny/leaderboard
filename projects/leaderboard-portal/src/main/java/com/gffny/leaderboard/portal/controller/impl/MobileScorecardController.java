/**
 * 
 */
package com.gffny.leaderboard.portal.controller.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utility.gffny.leaderboard.courseimages.ImageLoader;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.JSONable;
import com.gffny.leaderboard.portal.controller.abst.AbstractController;
import com.gffny.leaderboard.portal.model.dto.CourseListDto;
import com.gffny.leaderboard.portal.model.ui.JsonResponse;
import com.gffny.leaderboard.service.IGolfCourseService;

/**
 * @author John Gaffney (john@gffny.com) Aug 8, 2012
 * 
 */
@Controller
@RequestMapping("/mobile")
public class MobileScorecardController extends AbstractController {

	// Service Declaration
	@Autowired
	private IGolfCourseService golfCourseService;

	private ImageLoader imageLoader = ImageLoader.getInstance();

	private static Logger log = Logger
			.getLogger(MobileScorecardController.class);

	@RequestMapping("/a_courselist")
	@ResponseBody
	public ResponseEntity<JsonResponse<JSONable>> getCourseList(
			String userHandle) throws ServiceException {
		if (userHandle == null) {
			log.error("user profile handle is null");
			userHandle = "gffny";
		}
		return returnSuccess(
				new CourseListDto(
						golfCourseService
								.getGolfCourseShortListByUserId(userHandle)),
				HttpStatus.OK);
	}

	@RequestMapping("/sampleholemapimage")
	@ResponseBody
	public ResponseEntity<byte[]> getSampleHoleMapImage() {
		try {
			// TODO: this just returns a hardcoded imag
			InputStream fis = imageLoader.getImage("fresh-pond-hole-1")
					.getInputStream();
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fis),
					headers, HttpStatus.CREATED);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

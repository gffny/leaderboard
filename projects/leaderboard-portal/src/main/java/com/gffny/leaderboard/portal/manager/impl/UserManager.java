package com.gffny.leaderboard.portal.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.manager.IUserManager;
import com.gffny.leaderboard.portal.model.ui.InitResponseDto;
import com.gffny.leaderboard.service.IUserService;

@Component
public class UserManager implements IUserManager {

	@Autowired
	private IUserService userService;

	@Override
	public InitResponseDto getInit(String username) {
		IGolfer user;
		try {
			user = userService.getGolferByHandle(username);
			InitResponseDto dto = InitResponseDto.create(user);
			// check to see if the user's password has expired
			dto.setPasswordChangeRequired(isPasswordChangeRequired(user));
			return dto;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Handle the service exception
		return null;
	}

	private boolean isPasswordChangeRequired(IGolfer user) {
		// TODO Auto-generated method stub
		return false;
	}

	public IGolfer findUserByHandle(String username) throws ServiceException {
		return userService.getGolferByHandle(username);

	}
}

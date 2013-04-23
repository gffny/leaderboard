package com.gffny.leaderboard.portal.manager;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.portal.model.dto.InitResponseDto;

public interface IUserManager {

	public InitResponseDto getInit(String username);

	public IGolfer findUserByHandle(String username) throws ServiceException;

	public void save(IGolfer user) throws ServiceException;
}

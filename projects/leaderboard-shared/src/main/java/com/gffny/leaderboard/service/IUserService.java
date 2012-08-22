package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.IGolfer;

public interface IUserService {

	public List<IGolfer> getAllSocietyMembers(String societyId) throws ServiceException;

	public IGolfer getGolferBySocietyMemberId(String societyMemberId) throws ServiceException;
}
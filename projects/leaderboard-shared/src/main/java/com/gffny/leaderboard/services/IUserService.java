package com.gffny.leaderboard.services;

import java.util.List;

import com.gffny.leaderboard.layerUtils.ServiceException;
import com.gffny.leaderboard.model.IGolfer;

public interface IUserService {

	public List<IGolfer> getAllSocietyMembers(String societyId) throws ServiceException;

	public IGolfer getGolferBySocietyMemberId(String societyMemberId) throws ServiceException;
}
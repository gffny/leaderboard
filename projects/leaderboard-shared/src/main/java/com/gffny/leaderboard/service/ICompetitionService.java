package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.intralayer.ServiceResult;
import com.gffny.leaderboard.model.ICompetition;

public interface ICompetitionService {

	public static int STROKEPLAY = 1;
	public static int STABLEFORD = 2;

	public ICompetitionScheduler getCompetitionScheduler(int competitionType)
			throws ServiceException;

	public ICompetition createCompetition() throws ServiceException;

	public ServiceResult saveCompetition() throws ServiceException;

	/**
	 * @param userId
	 */
	public List<ICompetition> getCompetitionListForUserId(String userId)
			throws ServiceException;
}

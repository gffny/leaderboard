package com.gffny.leaderboard.service;

import java.util.List;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfer;

public interface ICompetitionService {

	public static int STROKEPLAY = 1;
	public static int STABLEFORD = 2;

	public ICompetitionScheduler getCompetitionScheduler(int competitionType)
			throws ServiceException;

	public ICompetition createCompetition() throws ServiceException;

	public IServiceResult saveCompetition() throws ServiceException;

	public ICompetition getCompetition(String competitionId)
			throws ServiceException;

	/**
	 * @param userId
	 */
	public List<ICompetition> getCompetitionListForUserId(String userId)
			throws ServiceException;

	/**
	 * @param competitionId
	 * @return
	 */
	public List<IGolfer> getCompetitorListForCompetition(String competitionId)
			throws ServiceException;

	/**
	 * @param competitionRoundId
	 * @return
	 */
	public ICompetitionRound getCompetitionRound(String competitionRoundId)
			throws ServiceException;
}

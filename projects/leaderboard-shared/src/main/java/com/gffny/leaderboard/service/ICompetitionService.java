package com.gffny.leaderboard.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.IGolfer;

public interface ICompetitionService {

	public static int STROKEPLAY = 1;
	public static int STABLEFORD = 2;

	/**
	 * 
	 * @param competitionType
	 * @return
	 * @throws ServiceException
	 */
	public ICompetitionScheduler getCompetitionScheduler(int competitionType)
			throws ServiceException;

	/**
	 * 
	 * @param competitionName
	 * @param competitionStartDate
	 * @return
	 * @throws ServiceException
	 */
	public ICompetition createCompetition(String competitionName,
			String competitionScoringSystem, String competitionVisibility,
			int numberOfRounds) throws ServiceException;

	/**
	 * @param roundNumber
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @return
	 * @throws ServiceException
	 */
	public ICompetitionRound createCompetitionRound(int roundNumber,
			String roundName, Date roundDate, int groupSize, String courseId)
			throws ServiceException;

	/**
	 * 
	 * @param competitionToSave
	 * @return
	 * @throws ServiceException
	 */
	public IServiceResult saveCompetition(ICompetition competitionToSave)
			throws ServiceException;

	/**
	 * 
	 * @param competitionRoundToSave
	 * @return
	 * @throws ServiceException
	 */
	public IServiceResult updateCompetitionRound(
			ICompetitionRound competitionRoundToSave) throws ServiceException;

	/**
	 * 
	 * @param competitionId
	 * @return
	 * @throws ServiceException
	 */
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

	/**
	 * @param competitionRoundId
	 * @return
	 */
	public Map<String, String[]> scoreRound(String competitionRoundId)
			throws ServiceException;

	/**
	 * @param competitionId
	 * @return
	 */
	public Map<String, String[]> scoreCompetition(String competitionId)
			throws ServiceException;

	/**
	 * @return
	 */
	public List<ICompetitionType> getCompetitionScoringSystemList()
			throws ServiceException;
}

/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import java.util.List;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Dec 26, 2012
 * 
 */
public class CompetitionService implements ICompetitionService {

	/**
	 * 
	 */
	private static CompetitionService INSTANCE = null;

	/**
	 * 
	 */
	private CompetitionService() {

	}

	/**
	 * 
	 * @return
	 */
	public static CompetitionService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CompetitionService();
		}
		return INSTANCE;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionScheduler(int)
	 */
	@Override
	public ICompetitionScheduler getCompetitionScheduler(int competitionType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#createCompetition()
	 */
	@Override
	public ICompetition createCompetition() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#saveCompetition()
	 */
	@Override
	public IServiceResult saveCompetition() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetition(java.lang.String)
	 */
	@Override
	public ICompetition getCompetition(String competitionId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionListForUserId(java.lang.String)
	 */
	@Override
	public List<ICompetition> getCompetitionListForUserId(String userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitorListForCompetition(java.lang.String)
	 */
	@Override
	public List<IGolfer> getCompetitorListForCompetition(String competitionId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionRound(java.lang.String)
	 */
	@Override
	public ICompetitionRound getCompetitionRound(String competitionRoundId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}

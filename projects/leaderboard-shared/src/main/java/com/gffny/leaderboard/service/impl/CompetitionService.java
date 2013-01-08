/**
 * 
 */
package com.gffny.leaderboard.service.impl;

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

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#scoreRound(java.lang.String)
	 */
	@Override
	public Map<String, String[]> scoreRound(String competitionRoundId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#scoreCompetition(java.lang.String)
	 */
	@Override
	public Map<String, String[]> scoreCompetition(String competitionId)
			throws ServiceException {
		ICompetition competition = getCompetition(competitionId);
		for (ICompetitionRound competitionRound : competition
				.getCompetitionRoundList()) {
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#createCompetition(java.lang.String,
	 *      java.lang.String, java.lang.String, int)
	 */
	@Override
	public ICompetition createCompetition(String competitionName,
			String competitionScoringSystem, String competitionVisibility,
			int numberOfRounds) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#saveCompetition(com.gffny.leaderboard.model.ICompetition)
	 */
	@Override
	public IServiceResult saveCompetition(ICompetition competitionToSave)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionScoringSystemList()
	 */
	@Override
	public List<ICompetitionType> getCompetitionScoringSystemList()
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#createCompetitionRound(java.lang.String,
	 *      java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public ICompetitionRound createCompetitionRound(String roundNumber,
			String roundName, Date roundDate, int groupSize, int courseId) {
		// TODO Auto-generated method stub
		return null;
	}
}

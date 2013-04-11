/**
 * 
 */
package com.gffny.leaderboard.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.dao.ICompetitionDAO;
import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.intralayer.ServiceResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.CompetitionRound;
import com.gffny.leaderboard.service.AbstractService;
import com.gffny.leaderboard.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Dec 26, 2012
 * 
 */
public class CompetitionService extends AbstractService implements
		ICompetitionService {

	/**
	 * 
	 */
	public static Logger log = Logger.getLogger(CompetitionService.class);

	@Autowired
	private ICompetitionDAO competitionDao;

	// WORK LIST
	// TODO IMPLEMENT FOR create competition feature
	// competitionService.getCompetitionScoringSystemList()
	// competitionService.createCompetition(...)
	// competitionService.getCompetition(...)
	// competitionService.createCompetitionRound(...)
	// competitionService.saveCompetition(...)

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
		try {
			return competitionDao.getCompetitionById(Integer
					.parseInt(competitionId));
		} catch (NumberFormatException nfe) {
			return logErrorReturnEmptyClass(nfe, log, Competition.class);
		}
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
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionScoringSystemList()
	 */
	@Override
	public List<ICompetitionType> getCompetitionScoringSystemList()
			throws ServiceException {
		try {
			return competitionDao.getCompetitionTypeList();
		} catch (DAOException daoEx) {
			return logErrorReturnEmptyList(daoEx, log, ICompetitionType.class);
		}
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
		// get the competitionType based on the competitionTypeName
		ICompetitionType competitionType = competitionDao
				.getCompetitionTypeByName(competitionScoringSystem);
		// create an unsaved instance of the competition to be passed to the dao
		Competition competition = new Competition(competitionName,
				competitionType, competitionVisibility, numberOfRounds);
		competition.setNew(true);
		// TODO Do we want to save the competition here?
		// saveCompetition(newCompetition);
		return competition;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#createCompetitionRound(java.lang.String,
	 *      java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public ICompetitionRound createCompetitionRound(int roundNumber,
			String roundName, Date roundDate, int groupSize, String courseId)
			throws ServiceException {
		// create an unsaved instance of the competition to be passed to the dao
		CompetitionRound competitionRound = new CompetitionRound(roundName,
				roundNumber, roundDate, courseId);
		competitionRound.setNew(true);
		// TODO Do we want to save the competition round here?
		// try {
		// saveCompetitionRound(newCompetition);
		// } catch (ServiceException serEx) {
		// logErrorReturnEmptyClass(serEx, log, CompetitionRound.class);
		// }
		return competitionRound;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#saveCompetition(com.gffny.leaderboard.model.ICompetition)
	 */
	@Override
	public IServiceResult saveCompetition(ICompetition competition)
			throws ServiceException {
		try {
			// save the competition instance
			DAOResult daoResult = competitionDao.saveCompetition(competition);
			if (competition.isNew()) {
				competition.setId(daoResult.getIdAsInt());
			}
			return new ServiceResult("",
					IServiceResult.SAVE_COMPETITION_SUCCESS);
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.service.ICompetitionService#saveCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound)
	 */
	@Override
	public IServiceResult updateCompetitionRound(
			ICompetitionRound competitionRoundToSave) throws ServiceException {
		// if a competition round does not have a competition id, then it cannot
		// be saved
		if (competitionRoundToSave.getCompetitionId() != 0) {
			try {
				// save the competition round instance
				DAOResult daoResult = competitionDao
						.saveCompetitionRound(competitionRoundToSave);
				competitionRoundToSave.setRoundId(daoResult.getIdAsInt());
				return new ServiceResult(
						"competition round update was a success",
						IServiceResult.SAVE_COMPETITION_SUCCESS);
			} catch (DAOException e) {
				log.error(e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		} else {
			throw new ServiceException(
					"competition round does not have a competition id");
		}
	};

}

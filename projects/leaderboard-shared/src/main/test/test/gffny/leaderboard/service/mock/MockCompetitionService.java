/**
 * 
 */
package test.gffny.leaderboard.service.mock;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.gffny.leaderboard.utilities.TestUtilities;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.component.scheduler.impl.SimpleCompetitionScheduler;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.impl.Competition;
import com.gffny.leaderboard.model.impl.CompetitionRound;
import com.gffny.leaderboard.model.impl.CompetitionType;
import com.gffny.leaderboard.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public class MockCompetitionService implements ICompetitionService {

	private Map<String, ICompetition> competitionCache = new HashMap<String, ICompetition>();

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionScheduler(int)
	 */
	@Override
	public ICompetitionScheduler getCompetitionScheduler(int competitionType)
			throws ServiceException {
		// TODO in real implementation look at using spring injection
		switch (competitionType) {
		case STROKEPLAY:
			return SimpleCompetitionScheduler.getInstance();
		case STABLEFORD:
			return SimpleCompetitionScheduler.getInstance();
		}
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionListForUserId(java.lang.String)
	 */
	@Override
	public List<ICompetition> getCompetitionListForUserId(String userId)
			throws ServiceException {
		ICompetition competition = TestUtilities.getTestCompetition();
		competition.addCompetitionRound(TestUtilities.getTestRound(1));
		competition.addCompetitionRound(TestUtilities.getTestRound(2));
		competition.addCompetitionRound(TestUtilities.getTestRound(3));
		competition.addCompetitionRound(TestUtilities.getTestRound(4));
		return Arrays.asList(competition);
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetition(java.lang.String)
	 */
	@Override
	public ICompetition getCompetition(String competitionId)
			throws ServiceException {
		if (competitionCache.get(competitionId) == null) {
			ICompetition competition = TestUtilities.getTestCompetition();
			competition.addCompetitionRound(TestUtilities.getTestRound(1));
			competition.addCompetitionRound(TestUtilities.getTestRound(2));
			competition.addCompetitionRound(TestUtilities.getTestRound(3));
			competition.addCompetitionRound(TestUtilities.getTestRound(4));
			return competition;
		}
		return competitionCache.get(competitionId);
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitorListForCompetition(java.lang.String)
	 */
	@Override
	public List<IGolfer> getCompetitorListForCompetition(String competitionId)
			throws ServiceException {
		return TestUtilities.getTestGolferList();
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionRound(java.lang.String)
	 */
	@Override
	public ICompetitionRound getCompetitionRound(String competitionRoundId)
			throws ServiceException {
		return TestUtilities.getTestRound(1000);
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#getCompetitionScoringSystemList()
	 */
	@Override
	public List<ICompetitionType> getCompetitionScoringSystemList()
			throws ServiceException {
		return TestUtilities.getTestCompetitionTypeList();
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#createCompetition(java.lang.String,
	 *      java.util.Date, int)
	 */
	@Override
	public ICompetition createCompetition(String name,
			String competitionScoringSystem, String competitionVisiblity,
			int numberOfRounds) throws ServiceException {
		ICompetition competition = new Competition(name, new CompetitionType(
				"Stableford", "Stableford", "Stableford", true, true, false),
				competitionVisiblity, numberOfRounds);

		// TODO Use a service layer to cache objects - abstraction task
		competitionCache.put(competition.getCompetitionIdAsString(),
				competition);
		return competition;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#saveCompetition(com.gffny.leaderboard.model.ICompetition)
	 */
	@Override
	public IServiceResult saveCompetition(ICompetition competitionToSave)
			throws ServiceException {
		competitionCache.put(competitionToSave.getCompetitionIdAsString(),
				competitionToSave);
		return null;
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#createCompetitionRound(java.lang.String,
	 *      java.lang.String, java.util.Date, int, int)
	 */
	@Override
	public ICompetitionRound createCompetitionRound(int roundNumber,
			String roundName, Date roundDate, int groupSize, String courseId) {
		return new CompetitionRound(roundName, roundNumber, roundDate, courseId);
	}

	/**
	 * @see com.gffny.leaderboard.service.ICompetitionService#updateCompetitionRound(com.gffny.leaderboard.model.ICompetition.ICompetitionRound)
	 */
	@Override
	public IServiceResult updateCompetitionRound(
			ICompetitionRound competitionRoundToSave) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}

/**
 * 
 */
package test.gffny.leaderboard.service.mock;

import java.util.Arrays;
import java.util.List;

import test.utilities.TestUtilities;

import com.gffny.leaderboard.component.scheduler.ICompetitionScheduler;
import com.gffny.leaderboard.component.scheduler.impl.SimpleCompetitionScheduler;
import com.gffny.leaderboard.intralayer.IServiceResult;
import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Oct 3, 2012
 * 
 */
public class MockCompetitionService implements ICompetitionService {

	//
	private static MockCompetitionService INSTANCE = null;

	/**
	 * singleton constructor
	 */
	private MockCompetitionService() {

	}

	/**
	 * factory method
	 * 
	 * @return
	 */
	public static MockCompetitionService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockCompetitionService();
		}
		return INSTANCE;
	}

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
		ICompetition competition = TestUtilities.getTestCompetition();
		competition.addCompetitionRound(TestUtilities.getTestRound(1));
		competition.addCompetitionRound(TestUtilities.getTestRound(2));
		competition.addCompetitionRound(TestUtilities.getTestRound(3));
		competition.addCompetitionRound(TestUtilities.getTestRound(4));
		return competition;
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

}

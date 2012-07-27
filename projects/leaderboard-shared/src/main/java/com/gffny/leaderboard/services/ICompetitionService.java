package com.gffny.leaderboard.services;

import com.gffny.leaderboard.layerUtils.ServiceException;
import com.gffny.leaderboard.layerUtils.ServiceResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.utils.ICompetitionScheduler;

public interface ICompetitionService {

	public ICompetitionScheduler getCompetitionScheduler();
	
	public ICompetition createCompetition();
	
	public ServiceResult saveCompetition() throws ServiceException;
}

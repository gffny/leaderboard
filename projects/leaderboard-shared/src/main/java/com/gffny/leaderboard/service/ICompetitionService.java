package com.gffny.leaderboard.service;

import com.gffny.leaderboard.intralayer.ServiceException;
import com.gffny.leaderboard.intralayer.ServiceResult;
import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.util.ICompetitionScheduler;

public interface ICompetitionService {

	public ICompetitionScheduler getCompetitionScheduler();
	
	public ICompetition createCompetition();
	
	public ServiceResult saveCompetition() throws ServiceException;
}

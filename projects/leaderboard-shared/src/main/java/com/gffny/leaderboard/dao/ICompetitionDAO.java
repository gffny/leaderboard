package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.intralayer.DAOException;
import com.gffny.leaderboard.intralayer.DAOResult;
import com.gffny.leaderboard.model.ICompetitionType;

public interface ICompetitionDAO {

	public List<ICompetitionType> getCompetitionTypeList() throws DAOException;
	
	public DAOResult saveCompetition() throws DAOException;

}

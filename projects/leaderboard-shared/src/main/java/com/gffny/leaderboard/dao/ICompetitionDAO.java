package com.gffny.leaderboard.dao;

import java.util.List;

import com.gffny.leaderboard.layerUtils.DAOException;
import com.gffny.leaderboard.layerUtils.DAOResult;
import com.gffny.leaderboard.model.ICompetitionType;

public interface ICompetitionDAO {

	public List<ICompetitionType> getCompetitionTypeList() throws DAOException;
	
	public DAOResult saveCompetition() throws DAOException;

}

package com.gffny.leaderboard.dao;

import com.gffny.leaderboard.dao.IScorecardDAO;

public interface IDAOFactory {

	public IUserDAO getUserDAO();

	public IScorecardDAO getScorecardDAO();

	public IGolfCourseDAO getGolfCourseDAO();

}
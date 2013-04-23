/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gffny.leaderboard.dao.abst.AbstractDAO;
import com.gffny.leaderboard.intralayer.DAOException;

/**
 * @author jgaffney02
 * 
 */
public class AbstractMySQLDAO extends AbstractDAO {

	private static Logger log = Logger.getLogger(AbstractMySQLDAO.class);

	// TODO make these configurable so that they are defined a properties file
	private String url = "jdbc:mysql://localhost:3306/leaderboard";
	private String username = "root";
	private String password = "password";

	private Connection connection = null;

	public AbstractMySQLDAO() {
		BasicConfigurator.configure();

		try {
			log.info("creating connection");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public Connection getConnection() throws DAOException {
		if (connection == null) {
			log.error("no connection created in abstract class");
			throw new DAOException("No JDBC Connection to Database!");
		}
		return connection;
	}

	/**
	 * 
	 * @param res
	 * @param columnName
	 * @return
	 */
	protected String getString(ResultSet res, String columnName) {
		if (columnName != null) {
			try {
				return res.getString(columnName);
			} catch (SQLException sqlEx) {
				log.debug("No column named " + columnName
						+ " in the result set");
			}
		}
		return new String();
	}

	/**
	 * 
	 * @param res
	 * @param columnName
	 * @return false - default
	 */
	protected boolean getBoolean(ResultSet res, String columnName) {
		if (columnName != null) {
			try {
				return res.getBoolean(columnName);
			} catch (SQLException sqlEx) {
				log.debug("No column named " + columnName
						+ " in the result set");
			}
		}
		return false;
	}

	/**
	 * 
	 * @param res
	 * @param columnName
	 * @return
	 */
	protected int getInt(ResultSet res, String columnName) {
		if (columnName != null) {
			try {
				return res.getInt(columnName);
			} catch (SQLException sqlEx) {
				log.debug("No column named " + columnName
						+ " in the result set");
			}
		}
		return -1;
	}
}

/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gffny.leaderboard.intralayer.DAOException;

/**
 * @author jgaffney02
 *
 */
public class AbstractMySQLDAO {
	
	private static Logger log = Logger.getLogger(AbstractMySQLDAO.class);

	private String url = "jdbc:mysql://localhost:3306/leaderboard";
	private String username = "root";
	private String password = "password";

	private Connection connection = null;

	protected AbstractMySQLDAO() {
		BasicConfigurator.configure();

		try {
			log.info("creating connection");
	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(url,username, password);
			
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public Connection getConnection() throws DAOException {
		if(connection == null) {
			log.error("no connection created in abstract class");
			throw new DAOException("No JDBC Connection to Database!");
		}
		return connection;
	}
}

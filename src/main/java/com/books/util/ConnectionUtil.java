package com.books.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.books.exception.DbException;
import com.books.logger.Logger;

public class ConnectionUtil {
	private static final Logger log = Logger.getInstance();
	public static Connection getConnection() throws DbException {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "system", "oracle");
			log.getInput(connection);
			return connection;
		}
		catch(SQLException e)
		{
			throw new DbException("Unable to Connect");
		}catch (ClassNotFoundException e) {
			throw new DbException("Driver not found");
		}
	}
}

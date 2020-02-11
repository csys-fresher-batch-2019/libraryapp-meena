package com.books.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.books.logger.Logger;

public class ConnectionUtil
{
	private static final Logger log=Logger.getInstance();
	
	public static Connection getConnection()throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","oracle");
		log.getInput(connection);
		return connection;		
	}
}

package com.jjit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	
	
	public static Connection getConnection()
	{
		// Create connection
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Driver class is not Loaded...");
		}
		Connection con=null;
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice","root","root");
		}
		catch(SQLException ex)
		{
			System.out.println("Connection Parameter Error Connection not created...");
		}
		return con;
	}
}

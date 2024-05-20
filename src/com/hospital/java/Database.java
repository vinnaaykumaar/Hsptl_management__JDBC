package com.hospital.java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
	public static Connection conn;
	private static Connection createConn() throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","ts15er1891");
		System.out.println("Database Connection Created Successfully");
		return conn;
		
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(conn == null) {
			return createConn();
		}
		return conn;
	}



}

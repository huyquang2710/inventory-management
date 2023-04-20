package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class CheckConnection {
	public static void main(String[] args) {
		String uri = "jdbc:mysql://localhost:3306/inventory_management";
		String user = "root";
	
		try {
			Connection connection = DriverManager.getConnection(uri, user, null);
			System.err.println(connection);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

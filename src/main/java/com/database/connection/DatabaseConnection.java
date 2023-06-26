package com.database.connection;

import java.sql.*;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/sample";

	private static final String USERNAME = "root";
	private static final String PASSWORD = "Surya@123";

	public static Connection GetConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);

		// to use

//		try (Connection connection = DatabaseConnection.GetConnection();
//                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

	}
}

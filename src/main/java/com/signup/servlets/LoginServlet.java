package com.signup.servlets;

import java.io.*;


import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.database.connection.*;
import com.password.encrypt.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("Id");
		String password = request.getParameter("Password");

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		// Encrypt the login password
		String encryptedPassword = PasswordUtil.hashPassword(password);

//		System.out.println(encryptedPassword);
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DatabaseConnection.GetConnection();

			statement = connection.prepareStatement("SELECT * FROM users WHERE User_Id =? AND User_Password =?");
			statement.setString(1, userId);
			statement.setString(2, encryptedPassword);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				// create a new session for the user
				HttpSession session = request.getSession();

				// Add user id in the cookie
				Cookie cookie = new Cookie("userId", userId);
				cookie.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cookie);

				session.setAttribute("username", resultSet.getString("User_Name"));
				response.sendRedirect("welcome.jsp");
				// redirect the user to the welcome page

			} else {

				// return an error message
				request.setAttribute("errorMessage", "Invalid User Id or Password.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

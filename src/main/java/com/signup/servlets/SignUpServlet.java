package com.signup.servlets;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.database.connection.*;
import com.password.encrypt.*;

@jakarta.servlet.annotation.WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		String userId = request.getParameter("Id");
		String password = request.getParameter("Password");
		String userName = request.getParameter("Name");

		String encryptedPassword = PasswordUtil.hashPassword(password);
		try (Connection connection = DatabaseConnection.GetConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO users (User_Id, User_Password,Created_On,User_Name) VALUES (?, ?,curdate(),?)")) {
			Class.forName("com.mysql.jdbc.Driver");
			statement.setString(1, userId);
			statement.setString(2, encryptedPassword);
			statement.setString(3, userName);
			HttpSession session=request.getSession();
			session.setAttribute("username", userName);
			int row=statement.executeUpdate();
			if(row>0){
				response.sendRedirect("login.jsp");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			
			request.setAttribute("errorMessage", " User id alreday exist please use different user id.");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			throw new ServletException(e);
		}

	}

}

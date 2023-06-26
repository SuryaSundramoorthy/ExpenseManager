package com.signup.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        cookie.setMaxAge(0);
		        response.addCookie(cookie);
		    }
		}
		
		response.sendRedirect("login.jsp");
		
	}

	

}

package com.transactions.servlets;

import jakarta.servlet.*;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.database.connection.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.transactions.DAO.TransactionsDAOImpl;
import com.transactions.bean.*;

@WebServlet("/TransactionsServlet")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TransactionsDAOImpl impl = new TransactionsDAOImpl();

	//To Add a Transaction
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
response.setContentType("application/json");
		HttpSession session = request.getSession();

		try {

			int amount = Integer.parseInt(request.getParameter("amount"));
			String category = request.getParameter("category");
			String type = null;

			if (category.equalsIgnoreCase("salary") || category.equalsIgnoreCase("loan")) {

				type = "income";
			} else {
				type = "expense";
			}

			TransactionBean bean = new TransactionBean(amount, type, category,(String) session.getAttribute("sessionId"));

			// ADD TRANSACTION
			impl.addTransction(bean);
			System.out.println("Done");
			System.out.println((String) bean.toString());
			
			RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Get All Transaction
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*"); // You can set the allowed domains here instead of "*"
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		// response.setHeader("Access-Control-Allow-Headers", "Content-Type,
		// Authorization");

		response.setContentType("application/json");
		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("sessionId");

		PrintWriter out = response.getWriter();
		
		

		// Create a list Variable to store the values from database
		List<TransactionBean> allTransactions = new ArrayList<>();
		allTransactions = impl.getAllTransctions(userId);
	
//		System.out.println(allTransactions);

		
				
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		Gson gson = gb.create();

		String myObjectJson = gson.toJson(allTransactions); // Serialize the Java object to JSON
		
	
//		System.out.println(myObjectJson);
		out.print(myObjectJson); // Write JSON string to response
		
		System.out.println();
	}

	
	
	// To Update a Transaction
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		TransactionBean bean = new TransactionBean();
		String userId= (String) session.getAttribute("sessionId");
			
		PrintWriter out=response.getWriter();
		String category=request.getParameter("category");
		bean.setId(Integer.parseInt(request.getParameter("TransactionId")));
		bean.setAmount(Integer.parseInt(request.getParameter("amount")));
		bean.setCategory(category);
		String type = null;

		if (category.equalsIgnoreCase("salary") || category.equalsIgnoreCase("loan")) {

			type = "income";
		} else {
			type = "expense";
		}
		bean.setType(type);
		bean.setDate(request.getParameter("date"));
		bean.setUserId(userId);
		
		impl.updateTransction(bean);
		out.print("Updated");
		
	}
	
	//To Delete a Transaction
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		impl.deleteTransction(id);
		System.out.println("Deleteddd");
		out.print("successfully Deleted");		
	}
	
}

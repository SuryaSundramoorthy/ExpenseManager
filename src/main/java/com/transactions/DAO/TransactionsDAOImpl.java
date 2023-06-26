package com.transactions.DAO;

import com.database.connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.transactions.bean.*;

public class TransactionsDAOImpl implements TransactionsDAO {
	TransactionBean bean = new TransactionBean();
	ResultSet rs = null;

	@Override
	public boolean addTransction(TransactionBean transaction) {

		System.out.println("Started ADD TRANSACTION");
		try {
			Connection con = DatabaseConnection.GetConnection();
			String insertStatement = "insert into transactions(Amount,Category,Type,User_Id,Date) values(?,?,?,?,curdate())";
			PreparedStatement pst = con.prepareStatement(insertStatement);
			pst.setInt(1, transaction.getAmount());
			pst.setString(2, transaction.getCategory());
			pst.setString(3, transaction.getType());
			pst.setString(4, transaction.getUserId());
			int result = pst.executeUpdate();
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateTransction(TransactionBean transaction) {
		System.out.println("Started UPDATE TRANSACTION");
		try {
			Connection con = DatabaseConnection.GetConnection();
			String UpdateStatement = "update transactions set Amount=?,Category=?,Type=?,User_Id=?,Date=? where Transaction_Id=? ";
			PreparedStatement pst = con.prepareStatement(UpdateStatement);
			pst.setInt(1, transaction.getAmount());
			pst.setString(2, transaction.getCategory());
			pst.setString(3, transaction.getType());
			pst.setString(4, transaction.getUserId());
			pst.setString(5, transaction.getDate());
			pst.setInt(6, transaction.getId());
			int result = pst.executeUpdate();
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteTransction(int id) {

		System.out.println("Started Delete TRANSACTION");
		try {
			Connection con = DatabaseConnection.GetConnection();
			String deleteStatement = "delete from transactions where Transaction_Id=? ";
			PreparedStatement pst = con.prepareStatement(deleteStatement);
			pst.setInt(1, id);
			int result = pst.executeUpdate();
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<TransactionBean> getAllTransctions(String userId) {
		System.out.println("Started Get All Transactions");
		try {

			Connection con = DatabaseConnection.GetConnection();
			String selectStatement = "select *from Transactions  where User_Id=? order by Transaction_Id DESC" ;
			String userNameStatement="select *from Users where User_Id=?";
			PreparedStatement pst = con.prepareStatement(selectStatement);
			PreparedStatement pst1 = con.prepareStatement(userNameStatement);
			pst.setString(1, userId);
			pst1.setString(1, userId);
			ResultSet rs = pst.executeQuery();
			ResultSet rs1=pst1.executeQuery();
			List<TransactionBean> transactions = new ArrayList<TransactionBean>();

			while(rs1.next()) {
				TransactionBean bean = new TransactionBean();

				bean.setUserName(rs1.getString("User_Name"));
				transactions.add(bean);
			}
			
			while (rs.next()) {

				TransactionBean bean = new TransactionBean();

				bean.setAmount(rs.getInt("Amount"));
				bean.setCategory(rs.getString("Category"));
				bean.setDate(rs.getString("Date"));
				bean.setType(rs.getString("type"));
				bean.setId(rs.getInt("Transaction_Id"));
				transactions.add(bean);
			}

			return transactions;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {

		TransactionBean bean = new TransactionBean();
//		bean.setId(23);
		bean.setAmount(1099);
		bean.setCategory("food");
		bean.setType("expense");
		bean.setDate("2024-03-11");
		bean.setUserId("surya20010412@gmail.com");

		TransactionsDAOImpl impl = new TransactionsDAOImpl();
		// ADD TRANSACTION
//		impl.addTransction(bean);

		// UPDATE TRANSACTION
		// impl.updateTransction(bean);

		// DELETE TRANSACTION
//		 impl.deleteTransction(20);

		// GET ALL TRANSACTIONS

		System.out.println(impl.getAllTransctions("surya20010412@gmail.com"));
		
//		System.out.println(bean.toString());

	}




	
}

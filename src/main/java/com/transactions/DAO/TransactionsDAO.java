package com.transactions.DAO;
import java.util.List;

import com.transactions.bean.*;

public interface TransactionsDAO {
	
	
	// ADD Transaction
	public boolean addTransction (TransactionBean transction);
	
	// Update Transaction
	public boolean updateTransction (TransactionBean transction);
	
	// Delete Transaction
	public boolean deleteTransction (int id);
		
	
	// GET ALL Transactions
	public List<TransactionBean> getAllTransctions (String id);
	

}

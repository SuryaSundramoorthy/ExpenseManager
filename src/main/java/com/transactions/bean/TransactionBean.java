package com.transactions.bean;

public class TransactionBean {

	private int id;
	private int amount;
	private String type;
	private String category;
	private String date;
	private String userId;
	private String userName;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TransactionBean() {
		super();
	}

	public TransactionBean(int amount, String type, String category, String userId) {
		super();
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.userId = userId;
	}

	public TransactionBean(int id, int amount, String type, String category, String date, String userId) {
		super();
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.date = date;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", type=" + type + ", category=" + category + ", date="
				+ date + ", userId=" + userId + "]";
	}

	

	

	
}

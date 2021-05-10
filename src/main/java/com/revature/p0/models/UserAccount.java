package com.revature.p0.models;

public class UserAccount {

	private int account_id;
	private int owner_id;
	private int account_type; // 0 checking; 1 savings; 2 investment
	private int balance;

	public UserAccount (int account_id, int owner_id, int account_type, int balance) {
		this.account_id = account_id;
		this.owner_id = owner_id;
		this.account_type = account_type;
		this.balance = balance;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public int getAccount_type() {
		return account_type;
	}

	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"account_id=" + account_id +
				", owner_id=" + owner_id +
				", account_type=" + account_type +
				", balance=" + balance +
				'}';
	}
}

package com.revature.p0.models;

public class UserAccount {

	private int accountId;
	private int ownerId;
	private int accountType; // 0 checking; 1 savings; 2 investment
	private double balance;

	public UserAccount() {
		super();
	}

	public UserAccount (int accountId, int ownerId, int accountType, double balance) {
		this.accountId = accountId;
		this.ownerId = ownerId;
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"account_id=" + accountId +
				", owner_id=" + ownerId +
				", account_type=" + accountType +
				", balance=" + balance +
				'}';
	}
}

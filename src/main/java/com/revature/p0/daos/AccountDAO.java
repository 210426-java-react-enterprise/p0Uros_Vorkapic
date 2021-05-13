package com.revature.p0.daos;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

	/**
	 * Creates a new account of account_type accType that belongs to owner_id ownerId
	 * @param ownerId owner of new account
	 * @param accType type of account, e.g. checking, savings, etc.
	 * @return a UserAccount object of newly opened account with balance of 0.00
	 */
	public UserAccount openNewAccount(int ownerId, int accType) {
		UserAccount newAccount = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO accounts (owner_id, account_type, balance) VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "account_id" });
			pstmt.setInt(1, ownerId);
			pstmt.setInt(2, accType);
			pstmt.setInt(3, 0);

			if (pstmt.executeUpdate() != 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					newAccount = new UserAccount();
					newAccount.setAccountId(rs.getInt("account_id"));
					newAccount.setOwnerId(ownerId);
					newAccount.setAccountType(accType);
					newAccount.setBalance(0);
				}
			}
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return newAccount;
	}

	/**
	 * Deletes account from database
	 * @param account account to delete from database
	 */
	public void closeAccount(UserAccount account) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE * FROM accounts WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account.getAccountId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Makes a deposit of value 'deposit' into UserAccount 'account'
	 * @param account account the deposit will be made into
	 * @param deposit amount to deposit into account
	 */
	public void makeDeposit(UserAccount account, double deposit) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, account.getBalance() + deposit);
			pstmt.setInt(2, account.getAccountId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes a deposit of value 'deposit' into UserAccount 'account'
	 * @param account account the withdrawal will be made from
	 * @param withdraw amount to withdraw from account
	 */
	public void makeWithdraw(UserAccount account, double withdraw) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, account.getBalance() - withdraw);
			pstmt.setInt(2, account.getAccountId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double fetchAccountBalance(int accountId) {
		double balance = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT balance FROM accounts WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				balance = rs.getDouble("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	/**
	 * Finds existing account in database using its account ID
	 * @param ownerId owner of accounts being fetched from database
	 * @return ArrayList of UserAccount objects containing fetched accounts or null if none are found
	 */
	public ArrayList<UserAccount> fetchAccounts(int ownerId) {
		ArrayList<UserAccount> accounts = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM accounts WHERE owner_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ownerId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				accounts = new ArrayList<>();
			}
			while (rs.next()) {
				accounts.add(new UserAccount(rs.getInt("account_id"),
											 rs.getInt("owner_id"),
											 rs.getInt("account_type"),
											 rs.getDouble("balance")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}
}

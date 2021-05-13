package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.models.UserAccount;
import com.revature.p0.util.ArrayList;

public class AccountServices {

	private final AccountDAO aDao;

	public AccountServices(AccountDAO aDao) {
		this.aDao = aDao;
	}

	public ArrayList<UserAccount> findAllAccountsById(int id) {
		if (aDao.fetchAccounts(id) == null) {
			return new ArrayList<UserAccount>();
		} else {
			return aDao.fetchAccounts(id);
		}

	}

	public void makeDeposit(UserAccount account, double value) {
		if (value < 0 ) {
			throw new NumberFormatException();
		}
		if (value == 0) {
			return;
		}
		aDao.makeDeposit(account, value);
	}

	public void makeWithdrawal(UserAccount account, double value) {
		if (value < 0) {
			throw new NumberFormatException();
		}
		if (aDao.fetchAccountBalance(account.getAccountId()) - value < 0) {
			throw new InvalidInputException("# Not enough money in account to make withdrawal.");
		}
		if (value == 0) {
			return;
		}
		aDao.makeWithdraw(account, value);
	}

	public UserAccount openAccount(int ownerId, int accType) {
		return aDao.openNewAccount(ownerId, accType);
	}
}

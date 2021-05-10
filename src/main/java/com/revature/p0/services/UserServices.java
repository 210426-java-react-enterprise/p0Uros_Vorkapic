package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.models.AppUser;

public class UserServices {

	private UserDAO userDAO;

	public UserServices(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean authenticateUser(AppUser authUser) {
		return true;
	}

	public void registerUser(AppUser newUser) {

	}

	public boolean isFirstTimeUser(AppUser newUser) {
		return false;
	}
}

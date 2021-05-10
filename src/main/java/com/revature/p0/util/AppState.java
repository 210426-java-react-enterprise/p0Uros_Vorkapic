package com.revature.p0.util;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.screens.LoginScreen;
import com.revature.p0.screens.SignUpScreen;
import com.revature.p0.screens.WelcomeScreen;
import com.revature.p0.services.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {


	private final ScreenRouter router;
	private boolean appRunning;

	public AppState() {

		appRunning = true;
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		final UserDAO userDao = new UserDAO();
		final UserServices userServices = new UserServices(userDao);
		userDao.testConnection();
		router = new ScreenRouter();
		router.addScreen(new WelcomeScreen(consoleReader, router))
				.addScreen(new LoginScreen(consoleReader, router, userServices))
				.addScreen (new SignUpScreen(consoleReader, router, userServices));
	}

	public void startup() {
		while (appRunning) {
			router.navigate("/welcome");
		}
	}

	public void shutdown() {
		this.appRunning = false;
	}
}

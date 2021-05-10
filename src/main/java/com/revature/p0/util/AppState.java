package com.revature.p0.util;

import com.revature.p0.screens.LoginScreen;
import com.revature.p0.screens.SignUpScreen;
import com.revature.p0.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

	private final ScreenRouter router;
	private boolean appRunning;

	public AppState() {
		appRunning = true;
		final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		router = new ScreenRouter();
		router.addScreen(new WelcomeScreen(consoleReader, router))
				.addScreen(new LoginScreen(consoleReader, router))
				.addScreen (new SignUpScreen(consoleReader, router));
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

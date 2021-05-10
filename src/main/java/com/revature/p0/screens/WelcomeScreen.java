package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;

	public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
		super("WelcomeScreen", "/welcome");
		this.consoleReader = consoleReader;
		this.router = router;
	}

	@Override
	public void render() {

	}
}

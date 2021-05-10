package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class AccountScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;

	public AccountScreen(BufferedReader consoleReader, ScreenRouter router) {
		super("AccountScreen", "/accounts");
		this.consoleReader = consoleReader;
		this.router = router;
	}

	@Override
	public void render() {

	}
}

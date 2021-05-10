package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class DashboardScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;

	public DashboardScreen(BufferedReader consoleReader, ScreenRouter router) {
		super("DashboardScreen", "/dashboard");
		this.consoleReader = consoleReader;
		this.router = router;
	}

	@Override
	public void render() {

	}
}

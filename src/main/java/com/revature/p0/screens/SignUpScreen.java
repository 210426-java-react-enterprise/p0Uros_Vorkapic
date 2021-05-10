package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class SignUpScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;

	public SignUpScreen(BufferedReader consoleReader, ScreenRouter router) {
		super("SignUpScreen", "/signup");
		this.consoleReader = consoleReader;
		this.router = router;
	}

	@Override
	public void render() {

	}
}

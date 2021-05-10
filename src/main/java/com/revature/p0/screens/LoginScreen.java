package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;

	public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
		super("LoginScreen", "/login");
		this.consoleReader = consoleReader;
		this.router = router;
	}

	@Override
	public void render() {
		String username;
		String password;

		System.out.println("##################################");

		try {
			System.out.println("# Username: ");
			username = consoleReader.readLine();
			System.out.println("# Password: ");
			password = consoleReader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import static com.revature.p0.Driver.app;

import java.io.BufferedReader;
import java.io.IOException;

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
		printHeader();
		System.out.println("# Welcome to Boolean Bank!                       #");
		System.out.println("# Where you either have an account or you don't! #");
		System.out.println("##################################################");
		System.out.println("# [0] Sign Up!                                   #");
		System.out.println("# [1] Login                                      #");
		System.out.println("# [2] Quit                                       #");
		System.out.println("##################################################");

		try {
			System.out.print(">> ");
			switch (consoleReader.readLine()) {
				case "0":
					router.navigate("/signup");
					break;
				case "1":
					router.navigate("/login");
					break;
				case "2":
					app().shutdown();
					break;
				default:
					System.out.println("# Invalid Selection, try again.                  #");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

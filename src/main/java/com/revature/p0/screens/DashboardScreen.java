package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class DashboardScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private AppUser currentUser;

	public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, AppUser currentUser) {
		super("DashboardScreen", "/dashboard");
		this.consoleReader = consoleReader;
		this.router = router;
		this.currentUser = currentUser;
	}

	@Override
	public void render() {
		printHeader();
		System.out.println("# Welcome to your dashboard!                     #");
		System.out.println("# Here you can manage your profile and accounts! #");
		System.out.println("##################################################");
		System.out.println("# [0] User Profile                               #");
		System.out.println("# [1] Manage Accounts                            #");
		System.out.println("# [2] Log Out                                    #");
		System.out.println("##################################################");

		boolean isLoggedIn = true;
		try {
			while (isLoggedIn) {
				System.out.println(">> ");
				switch (consoleReader.readLine()) {
					case "0":
						router.addScreen(new ProfileScreen(consoleReader, router, currentUser));
						router.navigate("/profile");
						break;
					case "1":
						router.addScreen(new AccountScreen(consoleReader, router, currentUser));
						router.navigate("/accounts");
						break;
					case "2":
						isLoggedIn = false;
						break;
					default:
						System.out.println("##################################################");
						System.out.println("# Invalid Selection, try again.                  #");
						System.out.println("##################################################");
				}
			}
		} catch (IOException e) {

		}
	}
}

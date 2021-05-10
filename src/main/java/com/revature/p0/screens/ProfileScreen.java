package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserInfo;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class ProfileScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private AppUser currentUser;
	private UserInfo userInfo;

	public ProfileScreen(BufferedReader consoleReader, ScreenRouter router, AppUser currentUser) {
		super("ProfileScreen", "/profile");
		this.consoleReader = consoleReader;
		this.router = router;
		this.currentUser = currentUser;
	}

	@Override
	public void render() {

		printHeader();

		System.out.println("##################################################");
	}
}

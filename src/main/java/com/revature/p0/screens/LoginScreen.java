package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.services.UserServices;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private UserServices userService;

	public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserServices userService) {
		super("LoginScreen", "/login");
		this.consoleReader = consoleReader;
		this.router = router;
		this.userService = userService;
	}

	@Override
	public void render() {
		String username;
		String password;

		printHeader();
		try {
			System.out.println("# Username: ");
			username = consoleReader.readLine();
			System.out.println("# Password: ");
			password = consoleReader.readLine();
			AppUser appUser = new AppUser(username, password, null);
			if (userService.authenticateUser(appUser)) {
				router.navigate("/dashboard");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

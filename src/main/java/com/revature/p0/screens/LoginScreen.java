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
		String identifier;
		String password;

		printHeader();
		try {
			System.out.println("# Username/Email: ");
			identifier = consoleReader.readLine();
			System.out.println("# Password: ");
			password = consoleReader.readLine();
			AppUser appUser = new AppUser(identifier, password, identifier);

			// authenticateUser will update appUser username or email accordingly
			if (userService.authenticateUser(appUser)) {
				router.addScreen(new DashboardScreen(consoleReader, router, appUser));
				router.navigate("/dashboard");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

package com.revature.p0.screens;

import com.revature.p0.exceptions.InvalidAuthenticationException;
import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.exceptions.NoInputException;
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
			System.out.print("# Username/Email: ");
			identifier = consoleReader.readLine();
			System.out.print("# Password: ");
			password = consoleReader.readLine();
			AppUser appUser = userService.authenticate(identifier, password);
			if (appUser != null) {
				router.addScreen(new DashboardScreen(consoleReader, router, appUser));
				router.navigate("/dashboard");
			}
		} catch (NoInputException | InvalidInputException | InvalidAuthenticationException e) {
			System.out.println("# " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

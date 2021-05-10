package com.revature.p0.screens;

import com.revature.p0.models.AppUser;
import com.revature.p0.services.UserServices;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class SignUpScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private UserServices userServices;

	public SignUpScreen(BufferedReader consoleReader, ScreenRouter router, UserServices userServices) {
		super("SignUpScreen", "/signup");
		this.consoleReader = consoleReader;
		this.router = router;
		this.userServices = userServices;
	}

	@Override
	public void render() {
		String username;
		String email;
		String password;
		String fName;
		String lName;
		String dob;
		String street;
		String city;
		String state;
		int postalCode;

		printHeader();
		System.out.println("# Register New Account                           #");
		System.out.println("##################################################");
		try {
			System.out.print("# Username: ");
			username = consoleReader.readLine();
			System.out.print("# Email: ");
			email = consoleReader.readLine();

			boolean isValid = false;
			do {
				System.out.print("# Password: ");
				password = consoleReader.readLine();
				System.out.print("# Confirm Password: ");
				if (consoleReader.readLine().equals(password)) {
					isValid = true;
				} else {
					System.out.println("# Passwords do not match!");
					System.out.println("# Please try again.");
				}
			} while (!isValid);
			AppUser newUser = new AppUser(username, password, email);
			userServices.registerUser(newUser);
		} catch (IOException e) {
			e.printStackTrace();
		}




	}
}

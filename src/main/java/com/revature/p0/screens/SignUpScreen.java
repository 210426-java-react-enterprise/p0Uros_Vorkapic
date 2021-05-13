package com.revature.p0.screens;

import com.revature.p0.exceptions.InvalidAuthenticationException;
import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.exceptions.NoInputException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserProfile;
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

		boolean signUpSuccessful = false;
		while(!signUpSuccessful) {
			try {
				System.out.print("# Username: ");
				username = consoleReader.readLine();

				System.out.print("# Email: ");
				email = consoleReader.readLine();

				boolean doesMatch = false;
				do {
					System.out.print("# Password: ");
					password = consoleReader.readLine();
					System.out.print("# Confirm Password: ");
					if (consoleReader.readLine().equals(password)) {
						doesMatch = true;
					} else {
						System.out.println("# Passwords do not match!");
						System.out.println("# Please try again.");
					}
				} while (!doesMatch);

				// ugly, fix later
				doesMatch = false;
				while(!doesMatch) {
					System.out.println("##################################################");
					System.out.println("# [0] Submit                                     #");
					System.out.println("# [1] Redo Entries                               #");
					System.out.println("# [2] Back to Main Menu                          #");
					System.out.println("##################################################");

					System.out.print(">> ");
					String input = consoleReader.readLine();
					switch (input) {
						case "0":
							AppUser newUser = new AppUser(username, password, email);
							userServices.registerUser(newUser);
							signUpSuccessful = true;
							doesMatch = true;
							break;
						case "1":
							doesMatch = true;
							break;
						case "2":
							return;
						default:
							System.out.println("# Invalid input, please try again.               #");

					}
				}
			} catch (NoInputException | InvalidInputException | InvalidAuthenticationException e) {
				System.out.println("##################################################");
				System.out.println("# " + e.getMessage());
				System.out.println("##################################################");
			} catch (IOException e) {
					e.printStackTrace();
			}
		}

	}
}

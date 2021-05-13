package com.revature.p0.screens;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.ProfileDAO;
import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.exceptions.NoInputException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.models.UserProfile;
import com.revature.p0.services.AccountServices;
import com.revature.p0.services.ProfileServices;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class DashboardScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private AppUser currentUser;
	private UserProfile profile;
	private ArrayList<UserAccount> accounts;
	private ProfileServices profileServices;
	private AccountServices accountServices;

	public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, AppUser currentUser) {
		super("DashboardScreen", "/dashboard");
		this.consoleReader = consoleReader;
		this.router = router;
		this.currentUser = currentUser;

		profileServices = new ProfileServices(new ProfileDAO());
		profile = profileServices.findProfileById(currentUser.getId());

		accountServices = new AccountServices(new AccountDAO());
		accounts = accountServices.findAllAccountsById(currentUser.getId());

	}

	@Override
	public void render() {
		router.addScreen(new ProfileScreen(consoleReader, router, profile, profileServices));
		router.addScreen(new AccountScreen(consoleReader, router, accounts, accountServices, currentUser.getId()));
		boolean isLoggedIn = true;
		// Checks if it's a new user
		if (profile == null) {
			isLoggedIn = initNewProfile();
		}

		while (isLoggedIn) {
			printHeader();
			System.out.println("# Welcome to your dashboard!                     #");
			System.out.println("# Here you can manage your profile and accounts! #");
			System.out.println("##################################################");
			System.out.println("# [0] User Profile                               #");
			System.out.println("# [1] Manage Accounts                            #");
			System.out.println("# [2] Log Out                                    #");
			System.out.println("##################################################");
			try {
				System.out.print(">> ");
				switch (consoleReader.readLine()) {
					case "0":
						router.navigate("/profile");
						break;
					case "1":
						accounts = accountServices.findAllAccountsById(currentUser.getId());
						router.navigate("/accounts");
						break;
					case "2":
						isLoggedIn = false;
						break;
					default:
						throw new InvalidInputException("# Invalid Selection, try again.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Prompts user to create a new profile
	 * Code is sloppy, will refactor later if time permits
	 * @return true if user is still logged in by the end, false if they chose to log out instead of completing form
	 */
	private boolean initNewProfile() {
		printHeader();
		System.out.println("# Welcome! It appears you've just made a new     #");
		System.out.println("# account. Please fill out the information below #");
		System.out.println("# and we'll get you started!                     #");
		System.out.println("##################################################");

		boolean isLoggedIn = true;

		while (isLoggedIn) {
			try {
				System.out.print("# First Name: ");
				String fName = consoleReader.readLine();

				System.out.print("# Last Name: ");
				String lName = consoleReader.readLine();

				System.out.print("# Date of Birth (YYYY-MM-DD): ");
				String dob = consoleReader.readLine();

				System.out.print("# Street Address: ");
				String address = consoleReader.readLine();

				System.out.print("# City: ");
				String city = consoleReader.readLine();

				System.out.print("# Two-Letter State: ");
				String state = consoleReader.readLine();

				System.out.print("# 5-Digit Postal Code: ");
				String postal = consoleReader.readLine();

				boolean validInput = true;
				while (validInput) {
					System.out.println("##################################################");
					System.out.println("# [0] Submit                                     #");
					System.out.println("# [1] Redo Form                                  #");
					System.out.println("# [2] Log Out                                    #");
					System.out.println("##################################################");

					System.out.print(">> ");
					String input = consoleReader.readLine();
					switch (input) {
						case "0":
							profile = new UserProfile(currentUser.getId(), fName, lName, dob, address, city, state, postal);
							profileServices.registerProfile(profile);
							validInput = false;
							isLoggedIn = false;
							break;
						case "1":
							validInput = false;
							break;
						case "2":
							return false;
						default:
							System.out.println("# Invalid Input, please try again.               #");

					}
				}

			} catch (NoInputException | InvalidInputException e) {
				System.out.println("##################################################");
				System.out.println(e.getMessage());
				System.out.println("##################################################");
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
		return true;
	}
}

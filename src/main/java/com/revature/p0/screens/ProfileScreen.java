package com.revature.p0.screens;

import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserProfile;
import com.revature.p0.services.ProfileServices;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class ProfileScreen extends Screen {

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private UserProfile userProfile;
	private ProfileServices profileServices;

	public ProfileScreen(BufferedReader consoleReader, ScreenRouter router, UserProfile profile, ProfileServices profileServices) {
		super("ProfileScreen", "/profile");
		this.consoleReader = consoleReader;
		this.router = router;
		this.userProfile = profile;
		this.profileServices = profileServices;
	}

	@Override
	public void render() {

		printHeader();
		System.out.println("# Your Information:                              #");
		System.out.println("##################################################");
		System.out.println("# First Name: " + userProfile.getfName());
		System.out.println("# Last Name: " + userProfile.getlName());
		System.out.println("# Date of Birth: " + userProfile.getDob());
		System.out.println("# Address: " + userProfile.getStreet());
		System.out.println("# City/State/Zip: " + userProfile.getCity() + " " + userProfile.getState() + ", " + userProfile.getPostalCode());
		System.out.println("##################################################");
		System.out.println("# What would you like to do?                     #");
		System.out.println("# [0] Update Information                         #");
		System.out.println("# [1] Back to Dashboard                          #");
		System.out.println("##################################################");

		boolean validInput = false;
		while(!validInput) {
			try {
				System.out.print(">> ");
				String input = consoleReader.readLine();
				switch (input) {
					case "0":
						System.out.println("##################################################");
						updateScreen();
						validInput = true;
						break;
					case "1":
						validInput = true;
						break;
					default:
						System.out.println("# Invalid Input, please try again.               #");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}



		}

	}

	private void updateScreen() {
		boolean isOnScreen = true;
		while(isOnScreen) {
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

				boolean validInput = false;
				while (!validInput) {
					System.out.println("##################################################");
					System.out.println("# [0] Submit Update                              #");
					System.out.println("# [1] Redo Update                                #");
					System.out.println("# [2] Return to Profile                          #");
					System.out.println("##################################################");

					System.out.print(">> ");
					String input = consoleReader.readLine();
					switch (input) {
						case "0":
							validInput = true;
							userProfile = new UserProfile(userProfile.getId(), fName, lName, dob, address, city, state, postal);
							profileServices.updateProfile(userProfile);
							isOnScreen = false;
							break;
						case "1":
							validInput = true;
							break;
						case "2":
							validInput = true;
							isOnScreen = false;
							break;
						default:
							System.out.println("# Invalid Input, please try again.               #");

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
				System.out.println("##################################################");
			}
		}
	}
}

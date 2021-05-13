package com.revature.p0.screens;

import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.models.AppUser;
import com.revature.p0.models.UserAccount;
import com.revature.p0.services.AccountServices;
import com.revature.p0.services.ProfileServices;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class AccountScreen extends Screen {

	private final String[] ACCOUNT_TYPES = {null, "Checking", "Savings"};

	private BufferedReader consoleReader;
	private ScreenRouter router;
	private ArrayList<UserAccount> accountsList;
	private AccountServices accountServices;
	private int ownerId;

	public AccountScreen(BufferedReader consoleReader, ScreenRouter router, ArrayList<UserAccount> accountsList, AccountServices accountServices, int ownerId) {
		super("AccountScreen", "/accounts");
		this.consoleReader = consoleReader;
		this.router = router;
		this.accountsList = accountsList;
		this.accountServices = accountServices;
		this.ownerId = ownerId;
	}

	@Override
	public void render() {
		printHeader();
		System.out.println("# Welcome to your accounts screen!               #");
		System.out.println("# Below are all of your current active accounts. #");
		System.out.println("##################################################");
		if (accountsList.get(0) == null) {
			System.out.println("# There are no accounts to display yet!          #");
			renderWithoutAccounts();
		} else {
			for (int i = 0; i < accountsList.size(); i++) {
				String s = String.format("# %1$2d | %2$-23s | %3$15s #"
										, (i + 1)
										, ACCOUNT_TYPES[accountsList.get(i).getAccountType()]
										, accountsList.get(i).getBalance());
				System.out.println(s);
			}
			renderWithAccounts();
		}



	}

	private void renderWithoutAccounts() {
		String input;
		boolean validInput = false;
		while(!validInput) {
			System.out.println("##################################################");
			System.out.println("# What would you like to do?                     #");
			System.out.println("# [0] Create New Account                         #");
			System.out.println("# [1] Back to Dashboard                          #");
			System.out.println("##################################################");
			System.out.print(">> ");
			try {
				input = consoleReader.readLine();
				switch(input) {
					case "0":
						openAccount();
						validInput = true;
						break;
					case "1":
						validInput = true;
						break;
					default:
						throw new InvalidInputException("# Invalid Selection, try again.");
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void renderWithAccounts() {
		String input;
		boolean validInput = false;
		while(!validInput) {
			System.out.println("##################################################");
			System.out.println("# What would you like to do?                     #");
			System.out.println("# [0] Make Deposit                               #");
			System.out.println("# [1] Make Withdrawal                            #");
			System.out.println("# [2] Create New Account                         #");
			System.out.println("# [3] Back to Dashboard                          #");
			System.out.println("##################################################");
			System.out.print(">> ");
			try {
				input = consoleReader.readLine();
				switch(input) {
					case "0":
						makeDeposit();
						validInput = true;
						break;
					case "1":
						makeWithdrawal();
						validInput = true;
						break;
					case "2":
						openAccount();
						validInput = true;
						break;
					case "3":
						validInput = true;
						break;
					default:
						throw new InvalidInputException("# Invalid Selection, try again.");
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void makeDeposit() {
		String input;
		int accountIndex = 0;
		System.out.println("##################################################");
		System.out.println("# Into which account?                            #");
		boolean validInput = false;
		while (!validInput) {
			System.out.print(">> ");
			try {
				input = consoleReader.readLine();
				for (int i = 0; i < accountsList.size(); i++) {
					if (Integer.parseInt(input) == i + 1) {
						accountIndex = i;
						validInput = true;
					}
				}
				if (!validInput) {
					throw new InvalidInputException("# Invalid Selection, try again.");
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		validInput = false;
		System.out.println("##################################################");
		System.out.println("# How much would you like to deposit?            #");
		while(!validInput) {
			System.out.print(">> ");
			try {
				input = consoleReader.readLine();
				double value = Double.parseDouble(input);
				accountServices.makeDeposit(accountsList.get(accountIndex), value);
				accountsList = accountServices.findAllAccountsById(accountsList.get(0).getOwnerId());
				validInput = true;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("# Invalid Amount.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void makeWithdrawal() {
		String input;
		int accountIndex = 0;
		System.out.println("##################################################");
		System.out.println("# From which account?                            #");
		boolean validInput = false;
		while (!validInput) {
			System.out.print(">> ");
			try {
				input = consoleReader.readLine();
				for (int i = 0; i < accountsList.size(); i++) {
					if (Integer.parseInt(input) == i + 1) {
						accountIndex = i;
						validInput = true;
					}
				}
				if (!validInput) {
					throw new InvalidInputException("# Invalid Selection, try again.");
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		validInput = false;
		System.out.println("##################################################");
		System.out.println("# How much would you like to withdraw?           #");
		while(!validInput) {
			System.out.print(">> ");
			try {
				input = consoleReader.readLine();
				double value = Double.parseDouble(input);
				accountServices.makeWithdrawal(accountsList.get(accountIndex), value);
				accountsList = accountServices.findAllAccountsById(accountsList.get(0).getOwnerId());
				validInput = true;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("# Invalid Amount.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void openAccount() {
		System.out.println("##################################################");
		System.out.println("# What kind of account would you like to open?   #");
		for (int i = 0; i < ACCOUNT_TYPES.length ; i++) {
			if (i == ACCOUNT_TYPES.length - 1) {
				System.out.println("# [" + i + "] Return to Accounts screen");
			} else {
				System.out.println("# [" + i + "] " + ACCOUNT_TYPES[i+1]);
			}
		}
		System.out.println("##################################################");
		boolean isValid = false;
		while(!isValid) {
			try {
				System.out.print(">> ");
				String input = consoleReader.readLine();
				switch (input) {
					case "0":
						accountsList.add(accountServices.openAccount(ownerId, 1));
						isValid = true;
						break;
					case "1":
						accountsList.add(accountServices.openAccount(ownerId, 2));
						isValid = true;
						break;
					case "2":
						isValid = true;
						break;
					default:
						throw new InvalidInputException("# Invalid Selection, try again.");
				}


			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
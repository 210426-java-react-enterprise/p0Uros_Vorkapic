package com.revature.p0.services;

import com.revature.p0.daos.ProfileDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.InvalidAuthenticationException;
import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.exceptions.NoInputException;
import com.revature.p0.models.AppUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Business Logic layer for user table
 */
public class UserServices {

	private final UserDAO uDao;

	public UserServices(UserDAO uDao) {
		this.uDao = uDao;
	}

	/**
	 * Authenticates provided credentials to ensure username and password are valid
	 * @param identifier username or email
	 * @param password .
	 * @return AppUser if successful, null otherwise
	 * @throws NoInputException If input is "empty"
	 * @throws InvalidInputException Username or email contain invalid characters
	 * @throws InvalidAuthenticationException If username or email do not exist
	 */
	public AppUser authenticate(String identifier, String password)  throws NoInputException, InvalidInputException, InvalidAuthenticationException {
		if (identifier == null || password == null || password.trim().isEmpty() || identifier.trim().isEmpty()) {
			throw new NoInputException("# No value detected. Please try again");
		}

		String identifierType = identifier.contains("@") ? "email" : "username"; // checks if identifier is username or email
		if (!this.isValidIdentifier(identifier, identifierType)) {
			throw new InvalidInputException("# Invalid " + identifierType + ". Please try again.");
		}

		AppUser authUser = uDao.findByIdentifierAndPassword(identifier, password, identifierType);
		if (authUser == null) {
			throw new InvalidAuthenticationException("# User could not be found. Please try again");
		}
		return authUser;
	}

	/**
	 * Verifies user if user is valid, then invokes UserDAO to insert it into database if it is
	 * @param newUser user being registered
	 */
	public void registerUser(AppUser newUser) {
		if (!isValidUser(newUser)) {
			throw new InvalidInputException("# Invalid inputs, please try again.");
		}
		if (!uDao.isUserIdentifierAvailable(newUser.getUsername(), "username") || !uDao.isUserIdentifierAvailable(newUser.getEmail(), "email")) {
			throw new InvalidAuthenticationException("# Username or email is already taken!");
		}
		uDao.insertUser(newUser);

	}

	/**
	 * Verifies whether param user is a valid user
	 * @param user being tested
	 * @return false if anything is invalid inside the user object, true otherwise
	 */
	private boolean isValidUser(AppUser user) {
		if (user == null) return false;
		if (user.getUsername() == null || user.getUsername().trim().isEmpty() || !isValidIdentifier(user.getUsername(), "username")) return false;
		if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 50) return false;
		if (user.getEmail() == null || user.getEmail().trim().isEmpty() || !isValidIdentifier(user.getEmail(), "email")) return false;
		return true;
	}

	/**
	 * Checks whether the provided identifier falls within certain REGEX restrictions
	 * REGEX email: ^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[.][a-zA-Z0-9]+$
	 * 		- ^begins_with [these characters] +any_number_of_times,
	 * 		- then the '@' character
	 * 		- then [these characters] +any_number_of_times
	 * 		- then the '.' character
	 * 		- then $ends_with [these characters] +any_number_of_times;
	 * REGEX username: ^[a-zA-Z0-9_.-]+$
	 * 		- contains [these characters] +any_number_of_times
	 * @param identifier string being validated
	 * @param type expected inputs: "username" or "email"
	 * @return true if valid identifier, false otherwise
	 */
	private boolean isValidIdentifier(String identifier, String type) {
		Pattern pattern;
		Matcher match;
		if (type.equals("email")) {
			pattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[.][a-zA-Z0-9]+$");
			match = pattern.matcher(identifier);
			return match.find() && identifier.length() < 255;
		} else {
			pattern = Pattern.compile("^[a-zA-Z0-9_.-]+$");
			match = pattern.matcher(identifier);
			return match.find() && identifier.length() < 20;
		}

	}
}

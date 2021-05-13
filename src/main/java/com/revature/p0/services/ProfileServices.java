package com.revature.p0.services;

import com.revature.p0.daos.ProfileDAO;
import com.revature.p0.exceptions.InvalidAuthenticationException;
import com.revature.p0.exceptions.InvalidInputException;
import com.revature.p0.exceptions.NoInputException;
import com.revature.p0.models.UserProfile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileServices {

	private final ProfileDAO pDao;

	public ProfileServices(ProfileDAO pDao) {
		this.pDao = pDao;
	}

	/**
	 * Finds user profile based on id
	 * @param id of logged in user
	 * @return User Profile matching id
	 */
	public UserProfile findProfileById(int id) {
		return pDao.fetchProfile(id);
	}

	/**
	 *
	 * @param profile
	 * @throws InvalidAuthenticationException
	 */
	public void registerProfile(UserProfile profile) throws InvalidAuthenticationException {
		validate(profile);
		if (!pDao.doesProfileExist(profile.getId())) {
			pDao.insertNewProfile(profile);
		} else {
			throw new InvalidAuthenticationException("# Profile already exists for this user");
		}

	}

	/**
	 *
	 * @param profile
	 */
	public void updateProfile(UserProfile profile) {
		validate(profile);
		pDao.updateProfile(profile);
	}

	/**
	 *
	 * @param profile
	 * @throws NoInputException
	 * @throws InvalidInputException
	 * @throws InvalidAuthenticationException
	 */
	private void validate(UserProfile profile) throws NoInputException, InvalidInputException, InvalidAuthenticationException {
		if (profile.isEmpty()){
			throw new NoInputException ("# Please do not leave anything blank!");
		}

		if (!Pattern.compile("^[a-zA-Z-\\s]+$").matcher(profile.getfName()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your first name                                #\n" +
											"# Please enter your information again.           #");
		if (!Pattern.compile("^[a-zA-Z-\\s]+$").matcher(profile.getlName()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your last name                                 #\n" +
											"# Please enter your information again.           #");
		if (!Pattern.compile("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$").matcher(profile.getDob()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your date of birth                             #\n" +
											"# Please enter your information again.           #");
		if (!Pattern.compile("^[a-zA-z0-9\\s]+$").matcher(profile.getStreet()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your street address                            #\n" +
											"# Please enter your information again.           #");
		if (!Pattern.compile("^[a-zA-z\\s]+$").matcher(profile.getCity()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your city                                      #\n" +
											"# Please enter your information again.           #");
		if (!Pattern.compile("^([a-zA-z]{2})$").matcher(profile.getState()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your state                                     #\n" +
											"# Please enter your information again.           #");
		if (!Pattern.compile("^(\\d{5})$").matcher(profile.getPostalCode()).find())
			throw new InvalidInputException("# You seem to have made a mistake when entering  #\n" +
											"# your postal code                               #\n" +
											"# Please enter your information again.           #");
	}
}

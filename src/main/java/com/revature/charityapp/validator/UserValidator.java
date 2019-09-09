package com.revature.charityapp.validator;

import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.User;
import com.revature.charityapp.ui.Signin;

public class UserValidator {
	public void validateBeforeRegistration(User user) throws ValidatorException {
		
		rejectIfInvalidNumber(user.getId(), "Invalid id");

		rejectIfInvalidString(user.getName(), "Invalid Name");

		rejectIfInvalidString(user.getEmail(), "Invalid email");

		rejectIfInvalidString(user.getPassword(), "Invalid Password");

	}

	public void rejectIfInvalidString(String input, String message) throws ValidatorException {
		if (isInvalidString(input)) {
			throw new ValidatorException(message);
		}
	}

	public void rejectIfInvalidNumber(int input, String message) throws ValidatorException {
		if (isInvalidNumber(input)) {
			throw new ValidatorException(message);
		}

	}

	private boolean isInvalidNumber(int input) {
		return input == 0;
	}

	private boolean isInvalidString(String name) {
		return name == null || "".equals(name.trim());
	}
}

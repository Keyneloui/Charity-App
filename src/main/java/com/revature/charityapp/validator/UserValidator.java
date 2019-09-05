package com.revature.charityapp.validator;

import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.User;

public class UserValidator {
	public static void validateBeforeRegistration(User user) throws ValidatorException {
		String name = user.getName();
		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		}
	}

}

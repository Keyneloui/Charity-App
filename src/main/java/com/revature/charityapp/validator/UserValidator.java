package com.revature.charityapp.validator;

import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.User;

public class UserValidator {
	public void validateBeforeRegistration(User user) throws ValidatorException {

		int donorId = user.getId();
		if (donorId == 0) {
			throw new ValidatorException("Invalid DonorId");
		}
		String name = user.getName();
		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		}
		String email = user.getEmail();
		if (email == null || "".equals(email.trim())) {
			throw new ValidatorException("Invalid Email");
		}
		String password = user.getPassword();
		if (password == null || "".equals(password.trim())) {
			throw new ValidatorException("Invalid password");
		}
		String role = user.getRole();
		if (role == null || "".equals(role.trim())) {
			throw new ValidatorException("Invalid password");
		}

	}
}

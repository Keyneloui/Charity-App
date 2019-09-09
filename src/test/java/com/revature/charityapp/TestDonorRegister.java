package com.revature.charityapp;

import org.junit.Test;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.User;

public class TestDonorRegister {

	@Test
	public void Donortest() throws DBException {
		User user = new User();
		user.setId(900);
		user.setName(" ");
		user.setEmail("a@gmail.com");
		user.setPassword("mypass");
		UserDAO ob = new UserDAOImpl();
		ob.register(user);

	}

}

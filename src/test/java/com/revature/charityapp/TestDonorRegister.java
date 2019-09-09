package com.revature.charityapp;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.InputMismatchException;

import org.junit.Test;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.User;

public class TestDonorRegister {

	@Test
	public void Donortest() throws DBException {
		User user = new User();
		user.setId(1);
		user.setName(" ");
		user.setEmail("ajithkumar12@gmail.com");
		user.setPassword("mypass");
		UserDAO ob = new UserDAOImpl();
		ob.register(user);
		
	}
	try {
		welcomePage();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		try {
			welcomePage();
		} catch (InputMismatchException e1) {
			System.out.println(e1.getMessage());
		} catch (DBException e1) {
			System.out.println(e1.getMessage());

		} catch (ValidatorException e1) {
			System.out.println(e1.getMessage());

		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
		
	

}

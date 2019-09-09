package com.revature.charityapp;

import org.junit.Test;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.User;

public class TestDonorLogin {

	@Test
	public void test() throws DBException {

		String email = "a@gmail.com";
		String pwd = "mypass";
		System.out.println("Email:" + email);
		System.out.println("password+" + pwd);
		UserDAO ud = new UserDAOImpl();
		User userLogin = ud.donorLogin(email, pwd);
	}

}

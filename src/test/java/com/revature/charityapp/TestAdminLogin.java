package com.revature.charityapp;

import org.junit.Test;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.User;

public class TestAdminLogin {

	//@Test
	public void Admintest() throws DBException {

		String email = "k@gmail.com";
		String pwd = "123";
		System.out.println("Email:" + email);
		System.out.println("password" + pwd);
		UserDAO ob1 = new UserDAOImpl();
		User adminLogin = ob1.adminLogin(email, pwd);
	}

}

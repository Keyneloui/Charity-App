package com.revature.charityapp.ui;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.model.User;
import com.revature.charityapp.util.ConnectionUtil;
import com.revature.charityapp.validator.UserValidator;

public class Signin {

	static Scanner sc = new Scanner(System.in);
	static Connection con = ConnectionUtil.getConnection();

	public static void main(String[] args) throws Exception {
		welcomePage();
	}

	public static void welcomePage() throws Exception {

		System.out.println("WELCOME TO REVATURE CHARITY ");

		System.out.println("1.  Register ");
		System.out.println("2.  Donor Log-in ...");
		System.out.println("3.  Admin Log-in...");
		System.out.println("4.  Exit...");
		System.out.println("Enter Your Choice :");
		int val = sc.nextInt();
		switch (val) {
		case 1:
			System.out.println("Setup a donor id");
			int donor_id = sc.nextInt();
			System.out.println("Enter your name:");
			String name = sc.next();
			System.out.println("Enter your email:");
			String email_id1 = sc.next();
			System.out.println("Setup a password:");
			String setpassword = sc.next();
			System.out.println("Enter you role as D for donor");
			String role = sc.next();
			User user = new User();
			user.setId(donor_id);
			user.setName(name);
			user.setEmail(email_id1);
			user.setPassword(setpassword);
			user.setRole(role);
			UserValidator.validateBeforeRegistration(user);
			UserDAO.register(user);
			DonorFunction.operations();
			break;
		case 2:

			System.out.println("Enter your email:");
			String email_id = sc.next();
			System.out.println("Enter the password:");
			String password = sc.next();
			User userLogin = UserDAO.donorLogin(email_id, password);
			if (userLogin == null) {
				throw new Exception("Ïnvalid login");
			} else {
				DonorFunction.operations();
			}
			break;
		case 3:
			System.out.println("Enter your email:");
			String email_ids = sc.next();
			System.out.println("Enter the password:");
			String passwords = sc.next();
			User adminLogin = UserDAO.adminLogin(email_ids, passwords);
			if (adminLogin == null) {
				throw new Exception("Ïnvalid login");
			} else {
				AdminFunction.operations();
			}
			break;
		case 4:
			System.out.println("Thank You");
		default:
			System.out.println("Invalid  credentials");

		}

	}

}

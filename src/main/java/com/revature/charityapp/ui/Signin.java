package com.revature.charityapp.ui;

import java.util.Scanner;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOimpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.User;
import com.revature.charityapp.validator.UserValidator;

public class Signin {

	public static void main(String[] args) throws DBException {
		try {
			welcomePage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void welcomePage() throws DBException, ValidatorException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO REVATURE CHARITY ");

		System.out.println("1.  Register ");
		System.out.println("2.  Donor Log-in ...");
		System.out.println("3.  Admin Log-in...");
		System.out.println("4.  Exit...");
		System.out.println("Enter Your Choice :");
		int val = sc.nextInt();
		UserValidator uv = new UserValidator();
		/** Donor Registration **/

		switch (val) {
		case 1:
			System.out.println("Setup a donor id");
			int donorId = sc.nextInt();
			System.out.println("Enter your name:");
			String name = sc.next();
			System.out.println("Enter your email:");
			String emailId1 = sc.next();
			System.out.println("Setup a password:");
			String setpassword = sc.next();
			System.out.println("Enter you role as D for donor");
			String role = sc.next();
			User user = new User();
			user.setId(donorId);
			user.setName(name);
			user.setEmail(emailId1);
			user.setPassword(setpassword);
			user.setRole(role);
			try {
				uv.validateBeforeRegistration(user);

				UserDAO ob = new UserDAOimpl();
				ob.register(user);
				DonorFunction.operations();

			} catch (ValidatorException e) {
				e.printStackTrace();
				throw new ValidatorException("Unable to process your request", e);
			}

			break;
		/** Donor Login **/
		case 2:

			System.out.println("Enter your email:");
			String emailId = sc.next();
			System.out.println("Enter the password:");
			String password = sc.next();

			UserDAO obj = new UserDAOimpl();
			User userLogin = obj.donorLogin(emailId, password);
			if (userLogin == null) {
				throw new DBException("Ïnvalid login");
			} else {
				try {
					DonorFunction.operations();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		/** Admin Login **/
		case 3:
			System.out.println("Enter your email:");
			String emailIds = sc.next();
			System.out.println("Enter the password:");
			String passwords = sc.next();
			UserDAO ob1 = new UserDAOimpl();
			User adminLogin = ob1.adminLogin(emailIds, passwords);
			if (adminLogin == null) {
				throw new DBException("Ïnvalid login");
			} else {
				AdminFunction.operations();
			}
			break;
		case 4:
			System.out.println("Thank You");
			break;
		default:
			System.out.println("Invalid  credentials");

		}

	}

}

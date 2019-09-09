package com.revature.charityapp.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.User;
import com.revature.charityapp.validator.UserValidator;

public class Signin {

	public static void main(String[] args) {
		try {
			welcomePage();
		} catch (DBException | ValidatorException | SQLException | InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void welcomePage() throws DBException, ValidatorException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO REVATURE CHARITY ");

		try {
			System.out.println("1.  Donor Register ");
			System.out.println("2.  Donor Log-in ...");
			System.out.println("3.  Admin Log-in...");
			System.out.println("4.  Exit...");
			System.out.println("Enter Your Choice :");
			int val = sc.nextInt();
			UserValidator uv = new UserValidator();
			/** Donor Registration **/
			// try {
			switch (val) {
			case 1:
				User user = new User();
				System.out.println("create a donor id\nYour donor id must be in numbers and it shouldn't be zero");
				int donorId = sc.nextInt();

				System.out.println("Enter your name:");
				String name = sc.next();
				name(name);

				System.out.println("Enter your email:");
				String emailId1 = sc.next();
				mail(emailId1);

				System.out.println("Setup a password:");
				String setpassword = sc.next();

				user.setId(donorId);
				user.setName(name);
				user.setEmail(emailId1);
				user.setPassword(setpassword);
				try {
					uv.validateBeforeRegistration(user);

					UserDAO ob = new UserDAOImpl();
					ob.register(user);
					DonorFunction.operations();

				} catch (ValidatorException e) {
					throw new ValidatorException("Unable to process your request", e);
				} catch (InputMismatchException e) {
					throw new InputMismatchException("Give correct Inputs");
				}

				break;
			/** Donor Login **/
			case 2:

				System.out.println("Enter your registered email:");
				String emailId = sc.next();
				mail(emailId);
				System.out.println("Enter the password:");
				String password = sc.next();

				UserDAO ud = new UserDAOImpl();
				User userLogin = ud.donorLogin(emailId, password);
				if (userLogin == null) {
					throw new DBException("Ïnvalid Email-id and password");
				} else {
					try {
						DonorFunction.operations();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						DonorFunction.donationRequest();

					}
				}
				break;
			/** Admin Login **/
			case 3:
				System.out.println("Enter your email:");
				String emailIds = sc.next();
				mail(emailIds);

				System.out.println("Enter the password:");
				String passwords = sc.next();
				UserDAO ob1 = new UserDAOImpl();
				User adminLogin = ob1.adminLogin(emailIds, passwords);
				if (adminLogin == null) {
					throw new DBException("Ïnvalid Email-id and password");
				} else {
					AdminFunction.operations();
				}
				break;
			case 4:
				System.out.println("Thank You");
				break;
			default:
				System.out.println("Wrong Choice");
				welcomePage();

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			welcomePage();
		}

		sc.close();
	}/*
		 * catch(IOException e) { throw new IOException("Wrong choice",e); }
		 */

	public static void mail(String emailIds) {
		String regex = "^(.+)@(.+)$";

		String status = null;
		boolean loop = true;
		Pattern pattern = Pattern.compile(regex);
		Scanner sc = new Scanner(System.in);
		String email = emailIds;
		do {
			if (status != null && !status.equals("")) {
				System.out.println("Enter your  registered email:");
				email = sc.next();
			}
			Matcher matcher = pattern.matcher(email);
			// System.out.println("Hello"+matcher);
			if (!matcher.matches()) {
				System.out.println(" Email id is not in correct convention,Press any key for Re-entering the mail ");

				status = sc.next();

			} else {
				loop = false;
			}

			pattern = Pattern.compile(regex);
		} while (loop);

	}

	public static void name(String names) {
		String regex = "^[a-zA-Z]+$";
		String status = null;
		boolean loop = true;
		Pattern pattern = Pattern.compile(regex);
		Scanner sc = new Scanner(System.in);
		String name = names;
		do {
			if (status != null && !status.equals("")) {
				System.out.println("Enter your Name:");
				name = sc.next();
			}
			Matcher matcher = pattern.matcher(name);
			// System.out.println("Hello"+matcher);
			if (!matcher.matches()) {
				System.out.println(" Name is not in correct convention,Press any key for Re-entering your name ");

				status = sc.next();

			} else {
				loop = false;
			}

			pattern = Pattern.compile(regex);
		} while (loop);

	}

}

package com.revature.charityapp.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.charityapp.dao.DonationDAO;
import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.model.User;
import com.revature.charityapp.util.ConnectionUtil;

public class AdminFunction {
	static Scanner scn = new Scanner(System.in);
	static Connection con = ConnectionUtil.getConnection();

	public static void operations() throws SQLException {

		System.out.println("Select your preference");
		System.out.println(" 1. View Donation Requests\n 2. Donor Details\n 3. Donor Activity\n 4. Log-out");
		Scanner scn1 = new Scanner(System.in);

		int num1 = scn1.nextInt();
		switch (num1) {
		case 1:
			System.out.println("Donation Requests");
			donationRequest();
			operations();
			break;
		case 2:
			System.out.println("Donor Details");
			donorDetails();
			operations();
			break;
		case 3:
			System.out.println("Donor Activity");
			donorActivity();
			operations();
		case 4:
			System.out.println("Thank You");
			try {
				Signin.welcomePage();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	public static void donationRequest() throws SQLException

	{
		try {
			List<DonationRequest> list = DonationDAO.findAll();
			for (DonationRequest dr : list) {

				System.out.println(dr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Scanner scn = new Scanner(System.in);
		System.out.println("--------Do you wish to ADD/UPDATE any request:-------");
		String str = scn.next();
		if (str.equals("ADD")) {
			System.out.println("Enter request id:");
			int request_id = scn.nextInt();
			System.out.println("Enter you request type:");
			String request_type = scn.next();
			System.out.println("Enter the request amount:");
			double request_amount = scn.nextDouble();
			DonationRequest dr = new DonationRequest();
			dr.setRequestId(request_id);
			dr.setRequestType(request_type);
			dr.setRequestAmount(request_amount);
			DonationDAO.addDonations(dr);
		} else if (str.equals("UPDATE")) {
			System.out.println("Enter request type:");
			String request_type = scn.next();
			System.out.println("Enter the updated amount:");
			double request_amount = scn.nextDouble();
			DonationRequest drr = new DonationRequest();
			drr.setRequestType(request_type);
			drr.setRequestAmount(request_amount);
			DonationDAO.updateDonations(drr);
		} else
			System.out.println("Invalids");

	}

	public static void donorDetails() {
		try {
			List<User> list = UserDAO.findAll();
			for (User user : list) {

				System.out.println(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void donorActivity() throws SQLException {
		UserDAO.displayActivity();
	}

}

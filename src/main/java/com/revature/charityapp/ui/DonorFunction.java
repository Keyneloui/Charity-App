package com.revature.charityapp.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.charityapp.dao.DonationDAO;
import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.util.ConnectionUtil;

public class DonorFunction {
	static Scanner scn = new Scanner(System.in);
	static Connection con = ConnectionUtil.getConnection();

	public static void operations() throws SQLException {

		System.out.println("Select your preference");
		System.out.println("  1. Our Donation Requests\n  2. Log-out");
		Scanner scn1 = new Scanner(System.in);

		int num1 = scn1.nextInt();
		switch (num1) {
		case 1:
			System.out.println("Donation Requests");
			donationRequest();
			operations();
			break;
		case 2:
			System.out.println("Thank You");
			try {
				Signin.welcomePage();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				Signin.welcomePage();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Invalids");
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
		System.out.println("--------Do you wish to Contribute for our requests:YES/NO-------");
		String str = scn.next();
		if (str.equals("YES")) {
			System.out.println("Enter Your email_id:");
			String email_id = scn.next();
			System.out.println("Enter request type:");
			String request_type = scn.next();
			DonationRequest drr = new DonationRequest();
			drr.setRequestType(request_type);
			DonationDAO.findByRequestType(request_type);
			System.out.println("Enter the amount you want to contribute:");
			double amount = scn.nextDouble();
			System.out.println("Payment\nEnter your accountNo:");
			long account_no = scn.nextLong();
			System.out.println("Payment Success");
			double request_amount = amount;
			drr.setRequestAmount(request_amount);
			DonationDAO.updateDonations(drr);
			UserDAO.donorActivity(email_id, amount, request_type);
		} else {
			System.out.println("ThanK You");
			try {
				Signin.welcomePage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

package com.revature.charityapp.ui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.charityapp.dao.DonationDAOImpl;
import com.revature.charityapp.dao.DonationDAO;
import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.util.ConnectionUtil;

public class DonorFunction {
	/**
	 * Donor functions
	 * 
	 * @throws DBException
	 * @throws ValidatorException
	 **/

	public static void operations() throws DBException, ValidatorException {
		Connection con = ConnectionUtil.getConnection();

		System.out.println("Select your preference");
		System.out.println("  1. Our Donation Requests\n  2. Log-out");
		Scanner scn = new Scanner(System.in);

		int num1 = scn.nextInt();
		switch (num1) {
		case 1:
			System.out.println("Donation Requests");
			try {
				donationRequest();
				operations();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}

			break;
		case 2:
			System.out.println("Thank You");
			try {
				Signin.welcomePage();
			} catch (ValidatorException e) {
				System.out.println(e.getMessage());

				// throw new ValidatorException("Unable to process the request", e);
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {
				ConnectionUtil.close(con, scn);
				;
			}
			break;
		default:
			System.out.println("Wrong Choice");
		}

	}

	/**
	 * View Donation Request
	 * 
	 * @throws DBException
	 * @throws ValidatorException
	 */

	public static void donationRequest() throws DBException, ValidatorException, SQLException

	{
		Connection con = ConnectionUtil.getConnection();

		DonationDAO dao = new DonationDAOImpl();
		UserDAO udao = new UserDAOImpl();
		try {

			List<DonationRequest> list = dao.findAll();
			DisplayUtil.display(list);
		} catch (DBException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to process the request", e);
		}
		Scanner scn = new Scanner(System.in);
		System.out.println("--------Do you wish to Contribute for our requests:Yes/No-------");
		String str = scn.next();
		DonationRequest drr = new DonationRequest();
		if (str.equalsIgnoreCase("yes")) {
			System.out.println("Enter Your donor id:");
			int donorId = scn.nextInt();
			System.out.println("Enter request type:");
			String requestType = scn.next();

			drr.setRequestType(requestType);

			drr = dao.findByRequestType(requestType);
			if (drr == null) {
				System.out.println("Invalid Request Type");
				donationRequest();
			} else {
				System.out.println("Enter the amount you want to contribute:");
				double amount = scn.nextDouble();
				System.out.println("Payment\nEnter your accountNo:");
				long accountno = scn.nextLong();
				System.out.println("Payment Success");
				double requestAmount = amount;
				drr.setRequestAmount(requestAmount);
				dao.updateDonations(drr);
				Date date = null;
				udao.donorActivity(donorId, amount, requestType, date);
				operations();
			}
		} else if (str.equals("No")) {
			System.out.println("ThanK You");
		} else {
			System.out.println("Thank You");
		}
		try {
			Signin.welcomePage();
		} catch (ValidatorException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, scn);
		}

	}

}

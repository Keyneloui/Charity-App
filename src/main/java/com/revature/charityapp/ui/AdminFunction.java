package com.revature.charityapp.ui;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.revature.charityapp.dao.DonationDAOimpl;
import com.revature.charityapp.dao.DonationDAO;
import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOimpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.model.User;
import com.revature.charityapp.util.ConnectionUtil;

public class AdminFunction {
	/**
	 * Admin Functions
	 * 
	 * @throws DBException
	 * @throws ValidatorException
	 **/

	public static void operations() throws DBException, ValidatorException {
		Connection con = ConnectionUtil.getConnection();
		System.out.println("Select your preference");
		System.out.println(" 1. View Donation Requests\n 2. Donor Details\n 3. Donor Activity\n 4. Log-out");
		Scanner scn = new Scanner(System.in);

		int num1 = scn.nextInt();
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
			} catch (ValidatorException e) {
				e.printStackTrace();
				throw new ValidatorException("Unable to process the request", e);
			} finally {
				ConnectionUtil.close(con, scn);
			}
		}

	}

	/**
	 * ADD UPDATE Donation Request
	 * 
	 * @throws DBException
	 **/

	public static void donationRequest() throws DBException

	{
		Connection con = ConnectionUtil.getConnection();
		Scanner scn = new Scanner(System.in);

		DonationDAO dao = new DonationDAOimpl();
		try {
			List<DonationRequest> list = dao.findAll();
			for (DonationRequest dr : list) {

				System.out.println(dr);
			}

			System.out.println("--------Do you wish to ADD/UPDATE any request:-------");
			String str = scn.next();
			if (str.equals("ADD")) {
				System.out.println("Enter request id:");
				int requestId = scn.nextInt();
				System.out.println("Enter you request type:");
				String requestType = scn.next();
				System.out.println("Enter the request amount:");
				double requestAmount = scn.nextDouble();
				DonationRequest dr = new DonationRequest();
				dr.setRequestId(requestId);
				dr.setRequestType(requestType);
				dr.setRequestAmount(requestAmount);
				dao.addDonations(dr);
			} else if (str.equals("UPDATE")) {
				System.out.println("Enter request type:");
				String requestType = scn.next();
				System.out.println("Enter the updated amount:");
				double requestAmount = scn.nextDouble();
				DonationRequest drr = new DonationRequest();
				drr.setRequestType(requestType);
				drr.setRequestAmount(requestAmount);
				dao.updateDonations(drr);
			} else
				System.out.println("Invalids");
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to process the request", e);
		} finally {
			ConnectionUtil.close(con, scn);
		}
	}

	/**
	 * View Donor Details
	 * 
	 * @throws DBException
	 **/
	public static void donorDetails() throws DBException {
		UserDAO iudao = new UserDAOimpl();
		try {
			List<User> list = iudao.findAll();
			for (User user : list) {

				System.out.println(user);
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to process the request", e);
		}

	}

	/**
	 * View Donor Activity
	 * 
	 * @throws DBException
	 **/
	public static void donorActivity() throws DBException {
		UserDAO udao = new UserDAOimpl();
		try {
			udao.displayActivity();
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to process the request", e);

		}
	}

}

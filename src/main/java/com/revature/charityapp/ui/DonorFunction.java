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
import com.revature.charityapp.util.ConnectionUtil;

public class DonorFunction {
	/**Donor functions
	 * @throws DBException
	 * @throws ValidatorException**/

	public static void operations() throws DBException, ValidatorException {
		Connection con = ConnectionUtil.getConnection();

		System.out.println("Select your preference");
		System.out.println("  1. Our Donation Requests\n  2. Log-out");
		Scanner scn = new Scanner(System.in);

		int num1 = scn.nextInt();
		switch (num1) {
		case 1:
			System.out.println("Donation Requests");
			donationRequest();

			break;
		case 2:
			System.out.println("Thank You");
			try {
				Signin.welcomePage();
			} catch (ValidatorException e) {
				e.printStackTrace();
				throw new ValidatorException("Unable to process the request", e);
			} finally {
				ConnectionUtil.close(con, scn);
				;
			}
			break;
		default:
			System.out.println("Invalids");
		}

	}
	/**
	 * View Donation Request
	 * @throws DBException
	 * @throws ValidatorException
	 */

	public static void donationRequest() throws DBException, ValidatorException

	{
		Connection con = ConnectionUtil.getConnection();

		DonationDAO obj = new DonationDAOimpl();
		UserDAO ob = new UserDAOimpl();
		try {

			List<DonationRequest> list = obj.findAll();
			for (DonationRequest dr : list) {

				System.out.println(dr);
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new DBException("Unable to process the request",e);
		}
		Scanner scn = new Scanner(System.in);
		System.out.println("--------Do you wish to Contribute for our requests:Yes/No-------");
		String str = scn.next();
		if (str.equals("Yes")) {
			System.out.println("Enter Your email_id:");
			String emailId = scn.next();
			System.out.println("Enter request type:");
			String requestType = scn.next();
			DonationRequest drr = new DonationRequest();
			drr.setRequestType(requestType);
			obj.findByRequestType(requestType);
			System.out.println("Enter the amount you want to contribute:");
			double amount = scn.nextDouble();
			System.out.println("Payment\nEnter your accountNo:");
			@SuppressWarnings("unused")
			long accountno = scn.nextLong();
			System.out.println("Payment Success");
			double requestAmount = amount;
			drr.setRequestAmount(requestAmount);
			obj.updateDonations(drr);
			ob.donorActivity(emailId, amount, requestType);
			operations();
		} else if(str.equals("No")){
			System.out.println("ThanK You");
		}
			else
			{
				System.out.println("Invalid");
			}
			try {
				Signin.welcomePage();
			} catch (ValidatorException e) {
				e.printStackTrace();
			} finally {
				ConnectionUtil.close(con, scn);
			}

		}
	}



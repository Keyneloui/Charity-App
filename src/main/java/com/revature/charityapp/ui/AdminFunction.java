package com.revature.charityapp.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.charityapp.dao.DonationDAOImpl;
import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.dao.DonationDAO;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.exception.ValidatorException;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.util.ConnectionUtil;
import com.revature.charityapp.util.DisplayUtil;

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
		System.out.println(" 1. View Donation Requests\n 2. Donor Details\n 3. Donor Activity\n 4. Manage account\n 5. Log-out");
		Scanner scn = new Scanner(System.in);

		int num1 = scn.nextInt();
		switch (num1) {
		case 1:
			System.out.println("Donation Requests");
			donationRequest(scn);
			operations();
			break;
		case 2:
			System.out.println("Donor Details");
			DisplayUtil.donorDetails();
			operations();
			break;
		case 3:
			System.out.println("Donor Activity");
			DisplayUtil.donorActivity();
			operations();
		case 4:
			System.out.println("Manage account");
			manageAccount(scn);
			operations();
		case 5:
			System.out.println("Thank You");
			try {
				Signin.welcomePage();
			} catch (ValidatorException e) {
				System.out.println(e.getMessage());
				throw new ValidatorException("Unable to process the request", e);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionUtil.close(con, scn);
			}
		}

	}

	public static void manageAccount(Scanner scn) throws DBException {
		System.out.println("Do you want to change the email id and password");
		String str=scn.next();
		if(str.equalsIgnoreCase("yes"))
		{
			System.out.println("Enter the email you want to change");
			String email=scn.next();
			Signin.mail(email);
			System.out.println("Ënter the password you want to change");
			String pwd=scn.next();
			UserDAO ud=new UserDAOImpl();
			ud.admin(email, pwd);
		}
	}

	/**
	 * ADD UPDATE Donation Request
	 * 
	 * @throws DBException
	 **/

	public static void donationRequest(Scanner scn) throws DBException

	{

		DonationDAO dao = new DonationDAOImpl();
		try {
			List<DonationRequest> list = dao.findAll();
			DisplayUtil.display(list);

			System.out.println("--------Do you wish to ADD/UPDATE/CLOSE any request:-------");
			String str = scn.next();
			if (str.equalsIgnoreCase("ADD")) {
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
				dao.addDonation(dr);
			} else if (str.equalsIgnoreCase("UPDATE")) {
				System.out.println("Enter request type:");
				String requestType = scn.next();
				System.out.println("Enter the amount you want to add:");
				double requestAmount = scn.nextDouble();
				DonationRequest drr = new DonationRequest();
				drr.setRequestType(requestType);
				drr.setRequestAmount(requestAmount);
				dao.updateDonationss(drr);
			} else if (str.equalsIgnoreCase("DELETE")) {
				System.out.println("Enter request type:");
				String requestType = scn.next();
				DonationRequest drr = new DonationRequest();
				drr.setRequestType(requestType);
				dao.deleteDonation(drr);
			} else
				System.out.println("Thank You");
		} catch (DBException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to process the request", e);
		}
		System.out.println("Do you wish to see the targeted donations\n Yes or No");
		String s = scn.next();
		if (s.equalsIgnoreCase("yes")) {
			List<DonationRequest> list = dao.findAllDonation();
			DisplayUtil.displays(list);
			
		}
	}

}

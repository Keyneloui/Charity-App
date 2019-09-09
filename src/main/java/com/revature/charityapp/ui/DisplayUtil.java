package com.revature.charityapp.ui;

import java.util.List;

import com.revature.charityapp.dao.UserDAO;
import com.revature.charityapp.dao.UserDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.model.User;

public class DisplayUtil {

	public static void display(List<DonationRequest> list) {
		StringBuilder content = new StringBuilder();
		content.append("Request Id \t Request Type \t Request Amount\t\n");
		for (DonationRequest dr : list) {
			content.append(dr.getRequestId()).append("\t");
			content.append(dr.getRequestType()).append("\t");
			content.append(dr.getRequestAmount()).append("\t");
			content.append("\n");
		}

		System.out.println(content);
	}

	/**
	 * View Donor Details
	 * 
	 * @throws DBException
	 **/
	public static void donorDetails() throws DBException {
		UserDAO iudao = new UserDAOImpl();
		try {
			List<User> list = iudao.findAll();
			displayDonor(list);
		} catch (DBException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to process the request", e);
		}

	}

	private static void displayDonor(List<User> list) {
		StringBuilder content = new StringBuilder();
		content.append(" Id\tName\tEmail\t\n");
		for (User user : list) {
			content.append(user.getId()).append("\t");
			content.append(user.getName()).append("\t");
			content.append(user.getEmail()).append("\t");
			content.append("\n");
		}
		System.out.println(content);
	}

	/**
	 * View Donor Activity
	 * 
	 * @throws DBException
	 **/
	public static void donorActivity() throws DBException {
		UserDAO udao = new UserDAOImpl();
		try {
			udao.displayActivity();
		} catch (DBException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to process the request", e);

		}
	}

}

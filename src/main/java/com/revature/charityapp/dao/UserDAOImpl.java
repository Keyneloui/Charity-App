package com.revature.charityapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.model.User;
import com.revature.charityapp.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	/**
	 * method for donor login
	 * 
	 * @throws DBException
	 **/
	public User donorLogin(String email, String password) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select donor_id,name,email_id from donor where email_id = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("donor_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email_id"));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DBException("Unable to login", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

		return user;
	}
	public User donor(int id) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select donor_id,name,email_id from donor where donor_id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("donor_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email_id"));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DBException("Unable to login", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

		return user;
	}


	/**
	 * method for donor register
	 * 
	 * @throws DBException
	 **/

	public void register(User user) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		String sql = "insert into donor(donor_id,name,email_id,password) values (?,?,?,?)";

		try {
			con = ConnectionUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, user.getId());
			pst.setString(2, user.getName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPassword());

			int rows = pst.executeUpdate();
			// System.out.println("No of rows inserted:" + rows);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DBException("Donor Id,Name already exists\nRegister with a new Donor Id and Name", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	/**
	 * method for admin login
	 * 
	 * @throws DBException
	 **/

	public User adminLogin(String emailIds, String passwords) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,email from admin where email = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, emailIds);
			pst.setString(2, passwords);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				System.out.println("Login Success");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to login", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return user;
	}

	/**
	 * method to view the donor list
	 * 
	 * @throws DBException
	 **/
	public List<User> findAll() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		List<User> list = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select donor_id,name,email_id from donor";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<User>();
			while (rs.next()) {

				user = toRow(rs);
				list.add(user);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to list Donor", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return list;

	}

	private User toRow(ResultSet rs) throws DBException {
		User user = null;

		try {
			Integer donorId = rs.getInt("donor_id");
			String name = rs.getString("name");
			String emailId = rs.getString("email_id");
			user = new User();
			user.setId(donorId);
			user.setName(name);
			user.setEmail(emailId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to display", e);
		}
		return user;
	}

	/**
	 * method to add donor activity
	 * 
	 * @throws DBException
	 **/

	public void donorActivity(int donorId, double amount, String requestType, Date date) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into activity(donor_id,amount,request_type,date) values ( ?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, donorId);
			pst.setDouble(2, amount);
			pst.setString(3, requestType);
			pst.setDate(4, date);
			pst.executeUpdate();
			// System.out.println("No of rows inserted:" + rows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to display the donor activity", e);

		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	/**
	 * method to view donor activity
	 * 
	 * @throws DBException
	 **/
	public void displayActivity() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql1 = "select donor_id,amount,request_type,date from activity ";
			pst = con.prepareStatement(sql1);
			rs = pst.executeQuery();
			while (rs.next()) {

				int donorId = rs.getInt("donor_id");
				Double amount = rs.getDouble("amount");
				String requestType = rs.getString("request_type");
				Date date = rs.getDate("date");
				StringBuilder content = new StringBuilder();
				content.append("Donor Id\tAmount\tRequest Type\t\n");
				content.append(rs.getInt("donor_id")).append("\t\t");
				content.append(rs.getString("amount")).append("\t");
				content.append(rs.getString("request_type")).append("\t");
				content.append("\n");
				System.out.println(content);
				// System.out.println("Donor Id-" + donorId + ",Contributed Amount-" + amount +
				// ",For the request type-"
				// + requestType+"on"+date);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to process your request", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

	}

	private static void displayDonor(List<User> list) {
		StringBuilder content = new StringBuilder();
		content.append("Donor Id\tName\tEmail\t\n");
		for (User user : list) {
			content.append(user.getId()).append("\t");
			content.append(user.getName()).append("\t");
			content.append(user.getEmail()).append("\t");
			content.append("\n");
		}
		System.out.println(content);
	}
	public void admin(String email,String pwd) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update admin set email= ? where password=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pwd);
			pst.executeUpdate();
			// System.out.println("No of rows updated:" + rows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("unable to update request", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

}

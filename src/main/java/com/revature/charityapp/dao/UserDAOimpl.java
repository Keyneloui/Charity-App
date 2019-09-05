package com.revature.charityapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.User;
import com.revature.charityapp.util.ConnectionUtil;

public class UserDAOimpl implements UserDAO {
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
			String sql = "select donor_id,name,email_id,role from donor where email_id = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("donor_id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email_id"));
				user.setRole(rs.getString("role"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		String sql = "insert into donor(donor_id,name,email_id,password,role) values (?,?, ?,?,?)";

		try {
			con = ConnectionUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, user.getId());
			pst.setString(2, user.getName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getRole());

			pst.executeUpdate();
			// System.out.println("No of rows inserted:" + rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert user", e);
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
			String sql = "select admin_id,name,email_id,role from admin where email_id = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, emailIds);
			pst.setString(2, passwords);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				System.out.println("Login Success");
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
			String sql = "select donor_id,name,email_id,password,role from donor";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<User>();
			while (rs.next()) {

				user = toRow(rs);
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
			String password = rs.getString("password");
			String role = rs.getString("role");
			user = new User();
			user.setId(donorId);
			user.setName(name);
			user.setEmail(emailId);
			user.setPassword(password);
			user.setRole(role);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to display", e);
		}
		return user;
	}

	/**
	 * method to add donor activity
	 * 
	 * @throws DBException
	 **/

	public void donorActivity(String emailId, double amount, String requestType) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into activity(email_id,amount,request_type) values ( ?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, emailId);
			pst.setDouble(2, amount);
			pst.setString(3, requestType);
			pst.executeUpdate();
			// System.out.println("No of rows inserted:" + rows);
		} catch (SQLException e) {
			e.printStackTrace();
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
			String sql1 = "select email_id,amount,request_type from activity ";
			pst = con.prepareStatement(sql1);
			rs = pst.executeQuery();
			while (rs.next()) {

				String emailId = rs.getString("email_id");
				Double amount = rs.getDouble("amount");
				String requestType = rs.getString("request_type");
				System.out.println("Donor Email-" + emailId + ",Contributed Amount-" + amount + ",For the request type-"
						+ requestType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to process your request", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

	}

}

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

public class UserDAO {
	public static User donorLogin(String email, String password) throws DBException {
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to login", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

		return user;
	}

	public static void register(User user) throws DBException {

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

			int rows = pst.executeUpdate();
			System.out.println("No of rows inserted:" + rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to insert user", e);
		}

	}

	public static User adminLogin(String email_ids, String passwords) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select admin_id,name,email_id,role from admin where email_id = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email_ids);
			pst.setString(2, passwords);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				System.out.println("Login Success");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to login", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		return user;
	}

	public static List<User> findAll() throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select donor_id,name,email_id,password,role from donor";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<User> list = new ArrayList<User>();
		while (rs.next()) {

			User user = toRow(rs);
			list.add(user);
		}
		return list;
	}

	private static User toRow(ResultSet rs) throws SQLException {
		Integer donor_id = rs.getInt("donor_id");
		String name = rs.getString("name");
		String email_id = rs.getString("email_id");
		String password = rs.getString("password");
		String role = rs.getString("role");
		User user = new User();
		user.setId(donor_id);
		user.setName(name);
		user.setEmail(email_id);
		user.setPassword(password);
		user.setRole(role);
		return user;
	}

	public static void donorActivity(String email_id, double amount, String request_type) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into activity(email_id,amount,request_type) values ( ?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, email_id);
		pst.setDouble(2, amount);
		pst.setString(3, request_type);
		int rows = pst.executeUpdate();
		System.out.println("No of rows inserted:" + rows);
	}

	public static void displayActivity() throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql1 = "select email_id,amount,request_type from activity ";
		PreparedStatement pst1 = con.prepareStatement(sql1);
		ResultSet rs = pst1.executeQuery();
		while (rs.next()) {

			String email_id = rs.getString("email_id");
			Double amount = rs.getDouble("amount");
			String request_type = rs.getString("request_type");
			System.out.println("Donor Email-" + email_id + ",Contributed Amount-" + amount + ",For the request type-"
					+ request_type);
		}

	}

}

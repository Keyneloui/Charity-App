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
import com.revature.charityapp.util.ConnectionUtil;

public class DonationDAOImpl implements DonationDAO {
	/**
	 * List to view all the donation request
	 * 
	 * @throws DBException
	 **/
	public List<DonationRequest> findAll() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		List<DonationRequest> list = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,request_type,request_amount,request_date from donation_request";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<DonationRequest>();
			while (rs.next()) {

				DonationRequest dr = toRow(rs);
				list.add(dr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to display the list", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return list;
	}

	private DonationRequest toRow(ResultSet rs) throws DBException {
		DonationRequest dr = new DonationRequest();
		try {
			Integer requestId = rs.getInt("id");
			String requestType = rs.getString("request_type");
			double requestAmount = rs.getDouble("request_amount");
			Date date = rs.getDate("request_date");

			dr.setRequestId(requestId);
			dr.setRequestType(requestType);
			dr.setRequestAmount(requestAmount);
			dr.setDate(date);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to display the list", e);
		}

		return dr;
	}

	/**
	 * Method to add the donations
	 * 
	 * @throws DBException
	 **/

	public void addDonations(DonationRequest dr) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into donation_request(id,request_type,request_amount,request_date) values ( ?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, dr.getRequestId());
			pst.setString(2, dr.getRequestType());
			pst.setDouble(3, dr.getRequestAmount());
			pst.setDate(4, dr.getDate());
			int rows = pst.executeUpdate();
			System.out.println("No of rows inserted:" + rows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Unable to add request", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}

	/**
	 * Method to update the donations
	 * 
	 * @throws DBException
	 **/

	public void updateDonations(DonationRequest drr) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update donation_request set request_amount= request_amount - ? where request_type=?";
			pst = con.prepareStatement(sql);
			pst.setString(2, drr.getRequestType());
			pst.setDouble(1, drr.getRequestAmount());
			pst.executeUpdate();
			// System.out.println("No of rows updated:" + rows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("unable to update request", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

	public void updateDonationss(DonationRequest drr) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "update donation_request set request_amount= request_amount + ? where request_type=?";
			pst = con.prepareStatement(sql);
			pst.setString(2, drr.getRequestType());
			pst.setDouble(1, drr.getRequestAmount());
			pst.executeUpdate();
			// System.out.println("No of rows updated:" + rows);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("unable to update request", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
	}

	/**
	 * Method to find the donation request
	 * 
	 * @throws DBException
	 **/

	public DonationRequest findByRequestType(String requestType) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		DonationRequest dr = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,request_type,request_amount,request_date from donation_request where request_type= ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, requestType);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				dr = toRow1(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DBException("Invalid Request", e);

		} finally {
			ConnectionUtil.close(con, pst);
		}
		return dr;
	}

	private DonationRequest toRow1(ResultSet rs) throws DBException {
		DonationRequest dr = new DonationRequest();
		try {
			Integer requestId = rs.getInt("id");
			String requestType = rs.getString("request_type");
			Double requestAmount = rs.getDouble("request_amount");

			dr.setRequestId(requestId);
			dr.setRequestType(requestType);
			dr.setRequestAmount(requestAmount);
		} catch (SQLException e) {
			throw new DBException("unable to implement", e);
		}
		return dr;

	}

	public DonationRequest request(String requestType) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DonationRequest dr = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select * from donation_request where request_type=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, requestType);
			// pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				dr = new DonationRequest();
				dr.setRequestType(rs.getString("request_type"));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DBException("Invlid req", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}

		return dr;
	}

	public void deleteDonation(DonationRequest drr) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "delete from donation_request where request_type=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, drr.getRequestType());
			// pst.setDouble(1, drr.getRequestAmount());
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

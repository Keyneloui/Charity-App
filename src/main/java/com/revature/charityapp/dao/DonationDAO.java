package com.revature.charityapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.charityapp.model.DonationRequest;
import com.revature.charityapp.util.ConnectionUtil;

public class DonationDAO {
	static Connection con = ConnectionUtil.getConnection();

	public static List<DonationRequest> findAll() throws SQLException {

		String sql = "select request_id,request_type,request_amount,active from donation_request";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<DonationRequest> list = new ArrayList<DonationRequest>();
		while (rs.next()) {

			DonationRequest dr = toRow(rs);
			list.add(dr);
		}
		return list;
	}

	private static DonationRequest toRow(ResultSet rs) throws SQLException {
		Integer request_id = rs.getInt("request_id");
		String request_type = rs.getString("request_type");
		double request_amount = rs.getDouble("request_amount");
		Boolean active = rs.getBoolean("active");
		DonationRequest dr = new DonationRequest();
		dr.setRequestId(request_id);
		dr.setRequestType(request_type);
		dr.setRequestAmount(request_amount);
		dr.setActive(active);
		return dr;
	}

	public static void addDonations(DonationRequest dr) throws SQLException {
		String sql = "insert into donation_request(request_id,request_type,request_amount) values ( ?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, dr.getRequestId());
		pst.setString(2, dr.getRequestType());
		pst.setDouble(3, dr.getRequestAmount());
		int rows = pst.executeUpdate();
		System.out.println("No of rows inserted:" + rows);

	}

	public static void updateDonations(DonationRequest drr) throws SQLException {
		String sql = "update donation_request set request_amount= request_amount - ? where request_type=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(2, drr.getRequestType());
		pst.setDouble(1, drr.getRequestAmount());
		int rows = pst.executeUpdate();
		System.out.println("No of rows updated:" + rows);
	}

	public static DonationRequest findByRequestType(String request_type) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select request_id,request_type,request_amount from donation_request where request_type= ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, request_type);
		ResultSet rs = pst.executeQuery();
		DonationRequest dr = null;

		if (rs.next()) {
			dr = toRow1(rs);
		}
		return dr;
	}

	private static DonationRequest toRow1(ResultSet rs) throws SQLException {
		Integer request_id = rs.getInt("request_id");
		String request_type = rs.getString("request_type");
		Double request_amount = rs.getDouble("request_amount");
		DonationRequest dr = new DonationRequest();
		dr.setRequestId(request_id);
		dr.setRequestType(request_type);
		dr.setRequestAmount(request_amount);
		return dr;
	}

}

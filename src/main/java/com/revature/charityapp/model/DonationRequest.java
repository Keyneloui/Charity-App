package com.revature.charityapp.model;

import java.sql.Date;

public class DonationRequest {
	private int id;
	private String requestType;
	private Double requestAmount;
	private Date date;
	

	public int getRequestId() {
		return id;
	}

	public void setRequestId(int requestId) {
		this.id = requestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Double getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(Double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "DonationRequest :Request Id=" + id + ", Request Type=" + requestType + ", Request Amount="
				+ requestAmount + "Date="+date+".";
	}

}

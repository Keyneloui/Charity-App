package com.revature.charityapp.model;

public class DonationRequest {
	private int requestId;
	private String requestType;
	private Double requestAmount;
	private Boolean active;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DonationRequest :requestId=" + requestId + ", requestType=" + requestType + ", requestAmount="
				+ requestAmount + ", active=" + active + ".";
	}

}

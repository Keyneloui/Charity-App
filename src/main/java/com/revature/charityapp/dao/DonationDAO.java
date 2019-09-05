package com.revature.charityapp.dao;

import java.util.List;

import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.DonationRequest;

public interface DonationDAO {
	List<DonationRequest> findAll() throws DBException;

	void addDonations(DonationRequest dr) throws DBException;

	void updateDonations(DonationRequest drr) throws DBException;

	DonationRequest findByRequestType(String requestType) throws DBException;

}

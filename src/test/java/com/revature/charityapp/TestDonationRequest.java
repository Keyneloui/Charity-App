package com.revature.charityapp;

import java.util.List;

import org.junit.Test;

import com.revature.charityapp.dao.DonationDAO;
import com.revature.charityapp.dao.DonationDAOImpl;
import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.DonationRequest;

public class TestDonationRequest {

	@Test
	public void DonationRequesttest() throws DBException {
		
			DonationRequest request = new DonationRequest();

			request.setRequestType("FOOD");
			
			request.setRequestAmount(500.40);
		
			request.setRequestId(1);

			DonationDAO dao=new DonationDAOImpl();
			List<DonationRequest> list = dao.findAll();

	

		
	}

}

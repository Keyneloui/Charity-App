package com.revature.charityapp;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import com.revature.charityapp.util.ConnectionUtil;

public class ConnectionUtilTest {
	@Test
	public void connectionTest() {
		Connection con = ConnectionUtil.getConnection();
		assertNotNull(con);
	}

}

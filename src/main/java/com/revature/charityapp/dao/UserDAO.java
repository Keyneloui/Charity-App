package com.revature.charityapp.dao;

import java.sql.Date;
import java.util.List;

import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.User;

public interface UserDAO {

	void register(User user) throws DBException;

	User adminLogin(String emailIds, String passwords) throws DBException;

	User donorLogin(String emailId, String password) throws DBException;
	
	User donor(int id) throws DBException;

	List<User> findAll() throws DBException;

	void donorActivity(int donorId, double amount, String requestType, Date date) throws DBException;

	void displayActivity() throws DBException;

}
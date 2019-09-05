package com.revature.charityapp.dao;

import java.util.List;

import com.revature.charityapp.exception.DBException;
import com.revature.charityapp.model.User;

public interface UserDAO {

	void register(User user) throws DBException;

	User adminLogin(String emailIds, String passwords) throws DBException;

	User donorLogin(String emailId, String password) throws DBException;

	List<User> findAll() throws DBException;

	void donorActivity(String emailId, double amount, String requestType) throws DBException;

	void displayActivity() throws DBException;

}
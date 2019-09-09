package com.revature.charityapp.model;

public class User {
	private int donorId;
	private String name;
	private String email;
	private String password;
	

	@Override
	public String toString() {
		return "Donor :Id=" + donorId + ", Name=" + name + ", Email=" + email + ", Password=" + password + ".";
	}

	public int getId() {
		return donorId;
	}

	public void setId(int id) {
		this.donorId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}

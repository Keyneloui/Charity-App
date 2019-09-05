package com.revature.charityapp.model;

public class User {
	private int donorId;
	private String name;
	private String email;
	private String password;
	private String role;

	@Override
	public String toString() {
		return "Donor :id=" + donorId + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ".";
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

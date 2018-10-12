package com.example.taskapp.dto;

public class User extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String email;
	String userRole;
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
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
}

package com.revature.p0.models;

public class AppUser {

	private int id;
	private String username;
	private String password;
	private String email;

	public AppUser() {
		super();
	}

	public AppUser(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public AppUser(int id, String username, String password, String email) {
		this(username, password, email);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}

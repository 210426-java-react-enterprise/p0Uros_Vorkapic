package com.revature.p0.models;

public class UserInfo {

	private int id;
	private String fName;
	private String lName;
	private String dob; // Date of Birth, YYYY-MM-DD
	private String street;
	private String city;
	private String state;
	private int postalCode;

	public UserInfo(int id, String fName, String lName, String dob, String street, String city, String state, int postalCode) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public boolean compareWith(UserInfo otherProfile) {
		return this.equals(otherProfile);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"id=" + id +
				", fName='" + fName + '\'' +
				", lName='" + lName + '\'' +
				", dob='" + dob + '\'' +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", postalCode=" + postalCode +
				'}';
	}
}

package com.revature.p0.models;

public class UserProfile {

	private int id;
	private String fName;
	private String lName;
	private String dob; // Date of Birth, YYYY-MM-DD
	private String street;
	private String city;
	private String state;
	private String postalCode;

	public UserProfile() {
		super();
	}

	public UserProfile(int id, String fName, String lName, String dob, String street, String city, String state, String postalCode) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public boolean isEmpty() {
		if (this.fName == null || this.fName.trim().isEmpty())
			return true;
		if (this.lName == null || this.lName.trim().isEmpty())
			return true;
		if (this.dob == null || this.dob.trim().isEmpty())
			return true;
		if (this.street == null || this.street.trim().isEmpty())
			return true;
		if (this.city == null || this.city.trim().isEmpty())
			return true;
		if (this.state == null || this.state.trim().isEmpty())
			return true;
		if (this.postalCode == null || this.postalCode.trim().isEmpty())
			return true;
		return false;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
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

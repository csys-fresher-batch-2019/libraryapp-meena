package com.books.model;

public class UserDetails {
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	private int userId;
	private String userName;
	private String address;
	private long phno;
	private String email;
	private String gender;
	private int active=1;
	private String password;
	
		public UserDetails(String userName, String address, long phno, String email, String password, String gender) {
		
		this.userName = userName;
		this.address = address;
		this.phno = phno;
		this.email=email;
		this.password=password;
		this.gender=gender;
	}
	
	public UserDetails(int userId, String userName, String address, long phno, String email, String gender) {
			
			this.userId = userId;
			this.userName = userName;
			this.address = address;
			this.phno = phno;
			this.email = email;
			this.gender = gender;
		}
	public UserDetails() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", address=" + address + ", phno=" + phno
				+ ", email=" + email + ", gender=" + gender + "]";
	}	
}
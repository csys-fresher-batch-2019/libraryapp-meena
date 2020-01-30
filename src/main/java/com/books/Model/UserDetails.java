package com.books.Model;

public class UserDetails {
	public int userId;
	public String userName;
	public String address;
	public long phno;
	
		public UserDetails(String userName, String address, long phno) {
		
		this.userName = userName;
		this.address = address;
		this.phno = phno;
	}
	public UserDetails(int userId, String userName, String address, long phno) {
			this.userId=userId;
			this.userName=userName;
			this.address=address;
			this.phno=phno;
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
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", address=" + address + ", phno=" + phno+ "]";
	}
	
}

	

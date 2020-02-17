package com.books.model;

import java.sql.Date;


public class User {
public int bookId;
public int userId;
//public String bookName;
public Date issuedDate;
public Date dueDate;
public Date returnedDate;
public int fineAmount;
public String status;
public User(int bookId, Date issuedDate, Date dueDate, Date returnedDate, int fineAmount, String status2) {
	this.bookId=bookId;
	
	this.issuedDate=issuedDate;
	this.dueDate=dueDate;
	this.returnedDate=returnedDate;
	this.fineAmount=fineAmount;
	this.status=status2;
	
}
@Override
public String toString() {
	return "User [bookId=" + bookId + ", issuedDate=" + issuedDate + ", dueDate=" + dueDate + ", returnedDate="
			+ returnedDate + ", fineAmount=" + fineAmount + ", status=" + status + "]";
}




}


	

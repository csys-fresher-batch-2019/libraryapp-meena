package com.books.model;

import java.sql.Date;


public class User {
public int bookId;
public int userId;
public String bookName;
public Date issuedDate;
public Date dueDate;
public Date returnedDate;
public int fineAmount;

public User(int bookId, Date issuedDate, Date dueDate, Date returnedDate, int fineAmount) {
	this.bookId=bookId;
	this.issuedDate=issuedDate;
	this.dueDate=dueDate;
	this.returnedDate=returnedDate;
	this.fineAmount=fineAmount;
}
@Override
public String toString() {
	return "User [bookId=" + bookId + ", bookName=" + bookName + ", issuedDate=" + issuedDate + ", dueDate=" + dueDate
			+ ", returnedDate=" + returnedDate + ", fineAmount=" + fineAmount + "]";
}


}


	

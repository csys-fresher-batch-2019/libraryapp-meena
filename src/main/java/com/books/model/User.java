package com.books.model;

import java.sql.Date;

public class User {
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public int getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private int bookId;
	private int userId;
	private Date issuedDate;
	private Date dueDate;
	private Date returnedDate;
	private int fineAmount;
	private String status;

	public User(int bookId, Date issuedDate, Date dueDate, Date returnedDate, int fineAmount, String status2) {
		this.bookId = bookId;
		this.issuedDate = issuedDate;
		this.dueDate = dueDate;
		this.returnedDate = returnedDate;
		this.fineAmount = fineAmount;
		this.status = status2;
	}

	@Override
	public String toString() {
		return "User [bookId=" + bookId + ", issuedDate=" + issuedDate + ", dueDate=" + dueDate + ", returnedDate="
				+ returnedDate + ", fineAmount=" + fineAmount + ", status=" + status + "]";
	}
}
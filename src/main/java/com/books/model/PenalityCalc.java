package com.books.model;

import java.sql.Date;

public class PenalityCalc {
	private int itemId;
	private int bookId;
	private int userId;
	private Date issuedDate;
	private Date dueDate;
	private Date returnedDate;
	private int fineAmount;
	private String status;
	public int getItemId() {
		return itemId;
	}
	public PenalityCalc(int itemId, int bookId, int userId, Date issuedDate, Date dueDate, Date returnedDate, int fineAmount, String status) {
		super();
		this.itemId = itemId;
		this.bookId = bookId;
		this.userId = userId;
		this.issuedDate = issuedDate;
		this.dueDate = dueDate;
		this.returnedDate = returnedDate;
		this.fineAmount = fineAmount;
		this.status = status;
	}
	public PenalityCalc() {
		// TODO Auto-generated constructor stub
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
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
	@Override
	public String toString() {
		return "PenalityCalc [itemId=" + itemId + ", bookId=" + bookId + ", userId=" + userId + ", issuedDate="
				+ issuedDate + ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + ", fineAmount=" + fineAmount
				+ ", status=" + status + "]";
	}
}
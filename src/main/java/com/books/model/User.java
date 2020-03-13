package com.books.model;

import java.time.LocalDate;

public class User {

	private int bookId;
	private int userId;
	private LocalDate issuedDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	private int fineAmount;
	private String status;

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

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(LocalDate returnedDate) {
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

	public User(int bookId, LocalDate issuedDate, LocalDate dueDate, LocalDate returnedDate, int fineAmount,
			String status2) {
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
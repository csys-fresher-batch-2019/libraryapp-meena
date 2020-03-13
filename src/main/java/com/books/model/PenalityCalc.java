package com.books.model;

import java.time.LocalDate;

public class PenalityCalc {
	private int itemId;
	private int bookId;
	private int userId;
	private LocalDate issuedDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	private int fineAmount;
	private String status;

	public PenalityCalc(int itemId, int bookId, int userId, LocalDate issuedDate2, LocalDate dueDate2,
			LocalDate returnedDate2, int fineAmount, String status) {
		super();
		this.itemId = itemId;
		this.bookId = bookId;
		this.userId = userId;
		this.issuedDate = issuedDate2;
		this.dueDate = dueDate2;
		this.returnedDate = returnedDate2;
		this.fineAmount = fineAmount;
		this.status = status;
	}

	public int getItemId() {
		return itemId;
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

	@Override
	public String toString() {
		return "PenalityCalc [itemId=" + itemId + ", bookId=" + bookId + ", userId=" + userId + ", issuedDate="
				+ issuedDate + ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + ", fineAmount=" + fineAmount
				+ ", status=" + status + "]";
	}
}
package com.books.model;

public class BookStockDetails {

	private int bookId;
	private int isbnNo;
	private int active = 1;
	private int totalBooks;
	private int remaining;
	private int issued;

	public BookStockDetails(int bookId, int isbnNo, int active) {
		this.bookId = bookId;
		this.isbnNo = isbnNo;
		this.active = active;
	}

	public int getTotalBooks() {
		return totalBooks;
	}

	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public int getIssued() {
		return issued;
	}

	public void setIssued(int issued) {
		this.issued = issued;
	}

	public BookStockDetails(int isbnNo2, int totalBooks) {
		this.setIsbnNo(isbnNo2);
		this.setRemaining(totalBooks);
	}
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(int isbnNo) {
		this.isbnNo = isbnNo;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "BookStockDetails [bookId=" + bookId + ", isbnNo=" + isbnNo + ", active=" + active + "]";
	}
	public BookStockDetails() {
	}
}
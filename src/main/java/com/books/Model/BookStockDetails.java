package com.books.model;

public class BookStockDetails {
	//public int isbnNo;
	public int bookId;
	public int isbnNo;
	public int active=1;
	public int totalBooks;
	public int remaining;
	public int issued;
	
	
	
	
	public BookStockDetails(int bookId, int isbnNo, int active) {
		
		this.bookId = bookId;
		this.isbnNo = isbnNo;
		this.active = active;
	}
	public BookStockDetails() {
		// TODO Auto-generated constructor stub
	}
	public BookStockDetails(int isbnNo2, int totalBooks) {
		this.isbnNo=isbnNo2;
		this.remaining=totalBooks;
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
	
	
	
	
	/*public BookStockDetails(int bookStockId, int bookId, int quantity,int issuedBooks) {
		//super();
		this.bookStockId = bookStockId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.issuedBooks=issuedBooks;
	}*/

		
	}

	
	

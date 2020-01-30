package com.books.Model;

public class BookStockDetails {
	public int bookStockId;
	public int bookId;
	public int quantity;
	public int issuedBooks=0;
	
	
	/*public BookStockDetails(int bookStockId, int bookId, int quantity,int issuedBooks) {
		//super();
		this.bookStockId = bookStockId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.issuedBooks=issuedBooks;
	}*/

	public BookStockDetails(int bookStockId, int bookId, int quantity, int issuedBooks) {
		
		this.bookStockId = bookStockId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.issuedBooks = issuedBooks;
	}


	public BookStockDetails() {
		// TODO Auto-generated constructor stub
	}


	public int getBookStockId() {
		return bookStockId;
	}


	public void setBookStockId(int bookStockId) {
		this.bookStockId = bookStockId;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getIssuedBooks() {
		return issuedBooks;
	}


	public void setIssuedBooks(int issuedBooks) {
		this.issuedBooks = issuedBooks;
	}


	@Override
	public String toString() {
		return "BookStockDetails [bookStockId=" + bookStockId + ", bookId=" + bookId + ", quantity=" + quantity
				+ ", issuedBooks=" + issuedBooks + "]";
	}
	
	}

	
	

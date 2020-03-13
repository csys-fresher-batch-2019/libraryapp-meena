package com.books.dto;

public class BookCount {
	private int bookId;
	private String bookName;
	private int count;
	private int isbnNo;

	public BookCount(int isbnNo, int count) {
		super();
		this.isbnNo = isbnNo;
		this.count = count;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(int isbnNo) {
		this.isbnNo = isbnNo;
	}

	@Override
	public String toString() {
		return "Additional [isbnNo=" + isbnNo + ", count=" + count + "]";
	}
}
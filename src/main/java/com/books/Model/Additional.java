package com.books.Model;

public class Additional {
	public int bookId;
	public String bookName;
	public int count;
	
	public Additional(int bookId, String bookName, int count) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Additional [bookName=" + bookName + ", count=" + count + "]";
	}

}

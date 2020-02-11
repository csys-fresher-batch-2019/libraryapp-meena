package com.books.model;

public class Additional {
	public int bookId;
	public String bookName;
	public int count;
	public int isbnNo;
	
	public Additional(int isbnNo, int count) {
		super();
		this.isbnNo = isbnNo;
		//this.bookName = bookName;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Additional [isbnNo=" +isbnNo + ", count=" + count + "]";
	}

}

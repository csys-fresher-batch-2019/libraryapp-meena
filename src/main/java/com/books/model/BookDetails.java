package com.books.model;

public class BookDetails {

	private int isbnNo;
	private String bookName;
	private String authorName;
	private String publisher;
	private int version;
	private String categories;
	private String languages;
	private int totalBook;
	private int active;

	public int getTotalBook() {
		return totalBook;
	}

	public void setTotalBook(int totalBook) {
		this.totalBook = totalBook;
	}

	public int getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(int isbnNo) {
		this.isbnNo = isbnNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public BookDetails() {
		super();
	}

	@Override
	public String toString() {
		return "BookDetails [isbnNo=" + isbnNo + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", publisher=" + publisher + ", version=" + version + ", categories=" + categories + ", languages="
				+ languages + ", totalBook=" + totalBook + ", active=" + active + "]";
	}

	public BookDetails(int isbnNo, String bookName, String authorName, String publisher, int version, String categories,
			String languages, int totalBook, int active) {
		super();
		this.isbnNo = isbnNo;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publisher = publisher;
		this.version = version;
		this.categories = categories;
		this.languages = languages;
		this.totalBook = totalBook;
		this.active = active;
	}

}
package com.books.Model;

public class BookDetails 
{
	private int bookId;
	public String bookName;
	public String authorName;
	public String publisher;
	public int version;
	public String categories;
	public String languages;
	public int active=1;
	
	
	
	public int getBookId() {

		if(bookId<0)
		{
			throw new IllegalArgumentException("Invalid Id");
		}
		return bookId;
	}



	public BookDetails() {
		super();
		
		
	}



	public BookDetails(int bookId)
	{
		this.bookId=bookId;
	}



	public BookDetails(int bookId, String bookName, String authorName, String pub, int ver, String category,
			String language, int active) {
		this.bookId=bookId;
		this.bookName=bookName;
		this.authorName=authorName;
		this.publisher=pub;
		this.version=ver;
		this.categories=category;
		this.languages=language;
		this.active=active;
		
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



	@Override
	public String toString() {
		return "BookDetails [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", publisher=" + publisher + ", version=" + version + ", categories=" + categories + ", languages="
				+ languages + ", active=" + active + "]";
	}

	
	/*public BookDetails(int bookId, String bookName, String authorName, String publisher, int version, String categories,
			String languages,int active) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publisher = publisher;
		this.version = version;
		this.categories = categories;
		this.languages = languages;
		this.active = active;
	}
	public BookDetails(String bookName, String authorName, String publisher, int version, String categories,
			String languages) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.publisher = publisher;
		this.version = version;
		this.categories = categories;
		this.languages = languages;
	}
	public BookDetails(int bookId) {
		this.bookId=bookId;
	}	*/
}






















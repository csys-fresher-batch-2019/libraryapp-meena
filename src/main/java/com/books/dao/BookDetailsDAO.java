package com.books.dao;

import java.util.List;

import com.books.model.BookDetails;

public interface BookDetailsDAO
{
	public void insertBookDetails(BookDetailsDAO bookDetail)throws Exception;
	
	public List<BookDetails> displayBooks() throws Exception;
	
	public void deleteBookDetails(int bookId)throws Exception;
	
	public int totalBooks()throws Exception;
}

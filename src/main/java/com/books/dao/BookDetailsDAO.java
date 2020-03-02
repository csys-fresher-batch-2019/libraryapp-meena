package com.books.dao;

import java.util.List;

import com.books.model.BookDetails;

public interface BookDetailsDAO {
	public int insertBookDetails(BookDetails bookDetail) throws Exception;

	public List<BookDetails> displayBooks() throws Exception;

	public int deleteBookDetails(int bookId) throws Exception;

	public int totalBooks() throws Exception;

	public int checkAdmin(String admin, String password) throws Exception;

	public void updateTotalStock() throws Exception;
}

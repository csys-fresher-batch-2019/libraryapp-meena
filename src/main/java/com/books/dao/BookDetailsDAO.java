package com.books.dao;

import java.util.List;

import com.books.model.BookDetails;

public interface BookDetailsDAO {
	public int saveBookDetails(BookDetails bookDetail) throws Exception;

	public List<BookDetails> findAllBooks() throws Exception;

	public int deleteBookDetails(int bookId) throws Exception;

	public int findTotalBooks() throws Exception;

	public int findByAdmin(String admin, String password) throws Exception;

	public int updateTotalStock() throws Exception;
}

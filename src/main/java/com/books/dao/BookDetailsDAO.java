package com.books.dao;

import java.util.List;

import com.books.exception.DbException;
import com.books.model.BookDetails;

public interface BookDetailsDAO {
	public int saveBookDetails(BookDetails bookDetail) throws DbException;

	public List<BookDetails> findAllBooks() throws DbException;

	public int deleteBookDetails(int bookId) throws DbException;

	public int findTotalBooks() throws DbException;

	public int findByAdmin(String admin, String password) throws DbException;

	public int updateTotalStock() throws DbException;
}

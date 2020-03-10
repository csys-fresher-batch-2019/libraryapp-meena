package com.books.dao;

import java.util.List;

import com.books.model.BookStockDetails;

public interface BookStockDetailsDAO {
	public int saveBookStockDetails(int isbnNo) throws Exception;

	public List<BookStockDetails> findAllBookStockDetails() throws Exception;

	public int updateStockRoom() throws Exception;

	public List<BookStockDetails> findTotalStocks() throws Exception;

	public int updateActive(int bookId) throws Exception;

	public List<BookStockDetails> findAllIndividualRemaining() throws Exception;

	public List<BookStockDetails> findAllIndividualIssued() throws Exception;

	public int deleteStock(int bookId) throws Exception;
}
package com.books.dao;

import java.util.List;

import com.books.model.BookStockDetails;

public interface BookStockDetailsDAO {
	public int insertBookStockDetails(int isbnNo) throws Exception;

	public List<BookStockDetails> displayBookStockDetails() throws Exception;

	public int updateStockRoom() throws Exception;

	public List<BookStockDetails> totalStocks() throws Exception;

	public int updateActive(int bookId) throws Exception;

	public List<BookStockDetails> individualRemaining() throws Exception;

	public List<BookStockDetails> individualIssued() throws Exception;

	public int deleteStock(int bookId) throws Exception;
}
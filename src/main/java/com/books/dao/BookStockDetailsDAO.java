package com.books.dao;

import java.util.List;

import com.books.exception.DbException;
import com.books.model.BookStockDetails;

public interface BookStockDetailsDAO {
	public int saveBookStockDetails(int isbnNo) throws DbException;

	public List<BookStockDetails> findAllBookStockDetails() throws DbException;

	public int updateStockRoom() throws DbException;

	public List<BookStockDetails> findTotalStocks() throws DbException;

	public int updateActive(int bookId) throws DbException;

	public List<BookStockDetails> findAllIndividualRemaining() throws DbException;

	public List<BookStockDetails> findAllIndividualIssued() throws DbException;

	public int deleteStock(int bookId) throws DbException;
}
package com.books.service;

import java.util.List;

import com.books.dao.BookStockDetailsDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.BookStockDetails;

public class BookStockDetailsService {
	private BookStockDetailsDAO bookStockDetailsDAO = DAOFactory.getBookStockDetailsDAO();

	public int addNewStock(int isbnNo) throws Exception {
		return bookStockDetailsDAO.saveBookStockDetails(isbnNo);
	}

	public List<BookStockDetails> displayBookStockDetails() throws Exception {
		return bookStockDetailsDAO.findAllBookStockDetails();
	}

	public int updateStockRoom() throws Exception {
		return bookStockDetailsDAO.updateStockRoom();
	}

	public List<BookStockDetails> totalStocks() throws Exception {
		return bookStockDetailsDAO.findTotalStocks();
	}

	public int updateActive(int bookId) throws Exception {
		return bookStockDetailsDAO.updateActive(bookId);
	}

	public List<BookStockDetails> individualRemaining() throws Exception {
		return bookStockDetailsDAO.findAllIndividualRemaining();
	}

	public List<BookStockDetails> individualIssued() throws Exception {
		return bookStockDetailsDAO.findAllIndividualIssued();
	}

	public int deleteStock(int bookId) throws Exception {
		return bookStockDetailsDAO.deleteStock(bookId);
	}
}

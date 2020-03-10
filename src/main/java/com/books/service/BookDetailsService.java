package com.books.service;

import java.util.List;

import com.books.dao.BookDetailsDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.BookDetails;

public class BookDetailsService {

	private BookDetailsDAO bookDetailsDAO = DAOFactory.getBookDetailsDAO();

	public int addNewBook(BookDetails bookDetail) throws Exception {
		return bookDetailsDAO.saveBookDetails(bookDetail);
	}

	public List<BookDetails> displayBooks() throws Exception {
		return bookDetailsDAO.findAllBooks();
	}

	public int deleteBookDetails(int bookId) throws Exception {
		return bookDetailsDAO.deleteBookDetails(bookId);
	}

	public int totalBooks() throws Exception {
		return bookDetailsDAO.findTotalBooks();
	}

	public int checkAdmin(String userName, String password) throws Exception {
		return bookDetailsDAO.findByAdmin(userName, password);
	}

	public void updateStockRoom() throws Exception {
		bookDetailsDAO.updateTotalStock();
	}
}

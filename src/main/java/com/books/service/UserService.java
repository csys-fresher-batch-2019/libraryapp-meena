package com.books.service;

import java.util.List;

import com.books.dao.UserDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.BookDetails;
import com.books.dto.CalcCard;
import com.books.model.User;

public class UserService {
	private UserDAO userDAO = DAOFactory.getUserDAO();

	public int checkLogin(String userName, String password) throws Exception {
		return userDAO.findByUser(userName, password);
	}

	public List<User> viewHistory(int userId) throws Exception {
		return userDAO.findAllHistory(userId);
	}

	public List<User> currentBooks(int userId) throws Exception {
		return userDAO.findAllCurrentBooks(userId);
	}

	public List<BookDetails> searchBookName(String name) throws Exception {
		return userDAO.findByBookName(name);
	}

	public List<BookDetails> searchAuthorName(String author) throws Exception {
		return userDAO.findByAuthorName(author);
	}

	public List<CalcCard> remainingCard(int userId) throws Exception {
		return userDAO.findRemainingCard(userId);
	}

	public int updatePhoneNumber(int userId, long phoneNumber) throws Exception {
		return userDAO.updatePhoneNumber(userId, phoneNumber);
	}

	public int updateAddress(int userId, String address) throws Exception {
		return userDAO.updateAddress(userId, address);
	}

	public int changePassword(int userId, String password) throws Exception {
		return userDAO.updatePassword(userId, password);
	}

	public List<User> dueDateCheck(int userId) throws Exception {
		return userDAO.findDueDate(userId);
	}

	public int checkAvailable(int userId) throws Exception {
		return userDAO.findAvailable(userId);
	}
}

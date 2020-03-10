package com.books.dao;

import java.util.List;

import com.books.model.BookDetails;
import com.books.model.CalcCard;
import com.books.model.User;

public interface UserDAO {
	public int findByUser(String userName, String password) throws Exception;

	public List<User> findAllHistory(int userId) throws Exception;

	public List<User> findAllCurrentBooks(int userId) throws Exception;

	public List<BookDetails> findByBookName(String name) throws Exception;

	public List<BookDetails> findByAuthorName(String author) throws Exception;

	public List<CalcCard> findRemainingCard(int userId) throws Exception;

	public int updatePhoneNumber(int userId, long phoneNumber) throws Exception;

	public int updateAddress(int userId, String address) throws Exception;

	public int updatePassword(int userId, String password) throws Exception;

	public List<User> findDueDate(int userId) throws Exception;

	public int findAvailable(int userId) throws Exception;
}

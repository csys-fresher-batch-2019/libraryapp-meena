package com.books.dao;

import java.sql.SQLException;
import java.util.List;

import com.books.model.BookDetails;
import com.books.dto.CalcCard;
import com.books.exception.DbException;
import com.books.model.User;

public interface UserDAO {
	public int findByUser(String userName, String password) throws DbException,SQLException;

	public List<User> findAllHistory(int userId) throws DbException;

	public List<User> findAllCurrentBooks(int userId) throws DbException;

	public List<BookDetails> findByBookName(String name) throws DbException,SQLException;

	public List<BookDetails> findByAuthorName(String author) throws DbException;

	public List<CalcCard> findRemainingCard(int userId) throws DbException;

	public int updatePhoneNumber(int userId, long phoneNumber) throws DbException;

	public int updateAddress(int userId, String address) throws DbException;

	public int updatePassword(int userId, String password) throws DbException;

	public List<User> findDueDate(int userId) throws DbException;

	public int findAvailable(int userId) throws DbException,SQLException;
}

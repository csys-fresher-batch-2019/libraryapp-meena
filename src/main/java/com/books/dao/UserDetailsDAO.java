package com.books.dao;

import java.util.List;

import com.books.exception.DbException;
import com.books.model.UserDetails;

public interface UserDetailsDAO {
	public int saveUserDetails(UserDetails userDetails) throws DbException;

	public List<UserDetails> findAllUserDetails() throws DbException;

	public int deleteUserDetails(int userId) throws DbException;
}

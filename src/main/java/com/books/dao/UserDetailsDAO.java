package com.books.dao;

import java.util.List;

import com.books.model.UserDetails;

public interface UserDetailsDAO {
	public int saveUserDetails(UserDetails userDetails) throws Exception;

	public List<UserDetails> findAllUserDetails() throws Exception;

	public int deleteUserDetails(int userId) throws Exception;
}

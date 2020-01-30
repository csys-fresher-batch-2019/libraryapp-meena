package com.books.dao;

import java.util.List;

import com.books.model.User;

public interface UserDAO {
	
	public  List<User>displayBooks(int userId) throws Exception;

}

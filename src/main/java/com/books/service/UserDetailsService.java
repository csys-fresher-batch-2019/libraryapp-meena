package com.books.service;

import java.util.List;

import com.books.dao.UserDetailsDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.UserDetails;

public class UserDetailsService {
	private UserDetailsDAO userDetailsDAO = DAOFactory.getUserDetailsDAO();

	public int insertUserDetails(UserDetails userDetails) throws Exception {
		return userDetailsDAO.saveUserDetails(userDetails);
	}

	public List<UserDetails> displayUserDetails() throws Exception {
		return userDetailsDAO.findAllUserDetails();
	}

	public int deleteUserDetails(int userId) throws Exception {
		return userDetailsDAO.deleteUserDetails(userId);
	}
}

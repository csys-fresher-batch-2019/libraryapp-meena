package com.books.dao;

import java.util.List;

import com.books.model.UserDetails;


public interface UserDetailsDAO 
{
	public void insertUserDetails(UserDetails userDetails) throws Exception;
	
	public List<UserDetails>displayUserDetails()throws Exception;
	 
	 public void deleteUserDetails(int userId)throws Exception;
}

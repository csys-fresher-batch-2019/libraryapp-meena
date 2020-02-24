package com.books.dao;

import java.util.List;

import com.books.model.UserDetails;


public interface UserDetailsDAO 
{
	public int insertUserDetails(UserDetails userDetails) throws Exception;
	public List<UserDetails>displayUserDetails()throws Exception;
	public int deleteUserDetails(int userId)throws Exception;
} 

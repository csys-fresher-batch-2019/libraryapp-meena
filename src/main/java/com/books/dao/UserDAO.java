package com.books.dao;

import java.util.List;

import com.books.model.BookDetails;
import com.books.model.CalcCard;
import com.books.model.User;

public interface UserDAO {
	public int checkLogin(String userName,String password) throws Exception;
	public List<User>viewHistory(int userId)throws Exception;
	public List<User>currentBooks(int userId)throws Exception;
	public List<BookDetails>searchBookName(String name)throws Exception;
	public List<BookDetails>searchAuthorName(String author)throws Exception;
	public List<CalcCard> remainingCard(int userId)throws Exception;
	public int updatePhoneNumber(int userId,long phoneNumber)throws Exception;
	public int updateAddress(int userId,String address)throws Exception;
	public int changePassword(int userId,String password)throws Exception;
	public List<User>dueDateCheck(int userId)throws Exception;	
	public int checkAvailable(int userId)throws Exception;
}

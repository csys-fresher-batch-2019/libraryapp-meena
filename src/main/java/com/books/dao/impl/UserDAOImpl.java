package com.books.dao.impl;

import java.sql.Connection;
import com.books.logger.Logger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.User;
import com.books.dao.UserDAO;

public class UserDAOImpl implements UserDAO
{
	 private static final Logger log=Logger.getInstance(); 

	public List<User> displayBooks(int userId) throws Exception {
		String sql="select book_id,issued_date,due_date,returned_date,fine_amount from  fine_calc where user_id=? order by issued_date desc";
		List<User>list=new ArrayList<User>();
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql);)
		{
			pst.setInt(1, userId);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				int bookId=rs.getInt("book_id");
				Date issuedDate=rs.getDate("issued_date");
				Date returnedDate=rs.getDate("returned_date");
				Date dueDate=rs.getDate("due_date");
				int fineAmount=rs.getInt("fine_amount");
				
				User u=new User(bookId,issuedDate,dueDate,returnedDate,fineAmount);
				list.add(u);
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

	public void checkLogin(String email, String password) throws Exception {
		
		String sql="select email,password from users where email=? and password=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();)
		{
			
			pst.setString(1, email);
			pst.setString(2, password);
			
			
			if(rs.next()) {
				log.getInput("Welcome");
				
				
			}
			else
			{
				log.getInput("Invalid Login");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	

}

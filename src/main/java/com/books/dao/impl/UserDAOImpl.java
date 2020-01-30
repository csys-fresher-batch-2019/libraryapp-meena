package com.books.dao.impl;

import java.sql.Connection;
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

	public List<User> displayBooks(int userId) throws Exception {
		Connection connection=null;
		PreparedStatement pst=null;
		List<User>list=new ArrayList<User>();
		try
		{
			connection=ConnectionUtil.getConnection();
			String sql="select book_id,issued_date,due_date,returned_date,fine_amount from  fine_calc where user_id=? order by issued_date desc";
			System.out.println(sql);
			pst=connection.prepareStatement(sql);
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
		finally
		{
			if(pst!=null)
			{
				pst.close();
			}
			if(connection!=null)
			{
				connection.close();
			}
		}
		return list;
	}

}

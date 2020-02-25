package com.books.dao.impl;

import java.sql.Connection;
import com.books.logger.Logger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.BookDetails;
import com.books.model.CalcCard;
import com.books.model.User;
import com.books.dao.UserDAO;

public class UserDAOImpl implements UserDAO
{
	 private static final Logger log=Logger.getInstance(); 

	public List<User> viewHistory(int userId) throws Exception {
		String sql="select book_id,issued_date,due_date,returned_date,fine_amount,status from  fine_calc where user_id=? order by issued_date desc";
		List<User>list=new ArrayList<User>();
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql);)
		{
			pst.setInt(1, userId);
			ResultSet rs=pst.executeQuery();
			int flag=0;
				while(rs.next())
				{
					flag=1;
					int bookId=rs.getInt("book_id");
					Date issuedDate=rs.getDate("issued_date");
					Date returnedDate=rs.getDate("returned_date");
					Date dueDate=rs.getDate("due_date");
					int fineAmount=rs.getInt("fine_amount");
					String status=rs.getString("status");
					User u=new User(bookId,issuedDate,dueDate,returnedDate,fineAmount,status);
					list.add(u);	
				}
			
				if(flag==0)
				{
					log.getInput("No history");
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public int checkLogin(String email, String password) throws Exception {
		int uid=0;
		String sql="select * from users where email=?and password=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, email);
			pst.setString(2, password);
			try(ResultSet rs=pst.executeQuery();)
		{
			if(rs.next()) {
			uid=rs.getInt("user_id");
				String uname=rs.getString("user_name");
				log.getInput("Welcome "+uname);
			}
			else
			{
				log.getInput("Invalid Login");
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return uid;
	}

	@Override
	public List<User> currentBooks(int userId) throws Exception {
		String sql="select book_id,issued_date,due_date,returned_date,fine_amount,status from  fine_calc where user_id=? and status='Issued' order by issued_date desc";
		List<User>list=new ArrayList<User>();
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql);)
		{
			pst.setInt(1, userId);
			ResultSet rs=pst.executeQuery();
			int flag=0;
				while(rs.next())
				{
					flag=1;
					int bookId=rs.getInt("book_id");
					Date issuedDate=rs.getDate("issued_date");
					Date returnedDate=rs.getDate("returned_date");
					Date dueDate=rs.getDate("due_date");
					int fineAmount=rs.getInt("fine_amount");
					String status=rs.getString("status");
					User u=new User(bookId,issuedDate,dueDate,returnedDate,fineAmount,status);
					list.add(u);
				}
		if(flag==0)
		{
			log.getInput("No current Books in your account");
					
			}
		}
						
catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDetails> searchBookName(String bookName) throws Exception {
		List<BookDetails>list=new ArrayList<BookDetails>();
		String sql="select *from book where lower(book_name) like ?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, "%"+ bookName +"%");
			try(ResultSet rs=pst.executeQuery();)
			{
				while(rs.next()) {
					int isbnNo=rs.getInt("isbn_no");
					String bookName1=rs.getString("book_name");
					String authorName=rs.getString("author_name");
					String pub=rs.getString("publisher");
					int ver=rs.getInt("version_no");
					String category=rs.getString("categories");
					String language=rs.getString("languages");
					int totalBooks=rs.getInt("total_stocks");
					int active=rs.getInt("active");			
					BookDetails bd = new BookDetails(isbnNo,bookName1,authorName,pub,ver,category,language,totalBooks,active);
					list.add(bd);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.getInput("No Book Found");
		}
		
		return list;
		}
	}

	@Override
	public List<BookDetails> searchAuthorName(String author) throws Exception {
		List<BookDetails>list=new ArrayList<BookDetails>();
		String sql="select *from book where lower(author_name) like ?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();)
		{
			pst.setString(1, "%"+ author +"%");
			while(rs.next()) {
				int isbnNo=rs.getInt("isbn_no");
				String bookName=rs.getString("book_name");
				String authorName=rs.getString("author_name");
				String pub=rs.getString("publisher");
				int ver=rs.getInt("version_no");
				String category=rs.getString("categories");
				String language=rs.getString("languages");
				int totalBooks=rs.getInt("total_stocks");
				int active=rs.getInt("active");
				BookDetails bd = new BookDetails(isbnNo,bookName,authorName,pub,ver,category,language,totalBooks,active);
				list.add(bd);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.getInput("No Book Found");
		}
		return list;	
	}

	
	public List<CalcCard> remainingCard(int userId) throws Exception {
		CalcCard c=new CalcCard();
		List<CalcCard>list=new ArrayList<CalcCard>();
		String sql="select distinct user_id,count2(user_id)as taken_books, (case when count2(user_id)<=(select allocated_count from allocated) then((select allocated_count from allocated)-count2(user_id) )else 0 end)as remaining from users where user_id=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setInt(1, userId);
			try(ResultSet rs=pst.executeQuery();)
{
				if(rs.next())
				{
				c.setTakenBooks(rs.getInt(2));
				c.setRemaining(rs.getInt(3));
				}
				list.add(c);
}		
		}			
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;	
	}

	@Override
	public int updatePhoneNumber(int userId, long phoneNumber) throws Exception {
		String sql="update users set ph_no=? where user_id=?";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setLong(1, phoneNumber);
			pst.setInt(2,userId);
			row=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int updateAddress(int userId, String address) throws Exception {
		String sql="update users set address=? where user_id=?";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, address);
			pst.setInt(2,userId);
			row=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int changePassword(int userId, String password) throws Exception {
		String sql="update users set password=? where user_id=?";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, password);
			pst.setInt(2,userId);
			row=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<User> dueDateCheck(int userId) throws Exception {
		String sql="select *from fine_calc where due_date-sysdate=(select popup from popup)and user_id=?";
		List<User>list=new ArrayList<User>();
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setInt(1, userId);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				while(rs.next())
				{
					
					int bookId=rs.getInt("book_id");
					
					Date issuedDate=rs.getDate("issued_date");
					Date returnedDate=rs.getDate("returned_date");
					Date dueDate=rs.getDate("due_date");
					int fineAmount=rs.getInt("fine_amount");
					String status=rs.getString("status");
					User u=new User(bookId,issuedDate,dueDate,returnedDate,fineAmount,status);
					list.add(u);
				}

			}
				else
				{
					log.getInput("No Due Books in your account");
				}

			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int checkAvailable(int userId) throws Exception {
		int status=0;
		String sql="select *from fine_calc where user_id=?";
		String sql1="select * from allocated";
		UserDAOImpl s=new UserDAOImpl();
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setInt(1, userId);
			try(ResultSet rs=pst.executeQuery();)
			{
				if(rs.next())
				{
					status=1;
					s.remainingCard(userId);
				}
				else
				{
					try(PreparedStatement pst1=con.prepareStatement(sql1);
							ResultSet rs1=pst1.executeQuery();)
					{
						if(rs1.next()) {
							
							int remaining=rs1.getInt("allocated_count");
							log.getInput("Remaining:"+remaining);
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return status;
	}

	}
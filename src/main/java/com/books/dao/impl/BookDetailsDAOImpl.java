package com.books.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.BookDetails;

import com.books.dao.BookDetailsDAO;
import com.books.logger.Logger;

public class BookDetailsDAOImpl implements BookDetailsDAO {
	 private static final Logger log=Logger.getInstance();


	public List<BookDetails> displayBooks() throws Exception {
		
		Connection connection=null;
		Statement stmt=null;
		List<BookDetails> list = new ArrayList<BookDetails>();
		
		try
		{
			connection=ConnectionUtil.getConnection();
			stmt=connection.createStatement();
			String sqlQuery="select * from book where active=1";
			log.getInput(sqlQuery);
			
			
			ResultSet rs=stmt.executeQuery(sqlQuery);
			while(rs.next()) 
			{
				int isbnNo=rs.getInt("isbn_no");
				String bookName=rs.getString("book_name");
				String authorName=rs.getString("author_name");
				String pub=rs.getString("publisher");
				int ver=rs.getInt("version_no");
				String category=rs.getString("categories");
				String language=rs.getString("languages");
				int active=rs.getInt("active");
				
				
				BookDetails bd = new BookDetails(isbnNo,bookName,authorName,pub,ver,category,language,active);
				list.add(bd);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
		
		//return null;
	

	public void insertBookDetails(BookDetails bookDetail) throws Exception 
	{
		String sql=("insert into book(isbn_no,book_name,author_name,publisher,version_no,categories,languages)values(?,?,?,?,?,?,?)");
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);)
		{
			
			pst.setInt(1, bookDetail.isbnNo);
			pst.setString(2,bookDetail.getBookName());
			pst.setString(3,bookDetail.authorName);
			pst.setString(4,bookDetail.publisher);
			pst.setInt(5, bookDetail.version);
			pst.setString(6, bookDetail.categories);
			pst.setString(7,bookDetail.languages);
			int row=pst.executeUpdate();
			log.getInput(row+" row inserted");
			log.getInput(sql);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//return null;
	}

	public void deleteBookDetails(int isbnNo) throws Exception 
	{
		String sql=("update book set active=0 where isbn_no=?");
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery(sql);)
		{	
			pst.setInt(1,isbnNo);
			
			int row=pst.executeUpdate();
			log.getInput(row+" row deleted");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
	}

	public void insertBookDetails(BookDetailsDAO bookDetail) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public int totalBooks() throws Exception {
	
		int count = 0;
		String sql="select count(book_id)as total_books from stock_room";
		try(Connection con=ConnectionUtil.getConnection();
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);)
		{	
			if(rs.next())
			{
				 count=rs.getInt("total_books");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return count;
		
	}

	
}



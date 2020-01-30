package com.books.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.BookDetails;

//import java.util.ArrayList;

import com.books.dao.BookDetailsDAO;

public class BookDetailsDAOImpl implements BookDetailsDAO {
	//BookDetails bo=new BookDetails();

	public List<BookDetails> displayBooks() throws Exception {
		
		Connection connection=null;
		Statement stmt=null;
		List<BookDetails> list = new ArrayList<BookDetails>();
		
		try
		{
			connection=ConnectionUtil.getConnection();
			stmt=connection.createStatement();
			String sqlQuery="select * from book where active=1";
			System.out.println(sqlQuery);
			
			
			ResultSet rs=stmt.executeQuery(sqlQuery);
			while(rs.next()) 
			{
				int bookId=rs.getInt("book_id");
				String bookName=rs.getString("book_name");
				String authorName=rs.getString("author_name");
				String pub=rs.getString("publisher");
				int ver=rs.getInt("version_no");
				String category=rs.getString("categories");
				String language=rs.getString("languages");
				int active=rs.getInt("active");
				
				
				BookDetails bd = new BookDetails(bookId,bookName,authorName,pub,ver,category,language,active);
				list.add(bd);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(stmt!=null)
			{
				stmt.close();
			}
			if(connection!=null)
			{
				connection.close();
			}
		}
		
		return list;
	}
		
		//return null;
	

	public void insertBookDetails(BookDetails bookDetail) throws Exception 
	{
		Connection connection=null;
		PreparedStatement pst=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			//Statement stmt=connection.createStatement();
			String sql=("insert into book(book_id,book_name,author_name,publisher,version_no,categories,languages)values(book_id_seq1.nextval,?,?,?,?,?,?)");
			pst=connection.prepareStatement(sql);
			//pst.setInt(1, bookDetail.bookId);
			pst.setString(1,bookDetail.getBookName());
			pst.setString(2,bookDetail.authorName);
			pst.setString(3,bookDetail.publisher);
			pst.setInt(4, bookDetail.version);
			pst.setString(5, bookDetail.categories);
			pst.setString(6,bookDetail.languages);
			int row=pst.executeUpdate();
			System.out.println(row+" row inserted");
			System.out.println(sql);
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
		
		//return null;
	}

	public void deleteBookDetails(int bookId) throws Exception 
	{
		Connection connection=null;
		PreparedStatement pst=null;
		
		try
		{
			//BookDetails b=new BookDetails(bookId);
			connection=ConnectionUtil.getConnection();
			String sql=("update book set active=0 where book_id=?");
			pst=connection.prepareStatement(sql);
			
			pst.setInt(1,bookId);
			
			int row=pst.executeUpdate();
			System.out.println(row+" row deleted");
			
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
		
	}

	public void insertBookDetails(BookDetailsDAO bookDetail) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public int totalBooks() throws Exception {
		Connection con=null;
		Statement stmt=null;
		int count = 0;
		try
		{
			con=ConnectionUtil.getConnection();
			stmt=con.createStatement();
			String sql="select sum(total_quantity)as total_books from stock_room";
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next())
			{
				 count=rs.getInt("total_books");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(stmt!=null)
			{
				stmt.close();
			}
			if(con!=null)
			{
				con.close();
			}
		}
		return count;
		
	}

	
}



package com.books.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.books.model.BookStockDetails;
import com.books.util.ConnectionUtil;
import com.books.dao.BookStockDetailsDAO;

public class BookStockDetailsDAOImpl implements BookStockDetailsDAO {

	

	public void insertBookStockDetails(int bookId,int quantity)throws Exception
	{
		Connection connection=null;
		PreparedStatement pst=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			String sql="insert into stock_room(book_stock_id,book_id,total_quantity)values(book_stock_id_seq2.nextval,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setInt(1,bookId);
			pst.setInt(2,quantity);
			int row=pst.executeUpdate();
			System.out.println(row+"row inserted");
			
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

	public List<BookStockDetails> displayBookStockDetails() throws Exception{
		
		Connection connection=null;
		Statement stmt=null;
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		try
		{
			connection=ConnectionUtil.getConnection();
			stmt=connection.createStatement();
			String sql1="select *from stock_room";
			
			ResultSet rs=stmt.executeQuery(sql1);
			while(rs.next()) 
			{
				int bookStockId=rs.getInt("book_stock_id");
				int bookId=rs.getInt("book_id");
				int quantity=rs.getInt("total_quantity");
				int issuedBooks=rs.getInt("issued_books_total");
				
				BookStockDetails bd = new BookStockDetails(bookStockId,bookId,quantity,issuedBooks);
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

	public void updateStockRoom() throws Exception {
		
		Connection connection=null;
		Statement stmt=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			String sql="update stock_room set issued_books_total=issue(book_id)";
			System.out.println(sql);
			stmt=connection.createStatement();
			int row=stmt.executeUpdate(sql);
			System.out.println(row+" row updated");
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
		
	}

}

	

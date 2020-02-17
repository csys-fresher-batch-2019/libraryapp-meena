package com.books.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.books.logger.Logger;

import com.books.model.BookStockDetails;
import com.books.util.ConnectionUtil;
import com.books.dao.BookStockDetailsDAO;

public class BookStockDetailsDAOImpl implements BookStockDetailsDAO {

	 private static final Logger log=Logger.getInstance(); 


	public void insertBookStockDetails(int isbnNo)throws Exception
	{
		String sql="insert into stock_room(book_id,isbn_no)values(book_id_seq.nextval,?)";
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);)
		{
			
			pst.setInt(1,isbnNo);
			
			int row=pst.executeUpdate();
			log.getInput(row+"row inserted");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public List<BookStockDetails> displayBookStockDetails() throws Exception{
		
		
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		String sql1="select *from stock_room";
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql1);
			ResultSet rs=pst.executeQuery(sql1);)
		{
			
			while(rs.next()) 
			{
				
				int bookId=rs.getInt("book_id");
				int isbnNo=rs.getInt("isbn_no");
				int active=rs.getInt("active");
				
				BookStockDetails bd = new BookStockDetails(bookId,isbnNo,active);
				list.add(bd);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
				
		return list;
	}

	public void updateStockRoom() throws Exception {
		
		String sql="update stock_room set active=0 where book_id in(select f.book_id from stock_room s,fine_calc f where f.book_id=s.book_id and f.status='Issued')";
		
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);)
		{
			
			int row=pst.executeUpdate(sql);
			log.getInput(row+" row updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<BookStockDetails> totalStocks() throws Exception {
		List<BookStockDetails> list = new ArrayList<BookStockDetails>();
		String sql="select isbn_no,count(*)as total_books from stock_room group by isbn_no";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery(sql);)
		{
			while(rs.next()) 
			{
				
				
				int isbnNo=rs.getInt("isbn_no");
				//int bookName=rs.getInt("book_name");
				int totalBooks=rs.getInt("total_books");
				
				BookStockDetails bd = new BookStockDetails(isbnNo,totalBooks);
				list.add(bd);
			}
			return list;
		}
		
		
	}

	@Override
	public void updateActive(int bookId) throws Exception {
String sql="update stock_room set active=0 where book_id=?";
		
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);)
		{
			pst.setInt(1, bookId);
			int row=pst.executeUpdate(sql);
			log.getInput(row+" row updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<BookStockDetails> individualRemaining() throws Exception {
		List<BookStockDetails>list=new ArrayList<BookStockDetails>();
		String sql="select isbn_no,count(*)as remaining from stock_room where active=1 group by isbn_no";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery())
		{
			
			while(rs.next())
			{
				
				int isbnNo=rs.getInt("isbn_no");
				
				int remaining=rs.getInt("remaining");	
				//System.out.println(remaining);
				BookStockDetails e=new BookStockDetails(isbnNo,remaining);
				list.add(e);
			}
			return list;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookStockDetails> individualIssued() throws Exception {
		List<BookStockDetails>list=new ArrayList<BookStockDetails>();
		String sql="select isbn_no,count(*)as issued from stock_room where active=0 group by isbn_no";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();)
		{
			
			while(rs.next())
			{
				BookStockDetails d=new BookStockDetails();
				d.isbnNo=rs.getInt("isbn_no");
				
				d.issued=rs.getInt("issued");	
				
				
				
				list.add(d);
			}
			return list;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteStock(int bookId) throws Exception {
		String sql="delete from stock_room where book_id=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setInt(1, bookId);
			int row=pst.executeUpdate();
			log.getInput(row+" row deleted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}

	

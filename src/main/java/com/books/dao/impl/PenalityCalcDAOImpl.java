package com.books.dao.impl;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.PenalityCalc;
import com.books.dao.PenalityCalcDAO;

public class PenalityCalcDAOImpl implements PenalityCalcDAO{
	//PenalityCalc c=new PenalityCalc(int itemId,int bookId,int userId,issuedDate,dueDate,returnedDate,fineAmount,status);
	public void calculateFineAmount() throws Exception {
	
	
		
			Connection con=ConnectionUtil.getConnection();
			
			String sql="update fine_calc set fine_amount=FINE_AMOUNT1( book_id, user_id) where status='Issued'";
			System.out.println(sql);
			Statement stmt =con.createStatement();
			int row=stmt.executeUpdate(sql);
			System.out.println(row+" row updated");
}

	public List<PenalityCalc> displayFineDetails() throws Exception {
		Connection connection=null;
		Statement stmt=null;
		List<PenalityCalc> list = new ArrayList<PenalityCalc>();
		try
		{
			connection=ConnectionUtil.getConnection();
			stmt=connection.createStatement();
			String sql="select *from fine_calc";
			
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) 
			{
				int itemId=rs.getInt("item_id");
				int bookId=rs.getInt("book_id");
				int userId=rs.getInt("user_id");
				Date issuedDate=rs.getDate("issued_date");
				Date dueDate=rs.getDate("due_date");
				Date returnedDate=rs.getDate("returned_date");
				int fineAmount=rs.getInt("fine_amount");
				String status=rs.getString("status");
				
				PenalityCalc bd = new PenalityCalc(itemId,bookId,userId,issuedDate,dueDate,returnedDate,fineAmount,status);
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

	public void updateDueDate() throws Exception {
		
			Connection con=ConnectionUtil.getConnection();
			
			String sql="update fine_calc set due_date=(issued_date+(select days from duedate))";
			System.out.println(sql);
			Statement stmt =con.createStatement();
			int row=stmt.executeUpdate(sql);
			System.out.println(row+" row updated");
			con.close();
			stmt.close();
		}
		
	

	public void setBookLimit(int count) throws Exception {
		Connection con=null;
		PreparedStatement pst=null;
		try
		{
			con=ConnectionUtil.getConnection();
			
			String sql="update allocated set allocated_count=?";
			System.out.println(sql);
			pst =con.prepareStatement(sql);
			pst.setInt(1,count);
			int row=pst.executeUpdate();
			System.out.println(row+" row updated");
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
			if(con!=null)
			{
				con.close();
			}
		}

		
	}

	public void setPenality(int amount) throws Exception {
		
		Connection con=null;
		PreparedStatement pst=null;
		try
		{
			con=ConnectionUtil.getConnection();
			
			String sql="update fine_table set fine_amount=?";
			System.out.println(sql);
			pst =con.prepareStatement(sql);
			pst.setInt(1,amount);
			int row=pst.executeUpdate();
			System.out.println(row+" row updated");
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
			if(con!=null)
			{
				con.close();
			}
		}


		
	}

	public void setDueDays(int days) throws Exception {
		Connection con=null;
		PreparedStatement pst=null;
		try
		{
			con=ConnectionUtil.getConnection();
			
			String sql="update duedate set days=?";
			System.out.println(sql);
			pst =con.prepareStatement(sql);
			pst.setInt(1,days);
			int row=pst.executeUpdate();
			System.out.println(row+" row updated");
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
			if(con!=null)
			{
				con.close();
			}
		}

		
	}

	

	public List<Additional> booksCount() throws Exception 
	{
		Connection connection=null;
		Statement stmt=null;
		List<Additional> list = new ArrayList<Additional>();
		try
		{
			connection=ConnectionUtil.getConnection();
			stmt=connection.createStatement();
			String sql="select n.book_id,n.book_name,count(*) as cnt from fine_calc k,book n where k.book_id=n.book_id group by n.book_id,n.book_name";
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) 
			{
				int bookId=rs.getInt("book_id");
				String bookName=rs.getString("book_name");
				int count=rs.getInt("cnt");
				//int count=rs.getInt("count");
				
				
				Additional bd = new Additional(bookId,bookName,count);
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

	
	
	

	public List<CalcCard> userCardCount() throws Exception
	{
		List<CalcCard>list=new ArrayList<CalcCard>();
		Connection connection=ConnectionUtil.getConnection();
		Statement stmt=connection.createStatement();
		String sql="select user_id,count2(user_id)as taken_books, (case when count2(user_id)<=(select allocated_count from allocated) then((select allocated_count from allocated)-count2(user_id) )else 0 end)as remaining from fine_calc group by user_id";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
			{
			int userId=rs.getInt("user_id");
			int takenBooks=rs.getInt("taken_books");
			int remaining=rs.getInt("remaining");
			
			CalcCard k=new CalcCard(userId,takenBooks,remaining);
			list.add(k);
			}
		return list;
	}

	public void updateReturnStatus(int bookId,int userId,LocalDate returnedDate) throws Exception
	{
		Connection connection=null;
		PreparedStatement pst=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			String sql="update fine_calc set returned_date=?,status='Returned', fine_amount=fine_amount-fine_amount where book_id=? and user_id=? and status='Issued'";
			pst=connection.prepareStatement(sql);
			Date rd=Date.valueOf(returnedDate);
			pst.setDate(1,rd);
			pst.setInt(2, bookId);
			pst.setInt(3, userId);
			int row=pst.executeUpdate();
			System.out.println(row+" row updated");
			
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

	

	public void insertUserBookDetails(int bookId, int userId, Date issuedDate) throws Exception {
		Connection con=null;
		PreparedStatement pst=null;
		try
		{
			con=ConnectionUtil.getConnection();
			String sql="insert into fine_calc(item_id,book_id,user_id,issued_date)values(item_id1_seq3.nextval,?,?,?)";
			pst=con.prepareStatement(sql);
			//Date rd=Date.valueOf(issuedDate);
			
			pst.setInt(1, bookId);
			pst.setInt(2, userId);
			pst.setDate(3,issuedDate);
			int row=pst.executeUpdate();
			System.out.println(row+" row updated");
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
			if(con!=null)
			{
				con.close();
			}
		}
		
	}



	
}

	

	
	


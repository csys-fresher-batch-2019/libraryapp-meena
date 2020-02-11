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
import com.books.model.CategorySettings;
import com.books.model.LanguageSettings;
import com.books.model.PenalityCalc;
import com.books.dao.PenalityCalcDAO;
import com.books.logger.Logger;

public class PenalityCalcDAOImpl implements PenalityCalcDAO{
	 private static final Logger log=Logger.getInstance(); 

	
	public void calculateFineAmount() throws Exception {
	
					
			String sql="update fine_calc set fine_amount=FUNCTION3( book_id, user_id) where status='Issued'";
			log.getInput(sql);
			try(Connection con=ConnectionUtil.getConnection();Statement stmt =con.createStatement();){
			int row=stmt.executeUpdate(sql);
			log.getInput(row+" row updated");
			}catch(Exception e)
			{
				
			}
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
			log.getInput(sql);
			Statement stmt =con.createStatement();
			int row=stmt.executeUpdate(sql);
			log.getInput(row+" row updated");
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
			log.getInput(sql);
			pst =con.prepareStatement(sql);
			pst.setInt(1,count);
			int row=pst.executeUpdate();
			log.getInput(row+" row updated");
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
			log.getInput(sql);
			pst =con.prepareStatement(sql);
			pst.setInt(1,amount);
			int row=pst.executeUpdate();
			log.getInput(row+" row updated");
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
			log.getInput(sql);
			pst =con.prepareStatement(sql);
			pst.setInt(1,days);
			int row=pst.executeUpdate();
			log.getInput(row+" row updated");
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
		String sql="select n.isbn_no,count(*) as cnt from fine_calc k,stock_room n where k.book_id=n.book_id group by n.isbn_no";
		List<Additional> list = new ArrayList<Additional>();
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();)
		{
			
			while(rs.next()) 
			{
				int isbn_no=rs.getInt("isbn_no");
				int count=rs.getInt("cnt");				
				Additional bd = new Additional(isbn_no,count);
				list.add(bd);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
		
	}

	public List<CalcCard> userCardCount() throws Exception
	{
		String sql="select user_id,count2(user_id)as taken_books, (case when count2(user_id)<=(select allocated_count from allocated) then((select allocated_count from allocated)-count2(user_id) )else 0 end)as remaining from fine_calc group by user_id";
		List<CalcCard>list=new ArrayList<CalcCard>();
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery(sql);)
		{
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
		
		
		
		
	}

	public void updateReturnStatus(int bookId,int userId,LocalDate returnedDate) throws Exception
	{
		String sql="update fine_calc set returned_date=?,status='Returned', fine_amount=fine_amount-fine_amount where book_id=? and user_id=? and status='Issued'";
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);)
		{
			
			Date rd=Date.valueOf(returnedDate);
			pst.setDate(1,rd);
			pst.setInt(2, bookId);
			pst.setInt(3, userId);
			int row=pst.executeUpdate();
			log.getInput(row+" row updated");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	

	public void insertUserBookDetails(int bookId, int userId, Date issuedDate) throws Exception {
		
		String str1="select isbn_no  from stock_room sr,fine_calc fc where sr.book_id = fc.book_id and user_id=? and isbn_no = (select isbn_no  from stock_room where book_id =?)";
		String sql="insert into fine_calc(item_id,book_id,user_id,issued_date)values(item_id1_seq3.nextval,?,?,?)";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst1=con.prepareStatement(str1);)
		{
			
			pst1.setInt(1,userId);
			pst1.setInt(2,bookId);
			ResultSet rs=pst1.executeQuery();
			if(rs.next())
			{
				log.getInput("Already taken");
			}
			else
			{
			
			PreparedStatement pst=con.prepareStatement(sql);
			
			
			pst.setInt(1, bookId);
			pst.setInt(2, userId);
			pst.setDate(3,issuedDate);
			int row=pst.executeUpdate();
			log.getInput(row+" row updated");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

	public void insertNewLanguage(String language) throws Exception 
	{
		String sql="insert into languages(languages) values(?)";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			
			pst.setString(1, language);
			int row=pst.executeUpdate();
			log.getInput(row+" row inserted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public void deleteLanguage(String language1) throws Exception 
	{
		String sql="update languages set active=0 where languages=?";
		
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			
			pst.setString(1, language1);
			int row=pst.executeUpdate();
			log.getInput(row+" row deleted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}

	public List<LanguageSettings> displayLanguages() throws Exception
	{
		List<LanguageSettings>list=new ArrayList<LanguageSettings>();
		String sql="select * from languages where active=1";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			
			ResultSet rs=pst.executeQuery(sql);)		{
			while(rs.next())
				{
				String language=rs.getString("languages");
				LanguageSettings k=new LanguageSettings(language);
				list.add(k);
				}
			return list;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public void insertNewCategory(String category) throws Exception
	{
		String sql="insert into category(categories)values(?)";
		
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, category);
			int row=pst.executeUpdate();
			log.getInput(row+" row inserted");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public void deteleCategory(String category1) throws Exception
	{
		String sql="update category set active=0 where categories=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, category1);
			int row=pst.executeUpdate();
			log.getInput(row+" row deleted");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}

	public List<CategorySettings> displayCategories() throws Exception {
		List<CategorySettings>list=new ArrayList<CategorySettings>();
		
		String sql="select * from category where active=1";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery(sql);)
		{
			
			
			
			while(rs.next())
				{
				String category=rs.getString("categories");
				
				
				CategorySettings d=new CategorySettings(category);
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
}



	


	

	
	


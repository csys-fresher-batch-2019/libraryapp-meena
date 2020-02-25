package com.books.dao.impl;

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

	
	public void calculateFineAmount(int bookId2, int userId2) throws Exception {
	
					
			String sql="update fine_calc set fine_amount=FUNCTION3( book_id, user_id) where status='Issued'and book_id=? and user_id=?";
			log.getInput(sql);
			try(Connection con=ConnectionUtil.getConnection();PreparedStatement stmt =con.prepareStatement(sql);)
			{
			stmt.setInt(1,bookId2);	
			stmt.setInt(2, userId2);
			int row=stmt.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
}

	public List<PenalityCalc> displayFineDetails() throws Exception {
		String sql="select *from fine_calc";
		List<PenalityCalc> list = new ArrayList<PenalityCalc>();
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql))
		{
			try(ResultSet rs=pst.executeQuery())
			{
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return list;
	}

	public void updateDueDate(int bookId2, int userId2) throws Exception {
		
		String sql="update fine_calc set due_date=(issued_date+(select days from duedate))where book_id=? and user_id=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement stmt =con.prepareStatement(sql);)	
		{
			log.getInput(sql);
			
			stmt.setInt(1, bookId2);
			stmt.setInt(2, userId2);
			int row=stmt.executeUpdate();
			con.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		}
		
	

	public int setBookLimit(int count) throws Exception {
		String sql="update allocated set allocated_count=?";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql))
		{
			log.getInput(sql);
			pst.setInt(1,count);
			row=pst.executeUpdate();
			log.getInput(row+" row updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	public int setPenality(int amount) throws Exception {
		
		int row=0;
		String sql="update fine_table set fine_amount=?";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			log.getInput(sql);
			pst.setInt(1,amount);
			row=pst.executeUpdate();
			log.getInput(row+" row updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;	
	}

	public int setDueDays(int days) throws Exception {
		String sql="update duedate set days=?";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			log.getInput(sql);
			pst.setInt(1,days);
			row=pst.executeUpdate();
			log.getInput(row+" row updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;	
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
		String sql="select user_id,count2(user_id)as taken_books, (case when count2(user_id)<=(select allocated_count from allocated) then((select allocated_count from allocated)-count2(user_id) )else 0 end)as remaining from users group by user_id";
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

	public int updateReturnStatus(int bookId,int userId,Date returnedDate) throws Exception
	{
		int row=0;
		String sql="update fine_calc set returned_date=?,status='Returned', fine_amount=fine_amount-fine_amount where book_id=? and user_id=? and status='Issued'";
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sql);)
		{
			pst.setDate(1,returnedDate);
			pst.setInt(2, bookId);
			pst.setInt(3, userId);
			row=pst.executeUpdate();
			log.getInput(row+" row updated");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	

	public int insertUserBookDetails(int bookId, int userId, Date issuedDate) throws Exception {
		int row=0;
		String str1="select isbn_no  from stock_room sr,fine_calc fc where sr.book_id = fc.book_id and user_id=? and isbn_no = (select isbn_no  from stock_room where book_id =?)";
		String sql="insert into fine_calc(item_id,book_id,user_id,issued_date)values(item_id_seq.nextval,?,?,?)";
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
			row=pst.executeUpdate();
			log.getInput(row+" row updated");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
		
	}

	public int insertNewLanguage(String language) throws Exception 
	{
		int row=0;
		String sql="insert into languages(languages) values(?)";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, language);
			row=pst.executeUpdate();
			log.getInput(row+" row inserted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	public int deleteLanguage(String language1) throws Exception 
	{
		String sql="update languages set active=0 where languages=?";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, language1);
			row=pst.executeUpdate();
			log.getInput(row+" row deleted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return row;
	}

	public List<LanguageSettings> displayLanguages() throws Exception
	{
		List<LanguageSettings>list=new ArrayList<LanguageSettings>();
		String sql="select * from languages where active=1";
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery(sql))		{
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

	public int insertNewCategory(String category) throws Exception
	{
		String sql="insert into category(categories)values(?)";
		int row=0;
		try(Connection con=ConnectionUtil.getConnection();
			PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, category);
			row=pst.executeUpdate();
			log.getInput(row+" row inserted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}

	public int deteleCategory(String category1) throws Exception
	{
		int row=0;
		String sql="update category set active=0 where categories=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setString(1, category1);
			row=pst.executeUpdate();
			log.getInput(row+" row deleted");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateDueDateAll() throws Exception{
			
		String sql="update fine_calc set due_date=(issued_date+(select days from duedate))";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement stmt =con.prepareStatement(sql);)
		{
			log.getInput(sql);
			int row=stmt.executeUpdate(sql);
			con.close();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		}

	@Override
	public void updateFineAll() throws Exception {
		String sql="update fine_calc set fine_amount=FUNCTION3( book_id, user_id) where status='Issued'";
		log.getInput(sql);
		try(Connection con=ConnectionUtil.getConnection();PreparedStatement stmt =con.prepareStatement(sql);)
		{
		
		int row=stmt.executeUpdate(sql);
		//log.getInput(row+" row updated");
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	@Override
	public void updatePopup(int popup) throws Exception {
		String sql="update popup set popup=?";
		try(Connection con=ConnectionUtil.getConnection();
				PreparedStatement pst=con.prepareStatement(sql);)
		{
			pst.setInt(1, popup);
			int row=pst.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
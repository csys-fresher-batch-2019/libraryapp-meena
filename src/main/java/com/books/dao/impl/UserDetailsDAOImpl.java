package com.books.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.books.util.ConnectionUtil;
import com.books.model.UserDetails;
import com.books.dao.UserDetailsDAO;
public class UserDetailsDAOImpl implements UserDetailsDAO {

	public void insertUserDetails(UserDetails userDetails) throws Exception 
	{
		
			Connection connection=null;
			PreparedStatement pst=null;
			try
			{
				connection=ConnectionUtil.getConnection();
				String sql=("insert into users(user_id,user_name,address,ph_no)values(user_id_seq.nextval,?,?,?)");
				pst=connection.prepareStatement(sql);
				
				pst.setString(1,userDetails.getUserName());
				pst.setString(2,userDetails.getAddress());
				pst.setLong(3,userDetails.getPhno());
				
				int row=pst.executeUpdate();
				System.out.println(row+"row inserted");
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
			
	}

	public List<UserDetails> displayUserDetails() throws Exception {
		Connection connection=null;
		Statement stmt=null;
		List<UserDetails> list = new ArrayList<UserDetails>();
		try
		{
			connection=ConnectionUtil.getConnection();
			stmt=connection.createStatement();
			String sqlQuery="select * from users";
			System.out.println(sqlQuery);
			
			
			ResultSet rs=stmt.executeQuery(sqlQuery);
			while(rs.next()) 
			{
				int userId=rs.getInt("user_id");
				String userName=rs.getString("user_name");
				String address=rs.getString("address");
				long phno=rs.getLong("ph_no");
				
				
				
				UserDetails bd = new UserDetails(userId,userName,address,phno);
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

	public void deleteUserDetails(int userId) throws Exception 
	{	
		Connection connection=null;
		PreparedStatement stmt=null;
		try
		{
			connection=ConnectionUtil.getConnection();
			String sql=("update users set active=0 where user_id=?");
			PreparedStatement pst=connection.prepareStatement(sql);
			
			pst.setInt(1,userId);
			
			int row=pst.executeUpdate();
			System.out.println(row+"row deleted");
			
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


	

	


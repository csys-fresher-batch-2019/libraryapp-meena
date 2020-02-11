package com.books.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.books.util.ConnectionUtil;
import com.books.model.UserDetails;
import com.books.dao.UserDetailsDAO;
import com.books.logger.Logger;
public class UserDetailsDAOImpl implements UserDetailsDAO {
	 private static final Logger log=Logger.getInstance();
	 
	public void insertUserDetails(UserDetails userDetails) throws Exception 
	{
		

		
		String sql=("insert into users(user_id,user_name,address,ph_no,email,password,gender)values(user_id_seq.nextval,?,?,?,?,?,?)");
			try(Connection connection=ConnectionUtil.getConnection();
					PreparedStatement pst=connection.prepareStatement(sql);)
			{
				pst.setString(1,userDetails.getUserName());
				pst.setString(2,userDetails.getAddress());
				pst.setLong(3,userDetails.getPhno());
				pst.setString(4,userDetails.email);
				pst.setString(5, userDetails.password);
				pst.setString(6, String.valueOf(userDetails.gender));
				int row=pst.executeUpdate();
				log.getInput(row+"row inserted");
				log.getInput(sql);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
	}

	public List<UserDetails> displayUserDetails() throws Exception {
		String sqlQuery="select * from users";
		List<UserDetails> list = new ArrayList<UserDetails>();
		try(Connection connection=ConnectionUtil.getConnection();
			PreparedStatement pst=connection.prepareStatement(sqlQuery);
				ResultSet rs=pst.executeQuery(sqlQuery);)
		{		
			while(rs.next()) 
			{
				int userId=rs.getInt("user_id");
				String userName=rs.getString("user_name");
				String address=rs.getString("address");
				long phno=rs.getLong("ph_no");
				String email=rs.getString("email");
			String gender=rs.getString("gender");
				
				UserDetails bd = new UserDetails(userId,userName,address,phno,email,gender);
				list.add(bd);
			
			}
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		
		return list;
	}

	public void deleteUserDetails(int userId) throws Exception 
	{	String sql=("update users set active=0 where user_id=?");
		
		try(Connection connection=ConnectionUtil.getConnection();
				PreparedStatement pst=connection.prepareStatement(sql);)
		{		
			pst.setInt(1,userId);
			
			int row=pst.executeUpdate();
			log.getInput(row+"row deleted");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	
}


	

	


package com.books.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.books.dao.UserDetailsDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.UserDetails;

public class UserDetailsDAOImplTest {
	UserDetailsDAO m = DAOFactory.getUserDetailsDAO();

	@Test 
	public void testInsertUserDetails() throws Exception {
		
		String userName = "Meenakshi";
		String address = "Kovilpatti";
		long phno = 9489745475L;
		String email = "meena@gmail.com";
		String password = "Meenakshi@123";
		String gender = "F";
		UserDetails userDetails = new UserDetails(userName, address, phno, email, password, gender);
		int expected = 1;
		int actual = m.insertUserDetails(userDetails);
		assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testDisplayUserDetails() throws Exception {	
		List<UserDetails> actual = m.displayUserDetails();
		assertTrue(!actual.isEmpty());
		
	}

	@Test @Ignore
	public void testDeleteUserDetails() throws Exception {
		int userId = 11;
		int actual = m.deleteUserDetails(userId);
		int expected=1;
		assertEquals(expected, actual);

	}

}

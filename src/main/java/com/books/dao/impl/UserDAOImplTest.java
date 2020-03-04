package com.books.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.books.dao.UserDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.BookDetails;
import com.books.model.CalcCard;
import com.books.model.User;

public class UserDAOImplTest {

	UserDAO m = DAOFactory.getUserDAO();

	@Test
	@Ignore
	public void testNoViewHistory() throws Exception {
		int userId = 43;
		List<User> actual = m.viewHistory(userId);
		Assert.assertTrue(actual.isEmpty());
	}

	@Test
	@Ignore
	public void testViewHistory() throws Exception {
		int userId = 5;
		List<User> actual = m.viewHistory(userId);
		Assert.assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testCheckLogin() throws Exception {
		String email = "meena@gmail.com";
		String password = "Meenakshi@123";
		int actual = m.checkLogin(email, password);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	@Ignore
	public void testNoCurrentBooks() throws Exception {
		int userId = 43;
		List<User> actual = m.currentBooks(userId);
		Assert.assertTrue(actual.isEmpty());
	}

	@Test
	@Ignore
	public void testCurrentBooks() throws Exception {
		int userId = 5;
		List<User> actual = m.currentBooks(userId);
		Assert.assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testSearchBookName() throws Exception {

		String bookName = "ele";
		List<BookDetails> actual = m.searchBookName(bookName);
		Assert.assertTrue(actual.isEmpty());
	}

	@Test
	@Ignore
	public void testNoSearchBookName() throws Exception {

		String bookName = "thi";
		List<BookDetails> actual = m.searchBookName(bookName);
		Assert.assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testSearchAuthorName() throws Exception {
		String author = "thi";
		List<BookDetails> actual = m.searchAuthorName(author);
		Assert.assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testNotSearchAuthorName() throws Exception {
		String author = "chan";
		List<BookDetails> actual = m.searchAuthorName(author);
		Assert.assertTrue(actual.isEmpty());
	}

	@Test
	@Ignore
	public void testRemainingCard() throws Exception {
		int userId = 5;
		List<CalcCard> actual = m.remainingCard(userId);
		Assert.assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testUpdatePhoneNumber() throws Exception {
		int userId = 5;
		Long phoneNumber = 7708467423L;
		int actual = m.updatePhoneNumber(userId, phoneNumber);
		int expected = 1;
		Assert.assertEquals(actual, expected);
	}

	@Test
	@Ignore
	public void testUpdateAddress() throws Exception {
		int userId = 5;
		String address = "78,Bharathi nagar,Kovilpatti";
		int actual = m.updateAddress(userId, address);
		int expected = 1;
		Assert.assertEquals(actual, expected);
	}

	@Test
	@Ignore
	public void testChangePassword() throws Exception {
		int userId = 5;
		String password = "Meena@123";
		int actual = m.changePassword(userId, password);
		int expected = 1;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDueDateCheck() throws Exception {
		int userId = 5;
		List<User> actual = m.dueDateCheck(userId);
		assertTrue(actual.isEmpty());
	}
}

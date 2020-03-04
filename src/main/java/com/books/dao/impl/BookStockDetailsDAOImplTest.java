package com.books.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.books.dao.BookStockDetailsDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.BookStockDetails;

public class BookStockDetailsDAOImplTest {
	BookStockDetailsDAO m = DAOFactory.getBookStockDetailsDAO();

	@Test
	@Ignore
	public void testInsertBookStockDetails() throws Exception {
		int isbnNo = 1001;
		int actual = m.insertBookStockDetails(isbnNo);
		int expected = 1;
		Assert.assertEquals(expected, actual);
	}

	@Test
	@Ignore
	public void testDisplayBookStockDetails() throws Exception {
		List<BookStockDetails> actual = m.displayBookStockDetails();
		assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testUpdateStockRoom() throws Exception {
		int actual = m.updateStockRoom();
		assertTrue(actual != 0);
	}

	@Test
	@Ignore
	public void testTotalStocks() throws Exception {
		List<BookStockDetails> actual = m.totalStocks();
		assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testUpdateActive() throws Exception {
		int bookId = 3;
		int actual = m.updateActive(bookId);
		int expected = 1;
		Assert.assertEquals(expected, actual);
	}

	@Test
	@Ignore
	public void testIndividualRemaining() throws Exception {

		List<BookStockDetails> actual = m.individualRemaining();
		assertTrue(!actual.isEmpty());
	}

	@Test
	@Ignore
	public void testIndividualIssued() throws Exception {
		List<BookStockDetails> actual = m.individualIssued();
		assertTrue(!actual.isEmpty());
	}

	@Test
	public void testDeleteStock() throws Exception {
		int bookId = 8;
		int actual = m.deleteStock(bookId);
		int expected = 1;
		Assert.assertEquals(expected, actual);
	}

}

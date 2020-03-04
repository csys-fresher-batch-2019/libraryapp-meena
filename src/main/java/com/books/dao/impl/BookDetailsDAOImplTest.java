package com.books.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.books.dao.BookDetailsDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.BookDetails;

public class BookDetailsDAOImplTest {
	BookDetailsDAO k=DAOFactory.getBookDetailsDAO();
	@Test @Ignore
	public void testDisplayBooks() throws Exception {
		List<BookDetails> actual=k.displayBooks();
		assertTrue(!actual.isEmpty());
	}

	@Test @Ignore
	public void testInsertBookDetails() throws Exception {
		int isbnNo=1040;
		String bookName="Basic of marketing";
		String authorName="Sundar";
		String publisher="AVK publisher";
		int version=1;
		String category="Business";
		String language="English";
		int totalStocks=0;
		int active=1;
		BookDetails obj=new BookDetails(isbnNo,bookName,authorName,publisher,version,category,language,totalStocks,active);
		int row=k.insertBookDetails(obj);
	}

	@Test 
	public void testDeleteBookDetails() throws Exception {
		int bookId=40;
		int actual=k.deleteBookDetails(bookId);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testTotalBooks() throws Exception {
		int actual=k.totalBooks();
		assertTrue(actual!=0);
	}

	@Test @Ignore
	public void testCheckAdmin() throws Exception {
		String userName="admin";
		String password="admin";
		int actual=k.checkAdmin(userName, password);
		int expected=1;
		Assert.assertEquals(expected,actual);
	}

	@Test @Ignore
	public void testUpdateTotalStock() throws Exception {
		int actual=k.updateTotalStock();
		assertTrue(actual!=0);
	}

}

package com.books.dao.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.books.dao.BookStockDetailsDAO;
import com.books.dao.PenalityCalcDAO;
import com.books.daofactory.DAOFactory;
import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.CategorySettings;
import com.books.model.LanguageSettings;
import com.books.model.PenalityCalc;



public class PenalityCalcDAOImplTest {
	PenalityCalcDAO m = DAOFactory.getPenalityCalcDAO();
	BookStockDetailsDAO b = DAOFactory.getBookStockDetailsDAO();
	@Test @Ignore
	public void testCalculateFineAmount() throws Exception {
		int actual=m.updateFineAll();
		assertTrue(actual!=0);
	}

	@Test @Ignore
	public void testDisplayFineDetails() throws Exception {
		List<PenalityCalc>actual=m.displayFineDetails();
		assertTrue(!actual.isEmpty());
	}

	@Test 
	public void testUpdateDueDate() throws Exception {
		int bookId=6;
		int userId=15;
		int actual=m.updateDueDate(bookId, userId);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testSetBookLimit() throws Exception {
		int bookLimit=5;
		int actual=m.setBookLimit(bookLimit);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testSetPenality() throws Exception {
		int penality=5;
		int actual=m.setPenality(penality);
		int expected=1;
		Assert.assertEquals(expected, actual);	
		}

	@Test @Ignore
	public void testSetDueDays() throws Exception {
		int dueDays=5;
		int actual=m.setDueDays(dueDays);
		int expected=1;
		Assert.assertEquals(expected, actual);	}

	@Test @Ignore
	public void testBooksCount() throws Exception {
		List<Additional>actual=m.booksCount();
		assert(!actual.isEmpty());
	}

	@Test @Ignore
	public void testUserCardCount() throws Exception {
		List<CalcCard>actual=m.userCardCount();
		assertTrue(!actual.isEmpty());
	}

	@Test @Ignore
	public void testUpdateReturnStatus() throws Exception {
		int bookId=21;
		int userId=4;
		String returnedDate1="2020-03-07";
		Date returnedDate = Date.valueOf(returnedDate1);
		int actual=m.updateReturnStatus(bookId, userId, returnedDate);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testInsertUserBookDetails() throws Exception {
		int bookId=43;
		int userId=4;
		String issuedDate1="2020-03-04";
		Date issuedDate = Date.valueOf(issuedDate1);
		int actual=m.insertUserBookDetails(bookId, userId, issuedDate);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testInsertNewLanguage() throws Exception 
	{
		String language="Russian";
		int actual=m.insertNewLanguage(language);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testDeleteLanguage() throws Exception 
	{
		String language="Russian";
		int actual=m.deleteLanguage(language);
		int expected=1;
		Assert.assertEquals(expected, actual);
	}

	@Test @Ignore
	public void testDisplayLanguages() throws Exception {
		List<LanguageSettings>actual=m.displayLanguages();
		assertTrue(!actual.isEmpty());
	}

	@Test @Ignore
	public void testInsertNewCategory() throws Exception {
		String category="Non-Technical";
		int actual=m.insertNewCategory(category);
		int expected=1;
		Assert.assertEquals(expected, actual);	}

	@Test @Ignore
	public void testDeteleCategory() throws Exception {
		String category="Non-Technical";
		int actual=m.deteleCategory(category);
		int expected=1;
		Assert.assertEquals(expected, actual);	}

	@Test @Ignore
	public void testDisplayCategories() throws Exception {
		List<CategorySettings>actual=m.displayCategories();
		assertTrue(actual.isEmpty());
	}

	@Test @Ignore
	public void testUpdateDueDateAll() throws Exception {
		int actual=m.updateDueDateAll();
		assertTrue(actual!=0);
	}

	@Test @Ignore
	public void testUpdateFineAll() throws Exception {
		int actual=m.updateFineAll();
		assertTrue(actual!=0);		
	}

	@Test @Ignore
	public void testUpdatePopup() throws Exception
	{ 
		int popup=1;
		int actual=m.updatePopup(popup);
		int expected=1;
		Assert.assertEquals(expected,actual);
	}

}

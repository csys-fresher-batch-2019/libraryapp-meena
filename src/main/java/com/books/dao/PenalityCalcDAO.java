package com.books.dao;

import java.sql.Date;
import java.util.List;

import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.CategorySettings;
import com.books.model.LanguageSettings;
import com.books.model.PenalityCalc;

public interface PenalityCalcDAO {
	public void calculateFineAmount(int bookId2, int userId2) throws Exception;

	public List<PenalityCalc> displayFineDetails() throws Exception;

	public int updateDueDate(int bookId2, int userId2) throws Exception;

	public int setBookLimit(int count) throws Exception;

	public int setPenality(int amount) throws Exception;

	public int setDueDays(int days) throws Exception;

	public List<Additional> booksCount() throws Exception;

	public int updateReturnStatus(int bookId, int userId, Date returnedDate) throws Exception;

	public List<CalcCard> userCardCount() throws Exception;

	public int insertUserBookDetails(int bookId, int userId, Date issuedDate) throws Exception;

	public int insertNewLanguage(String language) throws Exception;

	public int deleteLanguage(String language1) throws Exception;

	public List<LanguageSettings> displayLanguages() throws Exception;

	public int insertNewCategory(String category) throws Exception;

	public int deteleCategory(String category1) throws Exception;

	public List<CategorySettings> displayCategories() throws Exception;

	public int updateDueDateAll() throws Exception;

	public int updateFineAll() throws Exception;

	public int updatePopup(int popup) throws Exception;
}

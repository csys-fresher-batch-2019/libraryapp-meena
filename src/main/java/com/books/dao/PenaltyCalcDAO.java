package com.books.dao;

import java.sql.Date;
import java.util.List;

import com.books.dto.BookCount;
import com.books.dto.CalcCard;
import com.books.dto.CategorySettings;
import com.books.dto.LanguageSettings;
import com.books.exception.DbException;
import com.books.model.PenaltyCalc;

public interface PenaltyCalcDAO {
	public void findFineAmount(int bookId2, int userId2) throws DbException;

	public List<PenaltyCalc> findAllFineDetails() throws DbException;

	public int updateDueDate(int bookId2, int userId2) throws DbException;

	public int saveBookLimit(int count) throws DbException;

	public int savePenality(int amount) throws DbException;

	public int saveDueDays(int days) throws DbException;

	public List<BookCount> findBybooksCount() throws DbException;

	public int updateReturnStatus(int bookId, int userId, Date returnedDate) throws DbException;

	public List<CalcCard> findAlluserCardCount() throws DbException;

	public int saveUserBookDetails(int bookId, int userId, Date issuedDate) throws DbException;

	public int saveNewLanguage(String language) throws DbException;

	public int deleteLanguage(String language1) throws DbException;

	public List<LanguageSettings> findAllLanguages() throws DbException;

	public int saveNewCategory(String category) throws DbException;

	public int deteleCategory(String category1) throws DbException;

	public List<CategorySettings> findAllCategories() throws DbException;

	public int updateDueDateAll() throws DbException;

	public int updateFineAll() throws DbException;

	public int updatePopup(int popup) throws DbException;
}

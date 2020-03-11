package com.books.dao;

import java.sql.Date;
import java.util.List;

import com.books.dto.Additional;
import com.books.dto.CalcCard;
import com.books.dto.CategorySettings;
import com.books.dto.LanguageSettings;
import com.books.model.PenalityCalc;

public interface PenalityCalcDAO {
	public void findFineAmount(int bookId2, int userId2) throws Exception;

	public List<PenalityCalc> findAllFineDetails() throws Exception;

	public int updateDueDate(int bookId2, int userId2) throws Exception;

	public int saveBookLimit(int count) throws Exception;

	public int savePenality(int amount) throws Exception;

	public int saveDueDays(int days) throws Exception;

	public List<Additional> findBybooksCount() throws Exception;

	public int updateReturnStatus(int bookId, int userId, Date returnedDate) throws Exception;

	public List<CalcCard> findAlluserCardCount() throws Exception;

	public int saveUserBookDetails(int bookId, int userId, Date issuedDate) throws Exception;

	public int saveNewLanguage(String language) throws Exception;

	public int deleteLanguage(String language1) throws Exception;

	public List<LanguageSettings> findAllLanguages() throws Exception;

	public int saveNewCategory(String category) throws Exception;

	public int deteleCategory(String category1) throws Exception;

	public List<CategorySettings> findAllCategories() throws Exception;

	public int updateDueDateAll() throws Exception;

	public int updateFineAll() throws Exception;

	public int updatePopup(int popup) throws Exception;
}

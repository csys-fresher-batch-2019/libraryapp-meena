package com.books.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.CategorySettings;
import com.books.model.LanguageSettings;
import com.books.model.PenalityCalc;

public interface PenalityCalcDAO {

	public void calculateFineAmount(int bookId2,int userId2)throws Exception;
	
	public List<PenalityCalc>displayFineDetails()throws Exception;
	
	public void updateDueDate(int bookId2,int userId2)throws Exception;
	
	public void setBookLimit(int count)throws Exception;
	
	public void setPenality(int amount) throws Exception;
	
	public void setDueDays(int days)throws Exception;
	
	public List<Additional> booksCount()throws Exception;
	
	public void updateReturnStatus(int bookId,int userId,LocalDate returnedDate)throws Exception;
	
	public List<CalcCard>userCardCount()throws Exception;
	
	public void insertUserBookDetails(int bookId,int userId,Date issuedDate)throws Exception;
	
	public void insertNewLanguage(String language)throws Exception;
	
	public void deleteLanguage(String language1)throws Exception;
	
	public List<LanguageSettings>displayLanguages()throws Exception;
	
	public void insertNewCategory(String category)throws Exception;
	
	public void deteleCategory(String category1)throws Exception;
	
	public List<CategorySettings>displayCategories()throws Exception;
	
	public void updateDueDateAll()throws Exception;
	
	public void updateFineAll()throws Exception;
	
	public void updatePopup(int popup)throws Exception;
}

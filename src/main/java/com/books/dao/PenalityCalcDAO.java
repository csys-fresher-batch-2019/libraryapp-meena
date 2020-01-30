package com.books.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.PenalityCalc;

public interface PenalityCalcDAO {

	public void calculateFineAmount()throws Exception;
	
	public List<PenalityCalc>displayFineDetails()throws Exception;
	
	public void updateDueDate()throws Exception;
	
	public void setBookLimit(int count)throws Exception;
	
	public void setPenality(int amount) throws Exception;
	
	public void setDueDays(int days)throws Exception;
	
	public List<Additional> booksCount()throws Exception;
	
	public void updateReturnStatus(int bookId,int userId,LocalDate returnedDate)throws Exception;
	
	public List<CalcCard>userCardCount()throws Exception;
	
	public void insertUserBookDetails(int bookId,int userId,Date issuedDate)throws Exception;
}

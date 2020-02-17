package com.books.dao;

import java.util.List;

import com.books.model.BookStockDetails;


public interface BookStockDetailsDAO 
{
	public void insertBookStockDetails(int isbnNo)throws Exception;
	
	public List<BookStockDetails> displayBookStockDetails()throws Exception;
	
	public void updateStockRoom()throws Exception;
	
	public List<BookStockDetails> totalStocks()throws Exception;
	
	public void updateActive(int bookId)throws Exception;
	
	public List<BookStockDetails>individualRemaining()throws Exception;
	
	public List<BookStockDetails>individualIssued()throws Exception;
	
	public void deleteStock(int bookId)throws Exception;

}

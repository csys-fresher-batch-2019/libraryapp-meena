package com.books.dao;

import java.util.List;

import com.books.model.BookStockDetails;


public interface BookStockDetailsDAO 
{
	public void insertBookStockDetails(int bookId,int quantity)throws Exception;
	
	public List<BookStockDetails> displayBookStockDetails()throws Exception;
	
	public void updateStockRoom()throws Exception;

}

package com.books.daofactory;

import com.books.dao.BookDetailsDAO;
import com.books.dao.BookStockDetailsDAO;
import com.books.dao.PenaltyCalcDAO;
import com.books.dao.UserDAO;
import com.books.dao.UserDetailsDAO;
import com.books.dao.impl.BookDetailsDAOImpl;
import com.books.dao.impl.BookStockDetailsDAOImpl;
import com.books.dao.impl.PenaltyCalcDAOImpl;
import com.books.dao.impl.UserDAOImpl;
import com.books.dao.impl.UserDetailsDAOImpl;

public class DAOFactory {
public static BookDetailsDAO getBookDetailsDAO()
{
	return(new BookDetailsDAOImpl());
}
public static BookStockDetailsDAO getBookStockDetailsDAO()
{
	return(new BookStockDetailsDAOImpl());
}
public static PenaltyCalcDAO getPenalityCalcDAO()
{
	return (new PenaltyCalcDAOImpl());
}
public static UserDAO getUserDAO()
{
	return(new UserDAOImpl());
}
public static UserDetailsDAO getUserDetailsDAO()
{
	return(new UserDetailsDAOImpl());
}
}

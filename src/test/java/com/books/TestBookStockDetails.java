package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.model.BookStockDetails;
import com.books.dao.impl.BookStockDetailsDAOImpl;

public class TestBookStockDetails {

	public static void main(String[] args) throws Exception 
	{
		BookStockDetailsDAOImpl m=new BookStockDetailsDAOImpl();
		BookStockDetails obj=new BookStockDetails();
		Scanner k=new Scanner(System.in);
		char s;
		
		do {
			log.getInput("Enter your choice");
			log.getInput("1.Display Stocks");
			log.getInput("2.Insert new stocks");
			log.getInput("3.Update stock room");
			int ch=k.nextInt();
			switch(ch)
			{
			case 1:
				List<BookStockDetails>displayBookStockDetails=m.displayBookStockDetails();
				for (BookStockDetails bookStockDetails : displayBookStockDetails)
				{
					log.getInput(bookStockDetails);
				}
				break;
			case 2:
				log.getInput("Enter the ISBN number:");
				obj.isbnNo=k.nextInt();
				
				//BookStockDetails omg=new BookStockDetails(bookId2,quantity2);
				m.insertBookStockDetails(obj.isbnNo);
				break;
			case 3:
				m.updateStockRoom();
				//log.getInput("Updated");
				break;
			default:
					log.getInput("Invalid");
					break;
				
			}
			
			log.getInput("Do you want to continue?(y/n)");
			s=k.next().charAt(0);
		}while(s=='y'||s=='Y');
		k.close();
	}
}

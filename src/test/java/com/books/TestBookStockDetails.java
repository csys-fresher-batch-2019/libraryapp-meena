package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.model.BookStockDetails;
import com.books.dao.impl.BookStockDetailsDAOImpl;
import com.books.logger.Logger;

public class TestBookStockDetails {
	private static final Logger log=Logger.getInstance();

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
			log.getInput("4.Total stocks");
			log.getInput("5.Indivdual Stocks");
			log.getInput("6.Delete stock");
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
			case 4:
				List<BookStockDetails>totalStocks=m.totalStocks();
				for (BookStockDetails totalStock : totalStocks)
				{
					log.getInput("ISBN Number:"+totalStock.isbnNo +""+"Total Stock:"+ totalStock.totalBooks);
				}
				
				break;
			case 5:
				List<BookStockDetails>individualRemaining=m.individualRemaining();
				
				for(BookStockDetails stock:individualRemaining)
				{
					log.getInput("ISBN Number:"+stock.isbnNo+""+"Remaining:"+stock.remaining);
				}
				List<BookStockDetails>individualIssued=m.individualIssued();
				for(BookStockDetails stocks:individualIssued)
				{
					log.getInput("ISBN Number:"+stocks.isbnNo+""+"Issued:"+stocks.issued);
				}
				break;
			case 6:
				log.getInput("Enter the book Id:");
				int bookId=k.nextInt();
				m.deleteStock(bookId);
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

package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.logger.Logger;
import com.books.model.BookStockDetails;
import com.books.service.BookStockDetailsService;
public class TestBookStockDetails {
	private static final Logger log=Logger.getInstance();

	public static void main(String[] args) throws Exception 
	{
		BookStockDetailsService ob=new BookStockDetailsService();
		
		BookStockDetails obj=new BookStockDetails();
		Scanner k=new Scanner(System.in);
		char s;
		
		do {
			log.getInput("Enter your choice");
			log.getInput("1.Display Stocks\n2.Insert new stocks\n3.Update stock room\n4.Total stocks\n5.Indivdual Stocks\n6.Delete stock");
			int ch=k.nextInt();
			switch(ch)
			{
			case 1:
				List<BookStockDetails>displayBookStockDetails=ob.displayBookStockDetails();
				for (BookStockDetails bookStockDetails : displayBookStockDetails)
				{
					log.getInput(bookStockDetails);
				}
				break;
			case 2:
				log.getInput("Enter the ISBN number:");
				obj.setIsbnNo(k.nextInt());
				ob.addNewStock(obj.getIsbnNo());
				break;
			case 3:
				ob.updateStockRoom();
				break;
			case 4:
				List<BookStockDetails>totalStocks=ob.totalStocks();
				for (BookStockDetails totalStock : totalStocks)
				{
					log.getInput("ISBN Number:"+totalStock.getIsbnNo() +""+"Total Stock:"+totalStock.getTotalBooks());
				}
				break;
			case 5:
				List<BookStockDetails>individualRemaining=ob.individualRemaining();
				for(BookStockDetails stock:individualRemaining)
				{
					log.getInput("ISBN Number:"+stock.getIsbnNo()+""+"Remaining:"+stock.getRemaining());
				}
				List<BookStockDetails>individualIssued=ob.individualIssued();
				for(BookStockDetails stocks:individualIssued)
				{
					log.getInput("ISBN Number:"+stocks.getIsbnNo()+""+"Issued:"+stocks.getIssued());
				}
				break;
			case 6:
				log.getInput("Enter the book Id:");
				int bookId=k.nextInt();
				ob.deleteStock(bookId);
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
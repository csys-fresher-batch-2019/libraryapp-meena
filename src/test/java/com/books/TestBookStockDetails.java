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
			System.out.println("Enter your choice");
			System.out.println("1.Display Stocks");
			System.out.println("2.Insert new stocks");
			System.out.println("3.Update stock room");
			int ch=k.nextInt();
			switch(ch)
			{
			case 1:
				List<BookStockDetails>displayBookStockDetails=m.displayBookStockDetails();
				for (BookStockDetails bookStockDetails : displayBookStockDetails)
				{
					System.out.println(bookStockDetails);
				}
				break;
			case 2:
				System.out.println("Enter the book id:");
				obj.bookId=k.nextInt();
				System.out.println("Enter the quantity:");
				obj.quantity=k.nextInt();
				//BookStockDetails omg=new BookStockDetails(bookId2,quantity2);
				m.insertBookStockDetails(obj.bookId,obj.quantity);
				break;
			case 3:
				m.updateStockRoom();
				System.out.println("Updated");
				break;
			default:
					System.out.println("Invalid");
					break;
				
			}
			
			System.out.println("Do you want to continue?(y/n)");
			s=k.next().charAt(0);
		}while(s=='y'||s=='Y');
		k.close();
	}
}

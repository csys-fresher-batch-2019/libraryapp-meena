package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.model.BookDetails;
import com.books.dao.impl.BookDetailsDAOImpl;

public class TestBookDetails
{

	//private static final int active = 1;

	public static void main(String args[])throws Exception
	{
		BookDetailsDAOImpl k=new BookDetailsDAOImpl();
		//BookDetails m=new BookDetails();
		//BookDetails obj=new BookDetails();
		BookDetails obj=new BookDetails();
		Scanner sc=new Scanner(System.in);
		char s;
		
		do {
			System.out.println("Enter your choice");
			
			System.out.println("1.Display book details");
			System.out.println("2.Insert book detail");
			System.out.println("3.Delete book detail");
			System.out.println("4.Display total books in library");
			int c=sc.nextInt();
			switch(c) 
			{
			case 1:
				List<BookDetails> displayBooks = k.displayBooks();
				for (BookDetails bookDetails : displayBooks) {
					System.out.println(bookDetails);
				}
				break;
			case 2:
				
				System.out.print("Enter BookName:");
				obj.bookName=sc.next();
			//	sc.nextLine();
				System.out.print("Enter Author Name:");
				obj.authorName=sc.next();
				//sc.nextLine();
				System.out.print("Enter publisher:");
				obj.publisher=sc.next();
				//sc.nextLine();
				System.out.print("Enter version:");
				obj.version=sc.nextInt();
				//sc.nextLine();
				System.out.print("Enter category:");
				obj.categories=sc.next();
				//sc.nextLine();
				System.out.print("Enter language:");
				obj.languages=sc.next();
				//sc.nextLine();
				//BookDetails obj=new BookDetails(bookName,authorName,publisher,version,category,language);
				System.out.println(obj);
				k.insertBookDetails(obj);
				break;
			case 3:
				System.out.print("Enter the book Id");
				int id=sc.nextInt();
				
				k.deleteBookDetails(id);
				break;
			case 4:
				int tot=k.totalBooks();
				System.out.println(tot);
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			System.out.println("Do you want to continue?y/n");
		     s=sc.next().charAt(0);
			
		}while(s=='y'||s=='Y');
		sc.close();
		}
	
}


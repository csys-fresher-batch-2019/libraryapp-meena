package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.model.BookDetails;
import com.books.dao.impl.BookDetailsDAOImpl;
import com.books.logger.Logger;

public class TestBookDetails
{
	private static final Logger log=Logger.getInstance();
	public static void main(String args[])throws Exception
	{
		BookDetailsDAOImpl k=new BookDetailsDAOImpl();
		//BookDetails m=new BookDetails();
		//BookDetails obj=new BookDetails();
		BookDetails obj=new BookDetails();
		Scanner sc=new Scanner(System.in);
		char s;
		log.getInput("Admin");
		log.getInput("Enter the email:");
		String admin=sc.next();
		log.getInput("Enter the password:");
		String password=sc.next();
		int status=k.checkAdmin(admin, password);
		if(status!=0)
		{
			do {
				log.getInput("Enter your choice");
				
				log.getInput("1.Display book details");
				log.getInput("2.Insert book detail");
				log.getInput("3.Delete book detail");
				log.getInput("4.Display total books in library");
				int c=sc.nextInt();
				switch(c) 
				{
				case 1:
					List<BookDetails> displayBooks = k.displayBooks();
					for (BookDetails bookDetails : displayBooks) {
						log.getInput(bookDetails);
					}
					break;
				case 2:
					log.getInput("Enter the ISBN number");
					obj.isbnNo=sc.nextInt();
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
					log.getInput(obj);
					k.insertBookDetails(obj);
					break;
				case 3:
					System.out.print("Enter the ISBN number");
					int id=sc.nextInt();
					
					k.deleteBookDetails(id);
					break;
				case 4:
					int tot=k.totalBooks();
					log.getInput(tot);
					break;
				default:
					log.getInput("Invalid choice");
					break;
				}
				log.getInput("Do you want to continue?y/n");
			     s=sc.next().charAt(0);
				
			}while(s=='y'||s=='Y');
		
		}
			sc.close();
		}
	
}


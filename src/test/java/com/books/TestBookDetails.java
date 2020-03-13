package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.logger.Logger;
import com.books.model.BookDetails;
import com.books.service.BookDetailsService;
import com.books.validator.BookDetailsValidator;

public class TestBookDetails
{
	private static final Logger log=Logger.getInstance();
	public static void main(String args[])throws Exception
	{
		BookDetailsService ob=new BookDetailsService();
		BookDetailsValidator v=new BookDetailsValidator();
		BookDetails obj=new BookDetails();
		Scanner sc=new Scanner(System.in);
		char s;
		log.getInput("Admin");
		log.getInput("Enter the email:");
		String admin=sc.next();
		log.getInput("Enter the password:");
		String password=sc.next();
		int status=ob.checkAdmin(admin, password);
		if(status!=0)
		{
			do {
				log.getInput("Enter your choice");
				log.getInput("1.Display book details\n2.Insert book detail\n3.Delete book detail\n4.Display total books in library");
				int c=sc.nextInt();
				switch(c) 
				{
				case 1:
					ob.updateStockRoom();
					List<BookDetails> displayBooks = ob.displayBooks();
					for (BookDetails bookDetails : displayBooks) {
						log.getInput(bookDetails);
					}
					break;
				case 2:
					log.getInput("Enter the ISBN number");
					obj.setIsbnNo(sc.nextInt());
					boolean validNumber=v.checkIsbnNo(obj.getIsbnNo());
					log.getInput("Enter BookName:");
					obj.setBookName(sc.next());
					boolean validName=v.checkName(obj.getBookName());
					log.getInput("Enter Author Name:");
					obj.setAuthorName(sc.next());
					boolean validAuthorName=v.checkName(obj.getAuthorName());
					log.getInput("Enter publisher:");
					obj.setPublisher(sc.next());
					log.getInput("Enter version:");
					obj.setVersion(sc.nextInt());
					log.getInput("Enter category:");
					obj.setCategories(sc.next());
					log.getInput("Enter language:");
					obj.setLanguages(sc.next());
					log.getInput(obj);
					if(validName==true && validNumber==true && validAuthorName==true) {
						int row=ob.addNewBook(obj);	
						log.getInput(row);
					}
					
					break;
				case 3:
					log.getInput("Enter the ISBN number");
					int id=sc.nextInt();
						ob.deleteBookDetails(id);
					break;
				case 4:
					int tot=ob.totalBooks();
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

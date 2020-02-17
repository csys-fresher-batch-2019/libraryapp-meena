package com.books;


import java.util.List;
import java.util.Scanner;

import com.books.dao.impl.UserDAOImpl;
import com.books.logger.Logger;
import com.books.model.BookDetails;
import com.books.model.CalcCard;
import com.books.model.User;

public class TestUser {
	private static final Logger log=Logger.getInstance();
	public static void main(String[] args) throws Exception {
		UserDAOImpl m=new UserDAOImpl();
		
		Scanner sc=new Scanner(System.in);
		char s;
		log.getInput("Enter the email");
		String email=sc.next();
		log.getInput("Enter the password");
		String password=sc.next();
		
		int userId=m.checkLogin(email, password);
		System.out.println(userId);
		if(userId!=0)
		{
			do
			{
				log.getInput("Enter your choice:");
				log.getInput("1.History");
				log.getInput("2.Current Books");
				log.getInput("3.Search Books by Book Name");
				log.getInput("4.Search Books by Author Name");
				log.getInput("5.Availablity cards");
				log.getInput("6.Settings");
				log.getInput("7.Due check");
				
				int ch=sc.nextInt();
				switch(ch)
				{
				case 1:
					List<User>user=m.viewHistory(userId);
					for(User users:user)
					{
						log.getInput(users);
					}
					break;
				case 2:
					List<User>users=m.currentBooks(userId);
					for(User user1:users)
					{
						log.getInput(user1);
					}
					break;
				case 3:
					log.getInput("Enter the book Name");
					String bookName=sc.next();
					List<BookDetails>bookDetails=m.searchBookName(bookName);
					for(BookDetails book:bookDetails)
					{
						log.getInput(book);
					}
					break;
				case 4:
				
					log.getInput("Enter the author Name");
					String authorName=sc.next();
					List<BookDetails>book=m.searchBookName(authorName);
					for(BookDetails books:book)
					{
						log.getInput(books);
					}
					break;
				case 5:
					int status=m.checkAvailable(userId);
					if(status!=0)
					{
						List<CalcCard>card=m.remainingCard(userId);
						for(CalcCard cards:card)
						{
							log.getInput("Taken Cards:"+cards.takenBooks+""+"Remaining:"+cards.remaining);
						}
						
					}
					break;
				case 6:
					log.getInput("1.Change Phone number");
					log.getInput("2.Change address");
					log.getInput("3.Change password");
					int choice=sc.nextInt();
					switch(choice) {
					case 1:
						log.getInput("Enter your new phone number:");
						long phoneNumber=sc.nextLong();
						m.updatePhoneNumber(userId, phoneNumber);
						break;
					case 2:
						log.getInput("Enter the address:");
						String address=sc.next();
						m.updateAddress(userId, address);
						break;
					case 3:
						log.getInput("Enter your new Password:");
						String password1=sc.next();
						m.changePassword(userId, password1);
						break;
					default:
						log.getInput("Invalid choice");
						break;
					}
					break;
				case 7:
					
					List<User>dueBooks=m.dueDateCheck(userId);
					for(User dueBook:dueBooks)
					{
						log.getInput(dueBook);
					}
					break;
					
				default:
					log.getInput("Invalid Choice");
					break;
				}
				log.getInput("Do you want to continue?(Y/N)");
				s=sc.next().charAt(0);
			}while(s=='Y'||s=='y');
			
			
		}
		
		
		sc.close();

	}

}

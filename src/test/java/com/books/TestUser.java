package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.logger.Logger;
import com.books.model.BookDetails;
import com.books.model.CalcCard;
import com.books.model.User;
import com.books.service.UserService;

public class TestUser {
	private static final Logger log = Logger.getInstance();

	public static void main(String[] args) throws Exception {
		UserService ob = new UserService();
		
		Scanner sc = new Scanner(System.in);
		char s;
		log.getInput("Enter the email");
		String email = sc.next();
		log.getInput("Enter the password");
		String password = sc.next();
		int userId = ob.checkLogin(email, password);
		System.out.println(userId);
		if (userId != 0) {
			do {
				log.getInput("Enter your choice:");
				log.getInput(
						"1.History\n2.Current Books\n3.Search Books by Book Name\n4.Search Books by Author Name\n5.Availablity cards\n6.Settings\n7.Due check");
				int ch = sc.nextInt();
				switch (ch) {
				case 1:
					List<User> user = ob.viewHistory(userId);
					for (User users : user) {
						log.getInput(users);
					}
					break;
				case 2:
					List<User> users = ob.currentBooks(userId);
					for (User user1 : users) {
						log.getInput(user1);
					}
					break;
				case 3:
					log.getInput("Enter the book Name");
					String bookName = sc.next();
					List<BookDetails> bookDetails = ob.searchBookName(bookName);
					for (BookDetails book : bookDetails) {
						log.getInput(book);
					}
					break;
				case 4:
					log.getInput("Enter the author Name");
					String authorName = sc.next();
					List<BookDetails> book = ob.searchAuthorName(authorName);
					for (BookDetails books : book) {
						log.getInput(books);
					}
					break;
				case 5:
					int status = ob.checkAvailable(userId);
					if (status != 0) {
						List<CalcCard> card = ob.remainingCard(userId);
						for (CalcCard cards : card) {
							log.getInput(
									"Taken Cards:" + cards.getTakenBooks() + "" + "Remaining:" + cards.getRemaining());
						}
					}
					break;
				case 6:
					log.getInput("1.Change Phone number\n2.Change address\n3.Change password");
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						log.getInput("Enter your new phone number:");
						long phoneNumber = sc.nextLong();
						ob.updatePhoneNumber(userId, phoneNumber);
						break;
					case 2:
						log.getInput("Enter the address:");
						String address = sc.next();
						ob.updateAddress(userId, address);
						break;
					case 3:
						log.getInput("Enter your new Password:");
						String password1 = sc.next();
						ob.changePassword(userId, password1);
						break;
					default:
						log.getInput("Invalid choice");
						break;
					}
					break;
				case 7:
					List<User> dueBooks = ob.dueDateCheck(userId);
					for (User dueBook : dueBooks) {
						log.getInput(dueBook);
					}
					break;
				default:
					log.getInput("Invalid Choice");
					break;
				}
				log.getInput("Do you want to continue?(Y/N)");
				s = sc.next().charAt(0);
			} while (s == 'Y' || s == 'y');
		}
		sc.close();
	}
}
package com.books;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.books.dao.impl.BookStockDetailsDAOImpl;
import com.books.dao.impl.PenalityCalcDAOImpl;
import com.books.logger.Logger;
import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.CategorySettings;
import com.books.model.LanguageSettings;
import com.books.model.PenalityCalc;

public class TestPenalityCalc {
	private static final Logger log=Logger.getInstance();
	public static void main(String[] args) throws Exception {
		PenalityCalcDAOImpl m=new PenalityCalcDAOImpl();
		BookStockDetailsDAOImpl b=new BookStockDetailsDAOImpl();
		char s;
		Scanner k=new Scanner(System.in);
		do
		{
			log.getInput("Enter your choice");
			log.getInput("1.Update returned status\n2.Display Fine\n");
			log.getInput("Settings\n");
			log.getInput("3.Set Book Limit\n4.Set Penality\n5.Set Count of Due Days\n");
			log.getInput("6.Find count of each book\n7.Availablity cards for the each user\n8.Insert user-book details\n");
			log.getInput("Language Settings\n");
			log.getInput("9.Insert new language\n10.Delete languages\n11.Display languages\n");
			log.getInput("Category Settings\n");
			log.getInput("12.Insert new category\n13.delete category\n14.Display categories\n15.set Popup");
			int ch=k.nextInt();
			switch(ch)
			{
			case 1:
				log.getInput("Enter the user id");
				int userId=k.nextInt();
				log.getInput("Enter the book id");
				int bookId=k.nextInt();
				log.getInput("Enter the returned date");
				String returnedDate=k.next();
				Date date=Date.valueOf(returnedDate);
				int row=m.updateReturnStatus(bookId,userId,date);
			case 2:
				List<PenalityCalc>display=m.displayFineDetails();
				for (PenalityCalc penalityCalc : display)
				{
					log.getInput(penalityCalc);
				}
				break;
			case 3:
				log.getInput("Enter the Limit:");
				int count=k.nextInt();
				m.setBookLimit(count);
				break;
			case 4:
				log.getInput("Enter the penality amount per day:");
				int count1=k.nextInt();
				m.setPenality(count1);
				m.updateFineAll();
				break;
			case 5:
				log.getInput("Enter the count of due days:");
				int count2=k.nextInt();
				m.setDueDays(count2);
				m.updateDueDateAll();
				break;
			case 6:
				List<Additional> displaycount=m.booksCount();
				for (Additional additional : displaycount)
				{
					log.getInput(additional);
				}
				break;
			case 7:
				List<CalcCard>displayCard=m.userCardCount();
				for(CalcCard calcCard:displayCard)
				{
					log.getInput(calcCard);
				}
				break;
			case 8:
				log.getInput("Enter the user id");
				int userId2=k.nextInt();
				log.getInput("Enter the book id");
				int bookId2=k.nextInt();
				log.getInput("Enter the issued date");
				String issuedDate=k.next();
				LocalDate issuedDate1=LocalDate.parse(issuedDate);
				Date rd=Date.valueOf(issuedDate1);
				int row1=m.insertUserBookDetails(bookId2,userId2,rd);
				m.updateDueDate(bookId2,userId2);
				m.calculateFineAmount(bookId2,userId2);
				b.updateActive(bookId2);
				break;
			case 9:
				log.getInput("Enter the language");
				String language=k.next();
				m.insertNewLanguage(language);
				break;
			case 10:
				log.getInput("Enter the language to delete");
				String language1=k.next();
				m.deleteLanguage(language1);
				break;
			case 11:
				List<LanguageSettings>displayLanguage=m.displayLanguages();
				for(LanguageSettings languages:displayLanguage)
				{
					log.getInput(languages);
				}
				break;
			case 12:
				log.getInput("Enter the category");
				String category=k.next();
				m.insertNewCategory(category);
				break;
			case 13:
				log.getInput("Enter the category");
				String category1=k.next();
				m.deteleCategory(category1);
				break;
			case 14:
				List<CategorySettings>displayCategory=m.displayCategories();
				for(CategorySettings categories:displayCategory)
				{
					log.getInput(categories);
				}
				break;
			case 15:
				int popup=k.nextInt();
				m.updatePopup(popup);
			default:
				log.getInput("Invalid choice");
				break;
			}
			log.getInput("Do you want to continue(Y/N)");
			s=k.next().charAt(0);
		}while(s=='y'||s=='Y');
		k.close();
	}
}
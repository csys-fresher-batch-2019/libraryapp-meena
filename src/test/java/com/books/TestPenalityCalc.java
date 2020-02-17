package com.books;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.CategorySettings;
import com.books.model.LanguageSettings;
import com.books.model.PenalityCalc;
import com.books.dao.impl.BookStockDetailsDAOImpl;
import com.books.dao.impl.PenalityCalcDAOImpl;
import com.books.logger.Logger;

public class TestPenalityCalc {
	private static final Logger log=Logger.getInstance();
	public static void main(String[] args) throws Exception {
		PenalityCalcDAOImpl m=new PenalityCalcDAOImpl();
		BookStockDetailsDAOImpl b=new BookStockDetailsDAOImpl();
		//PenalityCalc obj=new PenalityCalc();
		//Additional obj1=new Additional();
		char s;
		Scanner k=new Scanner(System.in);
		do
		{
			log.getInput("Enter your choice");
			//log.getInput("1.Calculate Fine Amount");
			log.getInput("2.Update returned status");
			log.getInput("3.Display Fine");
			//log.getInput("4.Update Due Date\n");
			log.getInput("Settings\n");
			log.getInput("5.Set Book Limit");
			log.getInput("6.Set Penality");
			log.getInput("7.Set Count of Due Days");
			log.getInput("8.Find count of each book");
			log.getInput("9.Availablity cards for the each user");
			log.getInput("10.Insert user-book details\n");
			log.getInput("Language Settings\n");
			log.getInput("11.Insert new language");
			log.getInput("12.Delete languages");
			log.getInput("13.Display languages\n");
			log.getInput("Category Settings\n");
			log.getInput("14.Insert new category");
			log.getInput("15.delete category");
			log.getInput("16.Display categories");
			log.getInput("17.set Popup");
			int ch=k.nextInt();
			switch(ch)
			{
			case 1:
				//m.calculateFineAmount();
				break;
			case 2:
				log.getInput("Enter the user id");
				int userId=k.nextInt();
				log.getInput("Enter the book id");
				int bookId=k.nextInt();
				log.getInput("Enter the returned date");
				String returnedDate=k.next();
				LocalDate returnedDate1=LocalDate.parse(returnedDate);
				//Date.=Date.valueOf(returnedDate)
				m.updateReturnStatus(bookId,userId,returnedDate1);
			case 3:
				List<PenalityCalc>display=m.displayFineDetails();
				for (PenalityCalc penalityCalc : display)
				{
					log.getInput(penalityCalc);
				}
				//m.displayFineDetails();
				break;
			case 4:
				//m.updateDueDate();
				break;
			case 5:
				log.getInput("Enter the Limit:");
				int count=k.nextInt();
				m.setBookLimit(count);
				break;
			case 6:
				log.getInput("Enter the penality amount per day:");
				int count1=k.nextInt();
				m.setPenality(count1);
				m.updateFineAll();
				break;
			case 7:
				log.getInput("Enter the count of due days:");
				int count2=k.nextInt();
				m.setDueDays(count2);
				m.updateDueDateAll();
				break;
			case 8:
				
				List<Additional> displaycount=m.booksCount();
				for (Additional additional : displaycount)
				{
					log.getInput(additional);
				}
				break;
			case 9:
				List<CalcCard>displayCard=m.userCardCount();
				for(CalcCard calcCard:displayCard)
				{
					log.getInput(calcCard);
				}
				break;
			case 10:
				log.getInput("Enter the user id");
				int userId2=k.nextInt();
				log.getInput("Enter the book id");
				int bookId2=k.nextInt();
				log.getInput("Enter the issued date");
				String issuedDate=k.next();
				LocalDate issuedDate1=LocalDate.parse(issuedDate);
				Date rd=Date.valueOf(issuedDate1);
				m.insertUserBookDetails(bookId2,userId2,rd);
				m.updateDueDate(bookId2,userId2);
				m.calculateFineAmount(bookId2,userId2);
				b.updateActive(bookId2);
				
				
				break;
			case 11:
				log.getInput("Enter the language");
				String language=k.next();
				m.insertNewLanguage(language);
				break;
			case 12:
				log.getInput("Enter the language to delete");
				String language1=k.next();
				m.deleteLanguage(language1);
				break;
			case 13:
				List<LanguageSettings>displayLanguage=m.displayLanguages();
				for(LanguageSettings languages:displayLanguage)
				{
					log.getInput(languages);
				}
				break;
			case 14:
				log.getInput("Enter the category");
				String category=k.next();
				m.insertNewCategory(category);
				break;
			case 15:
				log.getInput("Enter the category");
				String category1=k.next();
				m.deteleCategory(category1);
				break;
			case 16:
				List<CategorySettings>displayCategory=m.displayCategories();
				for(CategorySettings categories:displayCategory)
				{
					log.getInput(categories);
				}
				break;
			case 17:
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

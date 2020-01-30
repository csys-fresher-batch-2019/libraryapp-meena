package com.books;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.books.model.Additional;
import com.books.model.CalcCard;
import com.books.model.PenalityCalc;
import com.books.dao.impl.PenalityCalcDAOImpl;

public class TestPenalityCalc {

	public static void main(String[] args) throws Exception {
		PenalityCalcDAOImpl m=new PenalityCalcDAOImpl();
		//PenalityCalc obj=new PenalityCalc();
		//Additional obj1=new Additional();
		char s;
		Scanner k=new Scanner(System.in);
		do
		{
			System.out.println("Enter your choice");
			System.out.println("1.Calculate Fine Amount");
			System.out.println("2.Update returned status");
			System.out.println("3.Display Fine");
			System.out.println("4.Update Due Date");
			System.out.println("5.Set Book Limit");
			System.out.println("6.Set Penality");
			System.out.println("7.Set Count of Due Days");
			System.out.println("8.Find count of each book");
			System.out.println("9.Availablity cards for the each user");
			System.out.println("10.Insert user-book details");
			int ch=k.nextInt();
			switch(ch)
			{
			case 1:
				m.calculateFineAmount();
				break;
			case 2:
				System.out.println("Enter the user id");
				int userId=k.nextInt();
				System.out.println("Enter the book id");
				int bookId=k.nextInt();
				System.out.println("Enter the returned date");
				String returnedDate=k.next();
				LocalDate returnedDate1=LocalDate.parse(returnedDate);
				//Date.=Date.valueOf(returnedDate)
				m.updateReturnStatus(bookId,userId,returnedDate1);
			case 3:
				List<PenalityCalc>display=m.displayFineDetails();
				for (PenalityCalc penalityCalc : display)
				{
					System.out.println(penalityCalc);
				}
				//m.displayFineDetails();
				break;
			case 4:
				m.updateDueDate();
				break;
			case 5:
				System.out.println("Enter the Limit:");
				int count=k.nextInt();
				m.setBookLimit(count);
				break;
			case 6:
				System.out.println("Enter the penality amount per day:");
				int count1=k.nextInt();
				m.setPenality(count1);
				break;
			case 7:
				System.out.println("Enter the count of due days:");
				int count2=k.nextInt();
				m.setDueDays(count2);
				break;
			case 8:
				
				List<Additional> displaycount=m.booksCount();
				for (Additional additional : displaycount)
				{
					System.out.println(additional);
				}
				break;
			case 9:
				List<CalcCard>displayCard=m.userCardCount();
				for(CalcCard calcCard:displayCard)
				{
					System.out.println(calcCard);
				}
				break;
			case 10:
				System.out.println("Enter the user id");
				int userId2=k.nextInt();
				System.out.println("Enter the book id");
				int bookId2=k.nextInt();
				System.out.println("Enter the issued date");
				String issuedDate=k.next();
				LocalDate issuedDate1=LocalDate.parse(issuedDate);
				Date rd=Date.valueOf(issuedDate1);
				m.insertUserBookDetails(userId2,bookId2,rd);
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			System.out.println("Do you want to continue(Y/N)");
			s=k.next().charAt(0);
		}while(s=='y'||s=='Y');

		k.close();
	}

}

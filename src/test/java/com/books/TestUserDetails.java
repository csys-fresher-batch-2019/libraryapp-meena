package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.model.UserDetails;
import com.books.dao.impl.*;
public class TestUserDetails {

	public static void main(String[] args) throws Exception
	{
		UserDetailsDAOImpl m=new UserDetailsDAOImpl();		
		Scanner t=new Scanner(System.in);
		char k;
		
		do {
			log.getInput("Enter your choice");
			log.getInput("1.Enter the user details");
			log.getInput("2.Display user details");
			log.getInput("3.Delete user details");
			int ch=t.nextInt();
			
			switch(ch){
			case 1:
				
				log.getInput("Enter the user name:");
				String userName=t.next();
				log.getInput("Enter the address:");
				String address=t.next();
				log.getInput("Enter the phone number:");
				long phno=t.nextLong();
				log.getInput("Enter the email id");
				String email=t.next();
				log.getInput("Enter the password");
				String password=t.next();
				log.getInput("Choose the gender:(M/F)");
				String gender=t.next();
				UserDetails userDetails=new UserDetails(userName,address,phno,email,password,gender);
				m.insertUserDetails(userDetails);
				break;
			case 2:
				List<UserDetails> displayUserDetails=m.displayUserDetails();
				for (UserDetails userdetails : displayUserDetails) 
				{
					log.getInput(userdetails);
				}
				break;
			case 3:
				log.getInput("Enter the user Id");
				int userId=t.nextInt();
				m.deleteUserDetails( userId);
				break;
			default:
				log.getInput("Invalid option");
				break;
			}
			log.getInput("Do you want to continue (y/n)");
			 k=t.next().charAt(0);

		}while(k=='y'||k=='Y');
	t.close();			
	}
}
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
			System.out.println("Enter your choice");
			System.out.println("1.Enter the user details");
			System.out.println("2.Display user details");
			System.out.println("3.Delete user details");
			int ch=t.nextInt();
			
			switch(ch){
			case 1:
				
				System.out.println("Enter the user name:");
				String userName=t.next();
				System.out.println("Enter the address:");
				String address=t.next();
				System.out.println("Enter the phone number:");
				long phno=t.nextLong();
				UserDetails userDetails=new UserDetails(userName,address,phno);
				m.insertUserDetails(userDetails);
				break;
			case 2:
				List<UserDetails> displayUserDetails=m.displayUserDetails();
				for (UserDetails userdetails : displayUserDetails) 
				{
					System.out.println(userdetails);
				}
				break;
			case 3:
				System.out.println("Enter the user Id");
				int userId=t.nextInt();
				m.deleteUserDetails( userId);
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
			System.out.println("Do you want to continue (y/n)");
			 k=t.next().charAt(0);

		}while(k=='y'||k=='Y');
	t.close();			
	}
}
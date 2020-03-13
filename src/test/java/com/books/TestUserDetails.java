package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.logger.Logger;
import com.books.model.UserDetails;
import com.books.service.UserDetailsService;
import com.books.validator.BookDetailsValidator;
import com.books.validator.UserDetailsValidator;

public class TestUserDetails {
	private static final Logger log = Logger.getInstance();

	public static void main(String[] args) throws Exception {
		UserDetailsService ob = new UserDetailsService();
		BookDetailsValidator val=new BookDetailsValidator();
		UserDetailsValidator userVal=new UserDetailsValidator();
		Scanner t = new Scanner(System.in);
		char k;

		do {
			log.getInput("Enter your choice");
			log.getInput("1.Enter the user details\n2.Display user details\n3.Delete user details");
			int ch = t.nextInt();
			switch (ch) {
			case 1:
				log.getInput("Enter the user name:");
				String userName = t.next();
				boolean validateName=val.checkName(userName);
				log.getInput("Enter the address:");
				String address = t.next();
				log.getInput("Enter the phone number:");
				long phno = t.nextLong();
				boolean validatePhoneNumber=userVal.checkPhoneNumber(phno);
				log.getInput("Enter the email id");
				String email = t.next();
				boolean validateEmail=userVal.checkEmail(email);
				log.getInput("Enter the password");
				String password = t.next();
				log.getInput("Choose the gender:(M/F)");
				String gender = t.next();
				UserDetails userDetails = new UserDetails(userName, address, phno, email, password, gender);
				if(validateName==true&&validateEmail==true&&validatePhoneNumber==true)
				{
					int row = ob.insertUserDetails(userDetails);
					log.getInput(row);
				}
				break;
			case 2:
				List<UserDetails> displayUserDetails = ob.displayUserDetails();
				for (UserDetails userdetails : displayUserDetails) {
					log.getInput(userdetails);
				}
				break;
			case 3:
				log.getInput("Enter the user Id");
				int userId = t.nextInt();
				int row1 = ob.deleteUserDetails(userId);
				log.getInput(row1);
				break;
			default:
				log.getInput("Invalid option");
				break;
			}
			log.getInput("Do you want to continue (y/n)");
			k = t.next().charAt(0);
		} while (k == 'y' || k == 'Y');
		t.close();
	}
}
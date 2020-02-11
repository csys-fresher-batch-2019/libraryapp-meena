package com.books;

import java.util.Scanner;

import com.books.dao.impl.UserDAOImpl;

public class TestUser {

	public static void main(String[] args) throws Exception {
		UserDAOImpl m=new UserDAOImpl();
		Scanner sc=new Scanner(System.in);
		log.getInput("Enter the username");
		String email=sc.next();
		log.getInput("Enter the password");
		String password=sc.next();
		m.checkLogin(email, password);
		
		
		sc.close();

	}

}

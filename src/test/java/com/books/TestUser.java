package com.books;

import java.util.List;
import java.util.Scanner;

import com.books.model.User;
import com.books.dao.impl.UserDAOImpl;

public class TestUser {

	public static void main(String[] args) throws Exception {
		UserDAOImpl m=new UserDAOImpl();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the user id");
		int userId=sc.nextInt();
		List<User>display=m.displayBooks(userId);
		for(User user:display)
		{
			System.out.println(user);
		}
		
		sc.close();

	}

}

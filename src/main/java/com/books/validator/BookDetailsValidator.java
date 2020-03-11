package com.books.validator;

import com.books.exception.ValidatorException;

public class BookDetailsValidator {
	public boolean checkBookName(String bookName)throws ValidatorException
	{
		boolean comments=true; 
		if(!bookName.matches("^[a-z0-9_-]{3,15}$"))
		{
			comments=false;
			throw new ValidatorException("Invalid Book Name");
		}
		else {
			return comments;
		}
	}
}

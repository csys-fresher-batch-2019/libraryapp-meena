package com.books.validator;

import com.books.exception.ValidatorException;

public class BookDetailsValidator {
	public void checkBookName(String bookName)throws ValidatorException
	{
		if(!bookName.matches("^[a-z0-9_-]{3,15}$"))
		{
			throw new ValidatorException("Invalid Book Name");
		}
	}
}

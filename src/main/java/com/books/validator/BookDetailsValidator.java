package com.books.validator;

import com.books.exception.ValidatorException;

public class BookDetailsValidator {
	public boolean checkName(String name) throws ValidatorException {
		boolean comments = false;
		if (!name.matches("^[A-Za-z_-]{3,30}$")) {
			throw new ValidatorException("Invalid Name");
		} else {
			comments = true;
		}
		return (comments);
	}

	public boolean checkIsbnNo(int number) throws ValidatorException {
		boolean status = false;
		if (number <= 0) {
			throw new ValidatorException("Isbn number should be a valid one");
		} else {
			status = true;
			return (status);
		}
	}

}
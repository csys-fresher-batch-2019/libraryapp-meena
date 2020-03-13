package com.books.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.books.exception.ValidatorException;

public class UserDetailsValidator {

	public boolean checkEmail(String email) throws ValidatorException {
		boolean status = false;

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!email.matches(regex)) {
			throw new ValidatorException("Invalid mail id");
		} else {
			status = true;
		}
		return status;
	}

	public boolean checkPhoneNumber(Long phoneNumber) throws ValidatorException {
		boolean status = false;
		Pattern p = Pattern.compile("[6-9][0-9]{9}");
		Matcher match = p.matcher(phoneNumber + "");
		if (match.find() && match.group().equals(phoneNumber + "")) {
			status = true;
		} else {
			throw new ValidatorException("Invalid Phone Number");
		}
		return status;
	}
}

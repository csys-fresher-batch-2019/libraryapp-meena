package com.books.model;

public class LanguageSettings {
	public String language;
	public int active=1;
	
	@Override
	public String toString() {
		return "LanguageSettings [language=" + language + "]";
	}

	public LanguageSettings(String language) {
		this.language=language;
		// TODO Auto-generated constructor stub
	}
	

}

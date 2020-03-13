package com.books.dto;

public class LanguageSettings {

	private String language;
	private int active = 1;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "LanguageSettings [language=" + language + "]";
	}

	public LanguageSettings(String language) {
		this.language = language;
	}
}
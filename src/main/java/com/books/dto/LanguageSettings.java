package com.books.dto;

public class LanguageSettings {
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

	private String language;
	private int active = 1;

	@Override
	public String toString() {
		return "LanguageSettings [language=" + language + "]";
	}

	public LanguageSettings(String language) {
		this.language = language;
	}
}
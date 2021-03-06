package com.books.dto;

public class CategorySettings {
	private String category;
	private int active = 1;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "CategorySettings [category=" + category + "]";
	}

	public CategorySettings(String category) {
		super();
		this.category = category;
	}
}
package com.books.model;

public class CategorySettings
{
	public String category;
	public int active=1;
	@Override
	public String toString() {
		return "CategorySettings [category=" + category + "]";
	}
	public CategorySettings(String category) {
		super();
		this.category = category;
	}

}

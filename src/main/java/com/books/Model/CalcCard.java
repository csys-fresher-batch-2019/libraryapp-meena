package com.books.model;

public class CalcCard {
	public int userId;
	public int takenBooks;
	public int remaining;
	
	public CalcCard(int userId, int takenBooks, int remaining) {
		super();
		this.userId = userId;
		this.takenBooks = takenBooks;
		this.remaining = remaining;
	}

	public CalcCard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CalcCard [userId=" + userId + ", takenBooks=" + takenBooks + ", remaining=" + remaining + "]";
	}

}

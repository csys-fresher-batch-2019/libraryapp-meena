package com.books.Model;

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

	@Override
	public String toString() {
		return "CalcCard [userId=" + userId + ", takenBooks=" + takenBooks + ", remaining=" + remaining + "]";
	}

}

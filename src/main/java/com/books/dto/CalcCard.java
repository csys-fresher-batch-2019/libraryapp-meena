package com.books.dto;

public class CalcCard {
	private int userId;
	private int takenBooks;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTakenBooks() {
		return takenBooks;
	}

	public void setTakenBooks(int takenBooks) {
		this.takenBooks = takenBooks;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	private int remaining;

	public CalcCard(int userId, int takenBooks, int remaining) {
		super();
		this.userId = userId;
		this.takenBooks = takenBooks;
		this.remaining = remaining;
	}

	public CalcCard() {
	}

	@Override
	public String toString() {
		return "CalcCard [userId=" + userId + ", takenBooks=" + takenBooks + ", remaining=" + remaining + "]";
	}
}
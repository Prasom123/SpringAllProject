package com.heraizen.cj.domain;

import java.util.Arrays;

public class Team {
	private String title;
	private String isbn;

	
	@Override
	public String toString() {
		return "Team [title=" + title + ", isbn=" + isbn + ", year=" + year + ", authors=" + Arrays.toString(authors)
				+ "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public long getYear() {
		return year;
	}

	public Team(String title, String isbn, long year, String[] authors) {
		this.title = title;
		this.isbn = isbn;
		this.year = year;
		this.authors = authors;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	private long year;
	private String[] authors;
}

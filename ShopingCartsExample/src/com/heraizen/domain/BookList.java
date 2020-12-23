package com.heraizen.domain;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookList {
	private String author;
	private String country;
	private String imageLink;
	private String language;
	private String link;
	private double pages;
	private String title;
	private double year;

}

package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection="Book-record")
public class Book {
	@Id
	private String bookId;
	
	@NonNull
	private String name;
	
	@NonNull
	private String summary;
	
	private int rating;
	
	public String getBookId() {
		return bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Book(String bookId, String name, String summary, int rating) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.summary = summary;
		this.rating = rating;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	

	

}
package com.springboot.library.view;

import java.io.Serializable;
import java.util.Set;

import com.springboot.library.entity.LibLoanBook;
import com.springboot.library.entity.LibRequestBook;
import com.springboot.library.entity.LibReturnBook;

public class LibBookView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3091431348456117943L;

	private long id;

	private String author;

	private String genre;

	private String issbn;

	private Long rentalPrice;

	private String status;

	private String title;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIssbn() {
		return issbn;
	}

	public void setIssbn(String issbn) {
		this.issbn = issbn;
	}

	public Long getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(Long rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

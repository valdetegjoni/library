package com.springboot.library.request;


import javax.validation.constraints.NotNull;
import com.springboot.library.base.BaseRequest;


public class BookRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037052763137502351L;

	

	@NotNull
	private String author;

	@NotNull
	private String genre;

	@NotNull
	private String issbn;

	@NotNull
	private Long rentalPrice;

	private String status;

	@NotNull
	private String title;

	
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

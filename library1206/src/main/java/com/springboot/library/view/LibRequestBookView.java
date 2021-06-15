package com.springboot.library.view;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LibRequestBookView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -593941230481240388L;

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date requestDate;

	private LibBookView libBook;

	private LibCustomerView libCustomer;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public LibBookView getLibBook() {
		return libBook;
	}

	public void setLibBook(LibBookView libBook) {
		this.libBook = libBook;
	}

	public LibCustomerView getLibCustomer() {
		return libCustomer;
	}

	public void setLibCustomer(LibCustomerView libCustomer) {
		this.libCustomer = libCustomer;
	}


}

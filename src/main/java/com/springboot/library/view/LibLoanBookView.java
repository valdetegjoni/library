package com.springboot.library.view;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;


public class LibLoanBookView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8615080551239361784L;

	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date issuedDate;

	private LibBookView libBook;

	private LibCustomerView libCustomer;

	private LibEmployeeView libEmployeeIssued;
	
	private LibEmployeeView libEmployeeReceived;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
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

	public LibEmployeeView getLibEmployeeIssued() {
		return libEmployeeIssued;
	}

	public void setLibEmployeeIssued(LibEmployeeView libEmployeeIssued) {
		this.libEmployeeIssued = libEmployeeIssued;
	}

	public LibEmployeeView getLibEmployeeReceived() {
		return libEmployeeReceived;
	}

	public void setLibEmployeeReceived(LibEmployeeView libEmployeeReceived) {
		this.libEmployeeReceived = libEmployeeReceived;
	}

	

	

}

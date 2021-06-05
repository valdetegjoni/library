package com.springboot.library.view;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibEmployee;

public class LibReturnBookView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2118958766942875199L;

	private Long id;

	private Boolean hasFine;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date returnDate;

	private LibBookView libBook;

	private LibCustomerView libCustomer;

	private LibEmployeeView libEmployee;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getHasFine() {
		return hasFine;
	}

	public void setHasFine(Boolean hasFine) {
		this.hasFine = hasFine;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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

	public LibEmployeeView getLibEmployee() {
		return libEmployee;
	}

	public void setLibEmployee(LibEmployeeView libEmployee) {
		this.libEmployee = libEmployee;
	}

	

}

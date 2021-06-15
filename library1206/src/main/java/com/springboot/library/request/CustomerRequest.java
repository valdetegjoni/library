package com.springboot.library.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.library.base.BaseRequest;

public class CustomerRequest extends BaseRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String address;

	@NotNull
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	private String password;

	@NotNull
	private long phone;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date registrationDate;

	private List<@Valid Id> libBooksReturned;
	private List<@Valid Id> libBooksRequest;
	private List<@Valid Id> libLoanBooks;

	public List<Id> getLibLoanBooks() {
		return libLoanBooks;
	}

	public void setLibLoanBooks(List<Id> libLoanBooks) {
		this.libLoanBooks = libLoanBooks;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<Id> getLibBooksReturned() {
		return libBooksReturned;
	}

	public void setLibBooksReturned(List<Id> libBooksReturned) {
		this.libBooksReturned = libBooksReturned;
	}

	public List<Id> getLibBooksRequest() {
		return libBooksRequest;
	}

	public void setLibBooksRequest(List<Id> libBooksRequest) {
		this.libBooksRequest = libBooksRequest;
	}

}

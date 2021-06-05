package com.springboot.library.view;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibLoanBook;
import com.springboot.library.entity.LibRequestBook;
import com.springboot.library.entity.LibReturnBook;

public class LibCustomerView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 548646021336958668L;

	private Long id;

	private String address;

	private String email;

	private String name;

	private String password;

	private Long phone;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date registrationDate;

	private String surname;

	private Set<LibBookView> libBooksReturned;
	
	private Set<LibBookView> libBooksRequest;
	
	public Set<LibBookView> getLibBooksReturned() {
		return libBooksReturned;
	}

	public void setLibBooksReturned(Set<LibBookView> libBooksReturned) {
		this.libBooksReturned = libBooksReturned;
	}

	public Set<LibBookView> getLibBooksRequest() {
		return libBooksRequest;
	}

	public void setLibBooksRequest(Set<LibBookView> libBooksRequest) {
		this.libBooksRequest = libBooksRequest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	


	


}

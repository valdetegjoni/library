package com.springboot.library.view;

import java.io.Serializable;
import java.util.Set;

import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibLoanBook;
import com.springboot.library.entity.LibReturnBook;

public class LibEmployeeView implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7732864009411212774L;

	private Long id;

	private String address;

	private Integer deskNr;

	private String email;

	private String name;

	private Set<LibBookView> libBooksLoaned;
	
	private Set<LibBookView> libBooksReturned;
	
	public Set<LibBookView> getLibBooksLoaned() {
		return libBooksLoaned;
	}

	public void setLibBooksLoaned(Set<LibBookView> libBooksLoaned) {
		this.libBooksLoaned = libBooksLoaned;
	}

	public Set<LibBookView> getLibBooksReturned() {
		return libBooksReturned;
	}

	public void setLibBooksReturned(Set<LibBookView> libBooksReturned) {
		this.libBooksReturned = libBooksReturned;
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

	public Integer getDeskNr() {
		return deskNr;
	}

	public void setDeskNr(Integer deskNr) {
		this.deskNr = deskNr;
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


}

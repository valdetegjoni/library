package com.springboot.library.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.springboot.library.base.BaseRequest;

public class EmployeeRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String address;
	
	@NotNull
	private int deskNr;
	
	@NotNull
	private String email;
	
	@NotNull
	private String name;
	
	private List<@Valid Id> libBooksLoaned;
	
	private List<@Valid Id> libBooksReturned;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDeskNr() {
		return deskNr;
	}
	public void setDeskNr(int deskNr) {
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
	public List<Id> getLibBooksLoaned() {
		return libBooksLoaned;
	}
	public void setLibBooksLoaned(List<Id> libBooksLoaned) {
		this.libBooksLoaned = libBooksLoaned;
	}
	public List<Id> getLibBooksReturned() {
		return libBooksReturned;
	}
	public void setLibBooksReturned(List<Id> libBooksReturned) {
		this.libBooksReturned = libBooksReturned;
	}

}

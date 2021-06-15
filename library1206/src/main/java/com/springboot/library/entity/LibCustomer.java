package com.springboot.library.entity;


import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the lib_customer database table.
 * 
 */
@Entity
@Table(name="lib_customer")
@NamedQuery(name="LibCustomer.findAll", query="SELECT l FROM LibCustomer l")
public class LibCustomer implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id  
	@SequenceGenerator(name="LIB_CUSTOMER_ID_GENERATOR", sequenceName="lib_customer_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIB_CUSTOMER_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=255)
	private String address;

	@Column(nullable=false, length=255)
	private String email;

	@Column(nullable=false, length=255)
	private String name;

	@Column(nullable=false, length=255)
	private String password;

	@Column(nullable=false)
	private Long phone;

	@Column(name="registration_date")
	private Date registrationDate;

	@Column(nullable=false, length=255)
	private String surname;

	//uni-directional many-to-many association to LibBook
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
		name="lib_return_book"
		, joinColumns={
			@JoinColumn(name="id_customer", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_book", nullable=false)
			}
		)
	private Set<LibBook> libBooksReturned;

	//uni-directional many-to-many association to LibBook
	@ManyToMany
	@JoinTable(
		name="lib_request_book"
		, joinColumns={
			@JoinColumn(name="id_customer", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_book", nullable=false)
			}
		)
	private Set<LibBook> libBooksRequest;

	public Set<LibBook> getLibBooksReturned() {
		return libBooksReturned;
	}

	public void setLibBooksReturned(Set<LibBook> libBooksReturned) {
		this.libBooksReturned = libBooksReturned;
	}

	public Set<LibBook> getLibBooksRequest() {
		return libBooksRequest;
	}

	public void setLibBooksRequest(Set<LibBook> libBooksRequest) {
		this.libBooksRequest = libBooksRequest;
	}

	public Set<LibBook> getLibLoanBooks() {
		return libLoanBooks;
	}

	public void setLibLoanBooks(Set<LibBook> libLoanBooks) {
		this.libLoanBooks = libLoanBooks;
	}

	@ManyToMany
	@JoinTable(
		name="lib_loan_book"
		, joinColumns={
			@JoinColumn(name="id_customer", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_book", nullable=false)
			}
		)
	private Set<LibBook> libLoanBooks;

	//bi-directional many-to-one association to LibRequestBook
	

	public LibCustomer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
}
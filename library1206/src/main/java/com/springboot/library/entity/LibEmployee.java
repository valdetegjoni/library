package com.springboot.library.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the lib_employee database table.
 * 
 */
@Entity
@Table(name="lib_employee")
@NamedQuery(name="LibEmployee.findAll", query="SELECT l FROM LibEmployee l")
public class LibEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	         
	@Id
	@SequenceGenerator(name="LIB_EMPLOYEE_ID_GENERATOR", sequenceName="lib_employee_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIB_EMPLOYEE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String address;

	@Column(name="desk_nr", nullable=false)
	private Integer deskNr;

	@Column(nullable=false, length=255)
	private String email;

	@Column(nullable=false, length=255)
	private String name;

	//uni-directional many-to-many association to LibBook
	@ManyToMany
	@JoinTable(
		name="lib_loan_book"
		, joinColumns={
			@JoinColumn(name="id_issued_emp", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_book", nullable=false)
			}
		)
	private Set<LibBook> libBooksLoaned;

	//uni-directional many-to-many association to LibBook
	@ManyToMany
	@JoinTable(
		name="lib_return_book"
		, joinColumns={
			@JoinColumn(name="id_employee", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_book", nullable=false)
			}
		)
	private Set<LibBook> libBooksReturned;


	public LibEmployee() {
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

	public Integer getDeskNr() {
		return this.deskNr;
	}

	public void setDeskNr(Integer deskNr) {
		this.deskNr = deskNr;
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

	public Set<LibBook> getLibBooksLoaned() {
		return libBooksLoaned;
	}

	public void setLibBooksLoaned(Set<LibBook> libBooksLoaned) {
		this.libBooksLoaned = libBooksLoaned;
	}

	public Set<LibBook> getLibBooksReturned() {
		return libBooksReturned;
	}

	public void setLibBooksReturned(Set<LibBook> libBooksReturned) {
		this.libBooksReturned = libBooksReturned;
	}

	

}
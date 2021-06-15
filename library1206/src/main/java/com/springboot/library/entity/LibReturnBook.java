package com.springboot.library.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lib_return_book database table.
 * 
 */
@Entity
@Table(name="lib_return_book")
@NamedQuery(name="LibReturnBook.findAll", query="SELECT l FROM LibReturnBook l")
public class LibReturnBook implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@SequenceGenerator(name="LIB_RETURN_BOOK_ID_GENERATOR", sequenceName="lib_return_book_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIB_RETURN_BOOK_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="has_fine")
	private Boolean hasFine;

	@Column(name="return_date", nullable=false)
	private Date returnDate;

	//uni-directional many-to-one association to LibBook
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_book", nullable=false)
	private LibBook libBook;

	//uni-directional many-to-one association to LibCustomer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_customer", nullable=false)
	private LibCustomer libCustomer;

	//bi-directional many-to-one association to LibEmployee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_employee", nullable=false)
	private LibEmployee libEmployee;

	public LibReturnBook() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getHasFine() {
		return this.hasFine;
	}

	public void setHasFine(Boolean hasFine) {
		this.hasFine = hasFine;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public LibBook getLibBook() {
		return this.libBook;
	}

	public void setLibBook(LibBook libBook) {
		this.libBook = libBook;
	}

	public LibCustomer getLibCustomer() {
		return this.libCustomer;
	}

	public void setLibCustomer(LibCustomer libCustomer) {
		this.libCustomer = libCustomer;
	}

	public LibEmployee getLibEmployee() {
		return this.libEmployee;
	}

	public void setLibEmployee(LibEmployee libEmployee) {
		this.libEmployee = libEmployee;
	}

}
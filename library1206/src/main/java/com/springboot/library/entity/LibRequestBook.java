package com.springboot.library.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lib_request_book database table.
 * 
 */
@Entity
@Table(name="lib_request_book")
@NamedQuery(name="LibRequestBook.findAll", query="SELECT l FROM LibRequestBook l")
public class LibRequestBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LIB_REQUEST_BOOK_ID_GENERATOR", sequenceName="lib_request_book_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIB_REQUEST_BOOK_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="request_date", nullable=false)
	private Date requestDate;

	//uni-directional many-to-one association to LibBook
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_book", nullable=false)
	private LibBook libBook;

	//bi-directional many-to-one association to LibCustomer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_customer", nullable=false)
	private LibCustomer libCustomer;

	public LibRequestBook() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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

}
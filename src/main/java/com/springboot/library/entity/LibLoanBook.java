package com.springboot.library.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the lib_loan_book database table.
 * 
 */
@Entity
@Table(name="lib_loan_book")
@NamedQuery(name="LibLoanBook.findAll", query="SELECT l FROM LibLoanBook l")
public class LibLoanBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LIB_LOAN_BOOK_ID_GENERATOR", sequenceName="lib_loan_book_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIB_LOAN_BOOK_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="issued_date", nullable=false)
	private Date issuedDate;


	//uni-directional many-to-one association to LibBook
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_book", nullable=false)
	private LibBook libBook;

	//bi-directional many-to-one association to LibCustomer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_customer", nullable=false)
	private LibCustomer libCustomer;

	//bi-directional many-to-one association to LibEmployee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_issued_emp", nullable=false)
	private LibEmployee libEmployeeIssued;

	//bi-directional many-to-one association to LibEmployee
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="id_received_emp", nullable=false)
	private LibEmployee libEmployeeReceived;

	public LibLoanBook() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getIssuedDate() {
		return this.issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
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

	public LibEmployee getLibEmployeeIssued() {
		return this.libEmployeeIssued;
	}

	public void setLibEmployeeIssued(LibEmployee libEmployeeIssued) {
		this.libEmployeeIssued = libEmployeeIssued;
	}

	public LibEmployee getLibEmployeeReceived() {
		return this.libEmployeeReceived;
	}

	public void setLibEmployeeReceived(LibEmployee libEmployeeReceived) {
		this.libEmployeeReceived = libEmployeeReceived;
	}

}
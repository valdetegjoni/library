package com.springboot.library.entity;

import java.io.Serializable;
//import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

/**
 * The persistent class for the lib_book database table.
 * 
 */
@Entity
@Table(name="lib_book")
 @NamedQuery(name="LibBook.findAll", query="SELECT l FROM LibBook l")
public class LibBook {
/*
 *     @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "fc_team_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fc_team_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fc_team_id_seq")
    	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
	@SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1)
 */

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIB_BOOK_ID_GENERATOR")
	@SequenceGenerator(name="LIB_BOOK_ID_GENERATOR", sequenceName="lib_book_id_seq", allocationSize = 1)
//	@SequenceGenerator(name = "lib_book_id_seq", allocationSize = 1) 

	@Column(name = "id",unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=255)
	private String author;

	@Column(nullable=false, length=255)
	private String genre;

	@Column(nullable=false, length=13)
	private String issbn;

	@Column(name="rental_price", nullable=false)
	private Long rentalPrice;

	@Column(length=50)
	private String status;

	@Column(nullable=false, length=255)
	private String title;

	public LibBook() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIssbn() {
		return this.issbn;
	}

	public void setIssbn(String issbn) {
		this.issbn = issbn;
	}

	public Long getRentalPrice() {
		return this.rentalPrice;
	}

	public void setRentalPrice(Long rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
CREATE SEQUENCE lib_book_id_seq 
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;
	
CREATE table lib_book (
	id int8 NOT NULL DEFAULT nextval('lib_book_id_seq'),
	issbn VARCHAR(13) NOT NULL,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	genre VARCHAR(255) NOT NULL,
	rental_price int8 NOT NULL,
	status VARCHAR(50),	
	PRIMARY KEY (id)
);

CREATE SEQUENCE lib_customer_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE lib_customer (
  id int8 NOT NULL DEFAULT nextval('lib_customer_id_seq'),
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone int8 NOT NULL,
  password VARCHAR(255) NOT NULL,
  registration_date TIMESTAMP,
  PRIMARY KEY (id));

  
CREATE SEQUENCE lib_employee_id_seq 
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;
	
CREATE TABLE lib_employee (
	id int8 NOT NULL DEFAULT nextval('lib_employee_id_seq'),
	name VARCHAR(255) NOT NULL,
	address VARCHAR(255),
	email VARCHAR(255) NOT NULL,
	desk_nr int4 NOT NULL,
	PRIMARY KEY (id));
  
CREATE SEQUENCE lib_request_book_id_seq 
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;	
	
CREATE TABLE lib_request_book
(
	id int8 NOT NULL DEFAULT nextval('lib_request_book_id_seq'),	
	id_book int8 NOT NULL,
	id_customer int8 NOT NULL,
	request_date TIMESTAMP NOT NULL,
	PRIMARY KEY(id)
);

CREATE SEQUENCE lib_return_book_id_seq 
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;

CREATE TABLE lib_return_book
(
	id int8 NOT NULL DEFAULT nextval('lib_return_book_id_seq'),
	id_employee int8 NOT NULL,
	id_book int8 NOT NULL,
	id_customer int8 NOT NULL, 
	return_date TIMESTAMP NOT NULL,
	has_fine BOOLEAN,
	PRIMARY KEY(id)
);


CREATE SEQUENCE lib_loan_book_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;

CREATE TABLE lib_loan_book (
	id int8 NOT NULL DEFAULT nextval('lib_loan_book_id_seq'),
	id_customer int8 NOT NULL,
	id_book int8 NOT NULL,
	id_issued_emp int8 NOT NULL,
	issued_date TIMESTAMP NOT NULL,
	id_received_emp int8 NOT NULL,
	PRIMARY KEY (id)
);

----FOREIGN KEY CONSTRAINTS-----------------


ALTER TABLE if EXISTS lib_request_book
ADD CONSTRAINT  lib_request_book_lb
FOREIGN KEY (id_book) REFERENCES lib_book;

ALTER TABLE if EXISTS lib_request_book
ADD CONSTRAINT lib_request_customer_lb
FOREIGN KEY (id_customer) REFERENCES lib_customer;

ALTER TABLE if EXISTS lib_return_book
ADD CONSTRAINT lib_return_book_emp_lb
FOREIGN KEY(id_employee) REFERENCES lib_employee;

ALTER TABLE if EXISTS lib_return_book
ADD CONSTRAINT lib_return_book_book_lb
FOREIGN KEY(id_book) REFERENCES lib_book;

ALTER TABLE if EXISTS lib_return_book
ADD CONSTRAINT lib_return_book_cust_lb
FOREIGN KEY(id_customer) REFERENCES lib_customer;

ALTER TABLE if EXISTS lib_loan_book
ADD CONSTRAINT lib_loan_customer_lb
FOREIGN KEY (id_customer) REFERENCES lib_customer;

ALTER TABLE if EXISTS lib_loan_book
ADD CONSTRAINT lib_loan_book_lb
FOREIGN KEY (id_book) REFERENCES lib_book;

ALTER TABLE if EXISTS lib_loan_book
ADD CONSTRAINT lib_loan_issued_emp_lb
FOREIGN KEY (id_issued_emp) REFERENCES lib_employee;

ALTER TABLE if EXISTS lib_loan_book
ADD CONSTRAINT lib_loan_received_emp_lb
FOREIGN KEY (id_received_emp) REFERENCES lib_employee;

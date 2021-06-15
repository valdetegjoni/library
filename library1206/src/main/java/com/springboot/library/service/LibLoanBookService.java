package com.springboot.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.library.converter.LoanBookToLoanBookViewConverter;
import com.springboot.library.repository.LibBookRepository;
import com.springboot.library.repository.LibCustomerRepository;
import com.springboot.library.repository.LibEmployeeRepository;
import com.springboot.library.repository.LibLoanBookRepository;
import com.springboot.library.request.LoanBookRequest;
import com.springboot.library.util.MessageUtil;
import com.springboot.library.view.LibLoanBookView;
import com.springboot.library.entity.*;
import com.springboot.library.error.EntityNotFoundException;

@Service
public class LibLoanBookService {

	private final LibLoanBookRepository repoLoanBook;
	private final LibBookRepository repoBook;
	private final LibCustomerRepository repoCustomer;
	private final LibEmployeeRepository repoEmployee;
	private final LoanBookToLoanBookViewConverter converter;
	private final MessageUtil message;

	/**
	 * @param repoLoanBook
	 * @param repoBook
	 * @param repoCustomer
	 * @param repoEmployee
	 * @param converter
	 * @param message
	 */
	public LibLoanBookService(LibLoanBookRepository repoLoanBook, LibBookRepository repoBook,
			LibCustomerRepository repoCustomer, LibEmployeeRepository repoEmployee,
			LoanBookToLoanBookViewConverter converter, MessageUtil message) {
		this.repoLoanBook = repoLoanBook;
		this.repoBook = repoBook;
		this.repoCustomer = repoCustomer;
		this.repoEmployee = repoEmployee;
		this.converter = converter;
		this.message = message;
	}

	public LibLoanBookView getLoanBook(Long id) {
		LibLoanBook loanBook = findByIdOrThrow(id);
		return converter.convert(loanBook);
	}

	public LibLoanBook findByIdOrThrow(Long id) {
		LibLoanBook loanedBook = repoLoanBook.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(message.getMessage("loanedBook.NotFound", id)));
		return loanedBook;
	}
	
	public Page<LibLoanBookView> getAllLoanedBooks(Pageable pageable) {
		Page<LibLoanBook> loanedBooks = repoLoanBook.findAll(pageable);
		List<LibLoanBookView> loanedBookViews = new ArrayList<>();
		loanedBooks.forEach(loanedBook -> { LibLoanBookView loanedView = converter.convert(loanedBook);
		loanedBookViews.add(loanedView);
		});
		return new PageImpl<>(loanedBookViews, pageable, loanedBooks.getTotalElements());
	}

	@Transactional
	public void deleteLoanBook(Long id) {
		try {
			repoLoanBook.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(message.getMessage("loanedBook.NotFound", id));
		}
	}
	
	public LibLoanBookView createLoanBook(LoanBookRequest req) {
		LibLoanBook loanBook = new LibLoanBook();
		this.prepare(loanBook, req);
		LibLoanBook loanBookSaved = repoLoanBook.save(loanBook);
		return converter.convert(loanBookSaved);
	}

	public LibLoanBookView update(LibLoanBook book, LoanBookRequest req) {
		LibLoanBook prepared = this.prepare(book, req);
		LibLoanBook updated = repoLoanBook.save(prepared);
		return converter.convert(updated);
		
	}
	private LibLoanBook prepare(LibLoanBook loanBook, LoanBookRequest req) {
		loanBook.setIssuedDate(req.getIssuedDate());
		loanBook.setLibBook(repoBook.getOne(req.getLibBookId()));
		loanBook.setLibCustomer(repoCustomer.getOne(req.getLibCustomerId()));
		loanBook.setLibEmployeeIssued(repoEmployee.getOne(req.getLibEmployeeIssuedId()));
		loanBook.setLibEmployeeReceived(repoEmployee.getOne(req.getLibEmployeeReceived()));
		return loanBook;
	}
}

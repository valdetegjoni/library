/**
 * 
 */
package com.springboot.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.library.converter.ReturnBookToReturnBookViewConverter;
import com.springboot.library.entity.LibReturnBook;
import com.springboot.library.error.EntityNotFoundException;
import com.springboot.library.repository.LibBookRepository;
import com.springboot.library.repository.LibCustomerRepository;
import com.springboot.library.repository.LibEmployeeRepository;
import com.springboot.library.repository.LibReturnBookRepository;
import com.springboot.library.request.ReturnBookRequest;
import com.springboot.library.util.MessageUtil;
import com.springboot.library.view.LibReturnBookView;
/**
 * @author valdete
 *
 */
@Service
public class LibReturnBookService {
	private final LibBookRepository bookRepo;
	private final LibCustomerRepository customerRepo;
	private final LibEmployeeRepository employeeRepo;
	private final LibReturnBookRepository returnBookRepo;
	private final ReturnBookToReturnBookViewConverter converter;
	private final MessageUtil messagge;
	/**
	 * @param bookRepo
	 * @param customerRepo
	 * @param employeeRepo
	 * @param returnBookRepo
	 * @param messagge
	 */
	public LibReturnBookService(LibBookRepository bookRepo, LibCustomerRepository customerRepo,
			LibEmployeeRepository employeeRepo, LibReturnBookRepository returnBookRepo, ReturnBookToReturnBookViewConverter converter, MessageUtil messagge) {
		this.bookRepo = bookRepo;
		this.customerRepo = customerRepo;
		this.employeeRepo = employeeRepo;
		this.returnBookRepo = returnBookRepo;
		this.converter =  converter;
		this.messagge = messagge;
	}
	
	public LibReturnBook findReturnBookByIdOrThrow(Long id) {
		return returnBookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(messagge.getMessage("returnedBook.NotFound", id)));
	}

	public LibReturnBookView getReturnBook(Long id) {
		LibReturnBook returnBook = this.findReturnBookByIdOrThrow(id);
		return converter.convert(returnBook);
	}
	
	public Page<LibReturnBookView> findAllReturnBook(Pageable pageable){
		List<LibReturnBookView> returnBookList = new ArrayList<>();
		Page<LibReturnBook> returnBookPaged = returnBookRepo.findAll(pageable);
		returnBookPaged.forEach(returnBook -> {LibReturnBookView view = converter.convert(returnBook);
						returnBookList.add(view);
		});
		return new PageImpl<>(returnBookList, pageable, returnBookPaged.getTotalElements());
	}
	@Transactional
	public void deleteReturnBook(Long id) {
		try {
			returnBookRepo.deleteById(id);
		} catch (DataAccessResourceFailureException e) {
			throw new EntityNotFoundException(messagge.getMessage("returnedBook.NotFound", id));
		}
	}
	
	public LibReturnBookView createReturnBook(ReturnBookRequest req) {
		
		LibReturnBook returnBook = new LibReturnBook();
		this.prepare(returnBook, req);
		LibReturnBook created = returnBookRepo.save(returnBook);
		return converter.convert(created);
	}

	public LibReturnBookView update(LibReturnBook rBook, ReturnBookRequest req) {
		LibReturnBook book = this.prepare(rBook, req);
		LibReturnBook updated = returnBookRepo.save(book);
		return converter.convert(updated);
	}
	public LibReturnBook prepare(LibReturnBook returnBook, ReturnBookRequest req) {
		returnBook.setHasFine(req.isHas_fine());
		
		returnBook.setLibBook(bookRepo.getOne(req.getLibBookId()));
		returnBook.setLibCustomer(customerRepo.getOne(req.getLibCustomerId()));
		returnBook.setLibEmployee(employeeRepo.getOne(req.getLibEmployeeId()));
		req.setReturnDate(req.getReturnDate());
		
		return returnBook;
	}
}

/**
 * 
 */
package com.springboot.library.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibBook;
import com.springboot.library.base.BaseRequest;
import com.springboot.library.converter.CustomerToCustomerViewConverter;
import com.springboot.library.error.EntityNotFoundException;
import com.springboot.library.repository.LibBookRepository;
import com.springboot.library.repository.LibCustomerRepository;
import com.springboot.library.request.CustomerRequest;
import com.springboot.library.util.MessageUtil;
import com.springboot.library.view.LibCustomerView;

/**
 * @author valdete
 *
 */
@Service
public class LibCustomerService {

	/**
	 * @param repoCustomer
	 * @param repoBook
	 * @param converter
	 * @param message
	 */
	public LibCustomerService(LibCustomerRepository repoCustomer, LibBookRepository repoBook,
			CustomerToCustomerViewConverter converter, MessageUtil message) {
		this.repoCustomer = repoCustomer;
		this.repoBook = repoBook;
		this.converter = converter;
		this.message = message;
	}

	private final LibCustomerRepository repoCustomer;
	private final LibBookRepository repoBook;
	private final CustomerToCustomerViewConverter converter;
	private final MessageUtil message;

	/**
	 * @param repo
	 * @param converter
	 * @param message
	 */

	public LibCustomer findLibCustomerOrThrow(long id) {
		return repoCustomer.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(message.getMessage("customer.NotFound", id)));
	}

	public LibCustomerView getCustomer(Long id) {
		LibCustomer customer = findLibCustomerOrThrow(id);
		return converter.convert(customer);
	}

	public Page<LibCustomerView> findAllCustomer(Pageable pageable) {
		Page<LibCustomer> customers = repoCustomer.findAll(pageable);
		List<LibCustomerView> customerViews = new ArrayList<>();
		customers.forEach(customer -> { LibCustomerView customerView = converter.convert(customer);
		customerViews.add(customerView);
		});
		return new PageImpl<>(customerViews, pageable, customers.getTotalElements());
	}
	 
	  
	@Transactional
	public void delete(Long id) {
		try {
			repoCustomer.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(message.getMessage("customer.NotFound", id));
		}

	}

	public LibCustomerView createCustomer(CustomerRequest req) {
		LibCustomer customer = new LibCustomer();
		this.prepare(customer, req);
		LibCustomer customerSave = repoCustomer.save(customer);
		return converter.convert(customerSave);
	}

	public LibCustomerView updateCustomer(LibCustomer customer, CustomerRequest req) {
		LibCustomer newCustomer = this.prepare(customer, req);
		LibCustomer customerUpdated = repoCustomer.save(newCustomer);
		return converter.convert(customerUpdated);
	}

	public LibCustomer prepare(LibCustomer customer, CustomerRequest req) {
		customer.setAddress(req.getAddress());
		customer.setEmail(req.getEmail());
		customer.setName(req.getName());
		customer.setSurname(req.getSurname());
		customer.setPassword(req.getPassword());
		customer.setPhone(req.getPhone());
		customer.setRegistrationDate(req.getRegistrationDate());

		List<LibBook> bookListRequested = repoBook
				.findAllById(req.getLibBooksRequest().stream().map(BaseRequest.Id::getId).collect(Collectors.toSet()));
		Set<LibBook> booksReq = new HashSet<>(bookListRequested);
		customer.setLibBooksRequest(booksReq);

		List<LibBook> bookListReturned = repoBook
				.findAllById(req.getLibBooksReturned().stream().map(BaseRequest.Id::getId).collect(Collectors.toSet()));
		Set<LibBook> booksReturned = new HashSet<>(bookListReturned);
		customer.setLibBooksReturned(booksReturned);

		List<LibBook> bookListLoaned = repoBook
				.findAllById(req.getLibLoanBooks().stream().map(BaseRequest.Id::getId).collect(Collectors.toSet()));
		Set<LibBook> booksLoaned = new HashSet<>(bookListLoaned);
		customer.setLibLoanBooks(booksLoaned);

		return customer;
	}

}

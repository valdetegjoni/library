/**
 * 
 */
package com.springboot.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.library.converter.RequestBookToRequestBookViewConverter;
import com.springboot.library.repository.LibBookRepository;
import com.springboot.library.repository.LibCustomerRepository;
import com.springboot.library.repository.LibRequestBookRepository;
import com.springboot.library.request.RequestBookRequest;
import com.springboot.library.util.MessageUtil;
import com.springboot.library.view.LibRequestBookView;
import com.springboot.library.entity.*;
import com.springboot.library.error.EntityNotFoundException;

/**
 * @author valdete
 *
 */
@Service
public class LibRequestBookService {
	private final LibCustomerRepository customerRepo;
	private final LibBookRepository bookRepo;
	private final LibRequestBookRepository requestRepo;
	private final MessageUtil messagge;
	private final RequestBookToRequestBookViewConverter converter;

	/**
	 * @param customerRepo
	 * @param bookRepo
	 * @param requestRepo
	 * @param messagge
	 * @param converter
	 */
	public LibRequestBookService(LibCustomerRepository customerRepo, LibBookRepository bookRepo,
			LibRequestBookRepository requestRepo, MessageUtil messagge,
			RequestBookToRequestBookViewConverter converter) {
		this.customerRepo = customerRepo;
		this.bookRepo = bookRepo;
		this.requestRepo = requestRepo;
		this.messagge = messagge;
		this.converter = converter;
	}

	public LibRequestBookView getRequestBook(Long id) {
		LibRequestBook requestBook = this.findByIdOrThrow(id);
		return converter.convert(requestBook);
	}

	public LibRequestBook findByIdOrThrow(Long id) {
		LibRequestBook requestBook = requestRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(messagge.getMessage("request.NotFound", id)));
		return requestBook;
	}

	public Page<LibRequestBookView> findAllRequestBooks(Pageable pageable) {
		Page<LibRequestBook> books = requestRepo.findAll(pageable);
		List<LibRequestBookView> requestListViews = new ArrayList<>();
		books.forEach(book -> {
			LibRequestBookView view = converter.convert(book);
			requestListViews.add(view);
		});
		return new PageImpl<>(requestListViews, pageable, books.getTotalElements());
	}

	@Transactional
	public void delete(Long id) {
		try {
			requestRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(messagge.getMessage("request.NotFound", id));
		}
	}

	public LibRequestBookView create(RequestBookRequest req) {
		LibRequestBook bookReq = new LibRequestBook();
		this.prepare(bookReq, req);
		LibRequestBook created = requestRepo.save(bookReq);
		return converter.convert(created);
	}

	public LibRequestBookView update(LibRequestBook requestUpdate, RequestBookRequest req) {
		LibRequestBook requested = this.prepare(requestUpdate, req);
		LibRequestBook updated = requestRepo.save(requested);
		return converter.convert(updated);
	}

	private LibRequestBook prepare(LibRequestBook bookReq, RequestBookRequest req) {
		bookReq.setLibBook(bookRepo.getOne(req.getLibBookId()));
		bookReq.setLibCustomer(customerRepo.getOne(req.getLibCustomerId()));
		bookReq.setRequestDate(req.getRequestDate());
		return bookReq;
	}
}

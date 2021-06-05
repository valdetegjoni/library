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

import com.springboot.library.converter.BookToBookViewConverter;
import com.springboot.library.entity.LibBook;
import com.springboot.library.error.EntityNotFoundException;
import com.springboot.library.repository.LibBookRepository;
import com.springboot.library.request.BookRequest;
import com.springboot.library.util.MessageUtil;
import com.springboot.library.view.LibBookView;

/**
 * @author valdete
 *
 */
@Service
public class LibBookService {

	private final LibBookRepository bookRepo;
	private final BookToBookViewConverter bookToBookViewConverter;
	private final MessageUtil messageUtil;
	/**
	 * @param bookRepo
	 * @param bookToBookViewConverter
	 * @param messageUtil
	 */
	public LibBookService(LibBookRepository bookRepo, BookToBookViewConverter bookToBookViewConverter,
			MessageUtil messageUtil) {
		this.bookRepo = bookRepo;
		this.bookToBookViewConverter = bookToBookViewConverter;
		this.messageUtil = messageUtil;
	}
	
	
	public LibBook findLibBookOrThrow(Long id) {
		return bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id)));
	}
	
	public LibBookView getBook(Long id) {
		LibBook book = findLibBookOrThrow(id);
		return bookToBookViewConverter.convert(book);
	}
	
	public Page<LibBookView> findAllBooks(Pageable pageable) {
		Page<LibBook> books = bookRepo.findAll(pageable);
		List<LibBookView> bookViews = new ArrayList<>();
		books.forEach(book -> {LibBookView bookView = bookToBookViewConverter.convert(book);
								bookViews.add(bookView);
		});
		return new PageImpl<>(bookViews, pageable, books.getTotalElements());
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			bookRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id));
		}
		
	}
	public LibBookView create(BookRequest req) {
		LibBook book = new LibBook();
		this.prepare(book, req);
		LibBook bookSave = bookRepo.save(book);
		return bookToBookViewConverter.convert(bookSave);
	}

	public LibBookView update(LibBook book, BookRequest req) {
		LibBook newBook = prepare(book, req);
		LibBook bookSave = bookRepo.save(newBook);
		return bookToBookViewConverter.convert(bookSave);
	}

	public LibBook prepare(LibBook book, BookRequest req) {
		book.setAuthor(req.getAuthor());
		book.setGenre(req.getGenre());
		book.setIssbn(req.getIssbn());
		book.setRentalPrice(req.getRentalPrice());
		book.setStatus(req.getStatus());
		book.setTitle(req.getTitle());
		return book;
	}

}

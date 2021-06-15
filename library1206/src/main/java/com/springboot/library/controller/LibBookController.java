/**
 * 
 */
package com.springboot.library.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.library.entity.LibBook;
import com.springboot.library.request.BookRequest;
import com.springboot.library.service.LibBookService;
import com.springboot.library.view.LibBookView;

/**
 * @author valdete
 *
 */
@RestController
@RequestMapping("/book")
public class LibBookController {

	private final LibBookService serviceBook;

	/**
	 * @param serviceBook
	 */
	public LibBookController(LibBookService serviceBook) {
		this.serviceBook = serviceBook;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public LibBookView getBook(@PathVariable Long id) {
		return serviceBook.getBook(id);
	}

	@GetMapping
	@ResponseBody
	public Page<LibBookView> getAllBooks(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

		return serviceBook.findAllBooks(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public LibBookView create(@RequestBody @Valid BookRequest req) {
		return serviceBook.create(req);
	}

	// @RequestMapping(value="delete/{id}", method= RequestMethod.PUT)
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long id) {
		serviceBook.delete(id);

	}

	@PutMapping("/{id}")
	public LibBookView update(@PathVariable(name = "id") Long id, @RequestBody @Valid BookRequest req) {
		LibBook book = serviceBook.findLibBookOrThrow(id);
		return serviceBook.update(book, req);
	}
}

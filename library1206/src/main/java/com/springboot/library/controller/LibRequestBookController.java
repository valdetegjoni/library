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

import com.springboot.library.entity.LibRequestBook;
import com.springboot.library.request.RequestBookRequest;
import com.springboot.library.service.LibRequestBookService;
import com.springboot.library.view.LibRequestBookView;

/**
 * @author valdete
 *
 */
@RestController
@RequestMapping("/request")
public class LibRequestBookController {

	private final LibRequestBookService service;

	/**
	 * @param service
	 */
	public LibRequestBookController(LibRequestBookService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public LibRequestBookView getRequestBook(@PathVariable Long id) {
		return service.getRequestBook(id);
	}

	@GetMapping
	@ResponseBody
	public Page<LibRequestBookView> getAllRequests(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
	
		return service.findAllRequestBooks(pageable);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRequestBook(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public LibRequestBookView createRequestBook(@RequestBody @Valid RequestBookRequest req) {
		return service.create(req);
	}
	
	@PutMapping("/{id}")
	public LibRequestBookView update(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestBookRequest req) {
		LibRequestBook requestBook = service.findByIdOrThrow(id);
		return service.update(requestBook, req);
	}
}

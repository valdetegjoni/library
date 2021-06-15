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

import com.springboot.library.entity.LibReturnBook;
import com.springboot.library.request.ReturnBookRequest;
import com.springboot.library.service.LibReturnBookService;
import com.springboot.library.view.LibReturnBookView;

/**
 * @author valdete
 *
 */
@RestController
@RequestMapping("/return")
public class LibReturnBookController {

	private final LibReturnBookService service;

	/**
	 * @param service
	 */
	public LibReturnBookController(LibReturnBookService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public LibReturnBookView getReturnBook(@PathVariable Long id) {
		return service.getReturnBook(id);
	}
	
	@GetMapping
	@ResponseBody
	public Page<LibReturnBookView> getAllReturnBooks(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return service.findAllReturnBook(pageable);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReturnBook(@PathVariable Long id) {
		service.deleteReturnBook(id);
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public LibReturnBookView createReturnBook(@RequestBody @Valid ReturnBookRequest req) {
		return service.createReturnBook(req);
	}
	
	@PutMapping("/{id}")
	public LibReturnBookView updateReturnBook(@PathVariable(name = "id") Long id, @RequestBody @Valid ReturnBookRequest req) {
		LibReturnBook returnBook = service.findReturnBookByIdOrThrow(id);
		return service.update(returnBook, req);
	}
}

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

import com.springboot.library.entity.LibLoanBook;
import com.springboot.library.request.LoanBookRequest;
import com.springboot.library.service.LibLoanBookService;
import com.springboot.library.view.LibLoanBookView;

/**
 * @author valdete
 *
 */
@RestController
@RequestMapping("/loan")
public class LibLoanBookController {

	private final LibLoanBookService service;

	/**
	 * @param service
	 */
	public LibLoanBookController(LibLoanBookService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public LibLoanBookView getLoanBook(@PathVariable Long id) {
		return service.getLoanBook(id);
	}
	
	@GetMapping
	@ResponseBody
	public Page<LibLoanBookView> getAllLoanBooks(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return service.getAllLoanedBooks(pageable);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.deleteLoanBook(id);
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public LibLoanBookView createLoanBook(@RequestBody @Valid LoanBookRequest req) {
		return service.createLoanBook(req);
	}
	
	@PutMapping("/{id}")
	public LibLoanBookView updateLoanBook(@PathVariable(name = "id") Long id, @RequestBody @Valid LoanBookRequest req) {
		LibLoanBook loanBook = service.findByIdOrThrow(id);
		return service.update(loanBook, req);
	}
	
	

}

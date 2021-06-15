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

import com.springboot.library.entity.LibCustomer;
import com.springboot.library.request.CustomerRequest;
import com.springboot.library.service.LibCustomerService;
import com.springboot.library.view.LibCustomerView;

/**
 * @author valdete
 *
 */

@RestController
@RequestMapping("/customer")
public class LibCustomerController {

	private final LibCustomerService customerService;

	/**
	 * @param customerService
	 */
	public LibCustomerController(LibCustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public LibCustomerView getCustomer(@PathVariable Long id) {

		return customerService.getCustomer(id);
	}

	@GetMapping
	@ResponseBody
	public Page<LibCustomerView> getAllCustomers(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return customerService.findAllCustomer(pageable);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Long id) {
		customerService.delete(id);
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public LibCustomerView create(@RequestBody @Valid CustomerRequest req) {
		return customerService.createCustomer(req);
	}

	@PutMapping("/{id}")
	public LibCustomerView update(@PathVariable(name = "id")Long id, @RequestBody @Valid CustomerRequest req) {
		LibCustomer customer = customerService.findLibCustomerOrThrow(id);
		return customerService.updateCustomer(customer, req);
	}

}

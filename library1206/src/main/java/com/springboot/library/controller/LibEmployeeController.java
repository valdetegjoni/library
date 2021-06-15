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

import com.springboot.library.entity.LibEmployee;
import com.springboot.library.request.EmployeeRequest;
import com.springboot.library.service.LibEmployeeService;
import com.springboot.library.view.LibEmployeeView;

/**
 * @author valdete
 * @param <LibemployeeView>
 *
 */

@RestController
@RequestMapping("/employee")
public class LibEmployeeController<LibemployeeView> {

	private final LibEmployeeService service;

	/**
	 * @param service
	 */
	public LibEmployeeController(LibEmployeeService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public LibEmployeeView getEmployee(@PathVariable Long id) {
		return service.getEmployee(id);
	}

	@GetMapping
	@ResponseBody
	public Page<LibEmployeeView> getAllEmployees(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

		return service.findAllEmployee(pageable);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.deleteEmployee(id);
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public LibEmployeeView create(@RequestBody @Valid EmployeeRequest req) {
		return service.createEmployee(req);
	}

	@PutMapping("/{id}")
	public LibEmployeeView updateEmployee(@PathVariable(name = "id") Long id, @RequestBody @Valid EmployeeRequest req) {

		LibEmployee employee = service.findEmployeeByIdOrThrow(id);
		return service.updateEmployee(employee, req);
	}
}

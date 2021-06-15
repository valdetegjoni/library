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

import com.springboot.library.base.BaseRequest;
import com.springboot.library.converter.EmployeeToEmployeeViewConverter;
import com.springboot.library.error.EntityNotFoundException;
import com.springboot.library.entity.LibEmployee;
import com.springboot.library.entity.LibBook;
import com.springboot.library.repository.LibBookRepository;
import com.springboot.library.repository.LibEmployeeRepository;
import com.springboot.library.request.EmployeeRequest;
import com.springboot.library.util.MessageUtil;
import com.springboot.library.view.LibEmployeeView;

/**
 * @author valdete
 *
 */
@Service
public class LibEmployeeService {

	private final LibEmployeeRepository employeeRepo;
	private final EmployeeToEmployeeViewConverter employeeConverter;
	private final MessageUtil message;
	private final LibBookRepository bookRepo;

	/**
	 * @param employeeRepo
	 * @param employeeConverter
	 * @param message
	 * @param bookRepo
	 */
	public LibEmployeeService(LibEmployeeRepository employeeRepo, EmployeeToEmployeeViewConverter employeeConverter,
			MessageUtil message, LibBookRepository bookRepo) {
		this.employeeRepo = employeeRepo;
		this.employeeConverter = employeeConverter;
		this.message = message;
		this.bookRepo = bookRepo;
	}

	public LibEmployee findEmployeeByIdOrThrow(Long id) {
		return employeeRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(message.getMessage("employee.NotFound", id)));

	}

	public LibEmployeeView getEmployee(Long id) {
		LibEmployee emp = findEmployeeByIdOrThrow(id);
		return employeeConverter.convert(emp);
	}

	public Page<LibEmployeeView> findAllEmployee(Pageable pageable) {

		Page<LibEmployee> employees = employeeRepo.findAll(pageable);
		List<LibEmployeeView> employeeViews = new ArrayList<>();
		employees.forEach(employee -> {
			LibEmployeeView view = employeeConverter.convert(employee);
			employeeViews.add(view);
		});
		return new PageImpl<>(employeeViews, pageable, employees.getTotalElements());
	}

	@Transactional
	public void deleteEmployee(Long id) {
		try {
			employeeRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(message.getMessage("employee.NotFound", id));
		}
	}

	public LibEmployeeView createEmployee(EmployeeRequest req) {

		LibEmployee employee = new LibEmployee();
		this.prepare(employee, req);
		LibEmployee createdEmployee = employeeRepo.save(employee);
		return employeeConverter.convert(createdEmployee);
	}

	public LibEmployeeView updateEmployee(LibEmployee employee, EmployeeRequest req) {
		LibEmployee newEmp = this.prepare(employee, req);
		LibEmployee updatedEmp = employeeRepo.save(newEmp);
		return employeeConverter.convert(updatedEmp);
	}

	private LibEmployee prepare(LibEmployee employee, EmployeeRequest req) {

		employee.setAddress(req.getAddress());
		employee.setDeskNr(req.getDeskNr());
		employee.setEmail(req.getEmail());
		employee.setName(req.getName());

		List<LibBook> bookLoanedList = bookRepo
				.findAllById(req.getLibBooksLoaned().stream().map(BaseRequest.Id::getId).collect(Collectors.toSet()));
		Set<LibBook> bookLoanedSet = new HashSet<LibBook>(bookLoanedList);
		employee.setLibBooksLoaned(bookLoanedSet);

		List<LibBook> returnedBookList = bookRepo
				.findAllById(req.getLibBooksReturned().stream().map(BaseRequest.Id::getId).collect(Collectors.toSet()));
		Set<LibBook> returnedBookSet = new HashSet<LibBook>(returnedBookList);
		employee.setLibBooksReturned(returnedBookSet);

		return employee;
	}

}

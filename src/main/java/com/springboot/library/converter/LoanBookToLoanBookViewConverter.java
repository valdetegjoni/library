package com.springboot.library.converter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibEmployee;
import com.springboot.library.entity.LibLoanBook;
import com.springboot.library.view.LibBookView;
import com.springboot.library.view.LibCustomerView;
import com.springboot.library.view.LibEmployeeView;
import com.springboot.library.view.LibLoanBookView;

public class LoanBookToLoanBookViewConverter implements Converter<LibLoanBook, LibLoanBookView>{

	private final BookToBookViewConverter bookToBookViewConverter;
	private final CustomerToCustomerViewConverter customerToCustomerViewConverter;
	private final EmployeeToEmployeeViewConverter employeeToEmployeeViewConverter;
	
	/**
	 * @param bookToBookViewConverter
	 * @param customerToCustomerViewConverter
	 * @param employeeToEmployeeViewConverter
	 */
	public LoanBookToLoanBookViewConverter(BookToBookViewConverter bookToBookViewConverter,
			CustomerToCustomerViewConverter customerToCustomerViewConverter,
			EmployeeToEmployeeViewConverter employeeToEmployeeViewConverter) {
		this.bookToBookViewConverter = bookToBookViewConverter;
		this.customerToCustomerViewConverter = customerToCustomerViewConverter;
		this.employeeToEmployeeViewConverter = employeeToEmployeeViewConverter;
	}

	
	@Override
	public LibLoanBookView convert(LibLoanBook source) {
		// TODO Auto-generated method stub
		LibLoanBookView view = new LibLoanBookView();
		view.setId(source.getId());
		view.setIssuedDate(source.getIssuedDate());
		LibBook book = source.getLibBook();
		view.setLibBook(bookToBookViewConverter.convert(book));
		LibCustomer customer = source.getLibCustomer();
		view.setLibCustomer(customerToCustomerViewConverter.convert(customer));
		LibEmployee employeeIssued = source.getLibEmployeeIssued();
		view.setLibEmployeeIssued(employeeToEmployeeViewConverter.convert(employeeIssued));
		LibEmployee employeeReceived = source.getLibEmployeeReceived();
		view.setLibEmployeeReceived(employeeToEmployeeViewConverter.convert(employeeReceived));
		return view;
	}

}

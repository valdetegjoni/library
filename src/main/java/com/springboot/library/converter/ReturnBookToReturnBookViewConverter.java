package com.springboot.library.converter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibEmployee;
import com.springboot.library.entity.LibReturnBook;
import com.springboot.library.view.LibBookView;
import com.springboot.library.view.LibCustomerView;
import com.springboot.library.view.LibEmployeeView;
import com.springboot.library.view.LibReturnBookView;

public class ReturnBookToReturnBookViewConverter implements Converter<LibReturnBook, LibReturnBookView>{

	private final BookToBookViewConverter libBookViewConverter;

	private final CustomerToCustomerViewConverter libCustomerViewConverter;

	private final EmployeeToEmployeeViewConverter libEmployeeViewConverter;
	
	
	/**
	 * @param libBookViewConverter
	 * @param libCustomerViewConverter
	 * @param libEmployeeViewConverter
	 */
	public ReturnBookToReturnBookViewConverter(BookToBookViewConverter libBookViewConverter,
			CustomerToCustomerViewConverter libCustomerViewConverter, EmployeeToEmployeeViewConverter libEmployeeViewConverter) {
		this.libBookViewConverter = libBookViewConverter;
		this.libCustomerViewConverter = libCustomerViewConverter;
		this.libEmployeeViewConverter = libEmployeeViewConverter;
	}

	@Override
	public LibReturnBookView convert(@NonNull LibReturnBook source) {
		// TODO Auto-generated method stub
		LibReturnBookView view = new LibReturnBookView();
		view.setId(source.getId());
		view.setHasFine(source.getHasFine());
		view.setReturnDate(source.getReturnDate());
		LibBook book = source.getLibBook();
		view.setLibBook(libBookViewConverter.convert(book));
		LibCustomer customer = source.getLibCustomer();
		view.setLibCustomer(libCustomerViewConverter.convert(customer));
		LibEmployee employee = source.getLibEmployee();
		view.setLibEmployee(libEmployeeViewConverter.convert(employee));
		
		return view;
	}

}

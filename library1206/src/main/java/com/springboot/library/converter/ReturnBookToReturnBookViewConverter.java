package com.springboot.library.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibEmployee;
import com.springboot.library.entity.LibReturnBook;
import com.springboot.library.view.LibReturnBookView;

@Component
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

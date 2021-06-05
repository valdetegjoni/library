package com.springboot.library.converter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import org.springframework.core.convert.converter.Converter;

import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibLoanBook;
import com.springboot.library.entity.LibRequestBook;
import com.springboot.library.entity.LibReturnBook;
import com.springboot.library.view.LibBookView;
import com.springboot.library.view.LibCustomerView;
import com.springboot.library.view.LibLoanBookView;

public class CustomerToCustomerViewConverter implements Converter<LibCustomer, LibCustomerView> {

	private final BookToBookViewConverter bookToBookViewConverter;
	
	/**
	 * @param bookToBookViewConverter
	 */
	public CustomerToCustomerViewConverter(BookToBookViewConverter bookToBookViewConverter) {
		this.bookToBookViewConverter = bookToBookViewConverter;
	}
	

	@Override
	public LibCustomerView convert(LibCustomer source) {
		LibCustomerView view = new LibCustomerView();
		view.setId(source.getId());
		view.setName(source.getName());
		view.setSurname(source.getName());
		view.setAddress(source.getAddress());
		view.setPhone(source.getPhone());
		view.setEmail(source.getEmail());
		view.setPassword(source.getPassword());
		view.setRegistrationDate(source.getRegistrationDate());
		Set<LibBookView> viewsRet = new HashSet<>();
		Set<LibBook> booksRet = source.getLibBooksReturned();
		booksRet.forEach(bookRet -> viewsRet.add(bookToBookViewConverter.convert(bookRet)));
		view.setLibBooksReturned(viewsRet);
		
		Set<LibBookView> viewsReq = new HashSet<>();
		Set<LibBook> booksReq = source.getLibBooksRequest();
		booksReq.forEach(bookReq -> viewsReq.add(bookToBookViewConverter.convert(bookReq)));
		view.setLibBooksRequest(viewsReq);
		
		return view;
	}

}

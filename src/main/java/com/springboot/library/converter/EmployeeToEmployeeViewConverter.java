package com.springboot.library.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibEmployee;
import com.springboot.library.view.LibBookView;
import com.springboot.library.view.LibEmployeeView;

public class EmployeeToEmployeeViewConverter implements Converter<LibEmployee, LibEmployeeView>{

	private final BookToBookViewConverter bookToBookViewConverter;
	
	/**
	 * @param bookToBookViewConverter
	 */
	public EmployeeToEmployeeViewConverter(BookToBookViewConverter bookToBookViewConverter) {
		this.bookToBookViewConverter = bookToBookViewConverter;
	}

	@Override
	public LibEmployeeView convert(@NonNull  LibEmployee source) {

		LibEmployeeView view = new LibEmployeeView();
		view.setId(source.getId());
		view.setName(source.getName());
		view.setAddress(source.getAddress());
		view.setEmail(source.getEmail());
		view.setDeskNr(source.getDeskNr());
		Set<LibBookView> loanedBooksViews = new HashSet<>();
		Set<LibBook> books = source.getLibBooksLoaned();
		books.forEach(book -> loanedBooksViews.add(bookToBookViewConverter.convert(book)));
		view.setLibBooksLoaned(loanedBooksViews);
		
		Set<LibBookView> returnedBooksViews = new HashSet<>();
		Set<LibBook> booksRet = source.getLibBooksReturned();
		booksRet.forEach(bookRet -> returnedBooksViews.add(bookToBookViewConverter.convert(bookRet)));
		view.setLibBooksReturned(returnedBooksViews);
		return view;
	}

}

package com.springboot.library.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibRequestBook;
import com.springboot.library.view.LibRequestBookView;

@Component
public class RequestBookToRequestBookViewConverter implements Converter<LibRequestBook, LibRequestBookView>{

	private final BookToBookViewConverter bookViewConverter;
	private final CustomerToCustomerViewConverter customerViewConverter;
	
	
	
	/**
	 * @param bookViewConverter
	 * @param customerViewConverter
	 */
	public RequestBookToRequestBookViewConverter(BookToBookViewConverter bookViewConverter,
			CustomerToCustomerViewConverter customerViewConverter) {
		this.bookViewConverter = bookViewConverter;
		this.customerViewConverter = customerViewConverter;
	}


	@Override
	public LibRequestBookView convert(@NonNull LibRequestBook source) {

		LibRequestBookView view = new LibRequestBookView();
		view.setId(source.getId());
		view.setRequestDate(source.getRequestDate());
		LibBook book = source.getLibBook();
		view.setLibBook(bookViewConverter.convert(book));
		LibCustomer customer = source.getLibCustomer();
		view.setLibCustomer(customerViewConverter.convert(customer));
		return view;
	}

}

package com.springboot.library.converter;



import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.springboot.library.entity.LibBook;
import com.springboot.library.view.LibBookView;

@Component
public class BookToBookViewConverter implements Converter<LibBook, LibBookView>{

	@Override
	public LibBookView convert(@NonNull LibBook source) {
		LibBookView view = new LibBookView();
		view.setId(source.getId());
		view.setAuthor(source.getAuthor());
		view.setGenre(source.getGenre());
		view.setIssbn(source.getIssbn());
		view.setRentalPrice(source.getRentalPrice());
		view.setStatus(source.getStatus());
		view.setTitle(source.getTitle());
		
		return view;
	}

}

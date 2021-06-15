package com.springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.library.entity.LibLoanBook;

public interface LibLoanBookRepository extends JpaRepository<LibLoanBook, Long> {

}

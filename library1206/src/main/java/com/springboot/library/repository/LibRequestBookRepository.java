package com.springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.library.entity.LibRequestBook;

public interface LibRequestBookRepository extends JpaRepository<LibRequestBook, Long> {

}

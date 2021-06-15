/**
 * 
 */
package com.springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.library.entity.LibBook;

/**
 * @author valdete
 *
 */
public interface LibBookRepository extends JpaRepository<LibBook, Long> {

}

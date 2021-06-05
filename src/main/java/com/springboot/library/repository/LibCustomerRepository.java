/**
 * 
 */
package com.springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springboot.library.entity.LibCustomer;

/**
 * @author valdete
 *
 */
public interface LibCustomerRepository extends JpaRepository<LibCustomer, Long> {

}

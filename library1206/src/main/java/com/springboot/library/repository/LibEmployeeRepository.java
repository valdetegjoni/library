package com.springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.library.entity.LibEmployee;

public interface LibEmployeeRepository extends JpaRepository<LibEmployee, Long> {

}

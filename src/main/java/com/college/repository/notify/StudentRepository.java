package com.college.repository.notify;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.login.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	// Get all students
    List<Student> findAll();

    // Department wise
    List<Student> findByDepartment(String department);

    // Department + Year
    List<Student> findByDepartmentAndYear(String department, String year);
    
    Optional<Student> findByPrn(Long prn);
}

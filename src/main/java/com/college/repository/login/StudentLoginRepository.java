package com.college.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.login.Student;

@Repository
public interface StudentLoginRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByPrn(Long prn);

    boolean existsByPrn(Long prn);
    
    boolean existsByEmail(String email);
}

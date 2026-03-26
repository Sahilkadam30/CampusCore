package com.college.repository.newStudentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.newstudent.StudentPersonalDetails;

@Repository
public interface StudentPersonalRepository extends JpaRepository<StudentPersonalDetails, Long> {
    StudentPersonalDetails findByPrnNumber(Long prnNumber);
    boolean existsByPrnNumber(Long long1);
}

package com.college.repository.newStudentRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.newstudent.CollegeDetails;

public interface StudentCollegeDetailsRepository extends JpaRepository<CollegeDetails, Long>{

}

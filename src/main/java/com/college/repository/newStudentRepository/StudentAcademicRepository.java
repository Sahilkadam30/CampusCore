package com.college.repository.newStudentRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.newstudent.StudentAcademicDetails;

public interface StudentAcademicRepository extends JpaRepository<StudentAcademicDetails, Long>{

}

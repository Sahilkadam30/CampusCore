package com.college.repository.academic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.academic.Syllabus;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long>{
	List<Syllabus> findBySemesterAndDepartment(String semester, String department);
}

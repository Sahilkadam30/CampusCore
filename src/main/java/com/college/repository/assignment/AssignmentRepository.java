package com.college.repository.assignment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.assignment.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long>{

	List<Assignment> findByDepartmentAndYear(String department,String year);
	
	List<Assignment> findTop2ByDepartmentAndYearOrderByIdDesc(String department,String year);
}

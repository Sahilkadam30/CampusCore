package com.college.repository.assignment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.assignment.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long>{

	List<Notes> findByDepartmentAndYear(String department,String year);
}

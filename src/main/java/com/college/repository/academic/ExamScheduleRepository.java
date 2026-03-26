package com.college.repository.academic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.academic.ExamSchedule;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long>{
	List<ExamSchedule> findByYear(String year);
	List<ExamSchedule> findByDepartmentIgnoreCaseAndYearIgnoreCase(String department, String year);
}

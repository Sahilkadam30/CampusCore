package com.college.repository.academic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.academic.Result;

public interface ResultRepository extends JpaRepository<Result, Long>{
	List<Result> findByPrnAndYear(Long prn, String year);

    List<Result> findByPrnAndYearAndSemester(Long prn, String year, String semester);
    List<Result> findByPrn(Long prn);
}

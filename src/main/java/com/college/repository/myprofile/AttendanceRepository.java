package com.college.repository.myprofile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.login.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByPrn(Long prn);

    List<Attendance> findByPrnAndMonth(Long prn, String month);

    List<Attendance> findByPrnAndMonthAndYear(Long prn, String month, String year);
    
}

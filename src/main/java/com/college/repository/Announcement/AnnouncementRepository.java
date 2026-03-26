package com.college.repository.Announcement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.Announcement.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{

	// All active announcements
    List<Announcement> findByActiveTrueOrderByPinnedDescCreatedAtDesc();

    // For all students
    List<Announcement> findByForAllStudentsTrueAndActiveTrue();

    // Department announcements
    List<Announcement> findByDepartmentAndActiveTrue(String department);

    // Department + Year announcements
    List<Announcement> findByDepartmentAndYearAndActiveTrue(String department, String year);
    
    List<Announcement> findAllByOrderByPinnedDescCreatedAtDesc();
    
    List<Announcement> findByPinnedTrueAndActiveTrueOrderByCreatedAtDesc();
}

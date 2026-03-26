package com.college.service.Announcement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.Announcement.Announcement;
import com.college.repository.Announcement.AnnouncementRepository;


@Service
public class AnnouncementService {

	@Autowired
    private AnnouncementRepository repo;

    // Save announcement
    public void saveAnnouncement(Announcement announcement){
        repo.save(announcement);
    }

    // Admin view
    public List<Announcement> getAllAnnouncements(){
        return repo.findByActiveTrueOrderByPinnedDescCreatedAtDesc();
    }

    // Student announcements
    public List<Announcement> getStudentAnnouncements(String department,String year){

        Set<Announcement> set = new LinkedHashSet<>();

        // All students
        set.addAll(repo.findByForAllStudentsTrueAndActiveTrue());

        // Department
        if (department != null) {
            set.addAll(repo.findByDepartmentAndActiveTrue(department));
        }

        // Department + Year
        if (department != null && year != null) {
            set.addAll(repo.findByDepartmentAndYearAndActiveTrue(department, year));
        }

        List<Announcement> list = new ArrayList<>(set);

        // remove pinned (shown separately)
        list.removeIf(Announcement::isPinned);

        // sort by latest
        list.sort((a,b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        return list;
    }
    
    public void deleteAnnouncement(Long id){
        repo.deleteById(id);
    }

    public Announcement getAnnouncementById(Long id){
        return repo.findById(id).orElse(null);
    }
    
    public List<Announcement> getPinnedAnnouncements(){
        return repo.findByPinnedTrueAndActiveTrueOrderByCreatedAtDesc();
    }
}

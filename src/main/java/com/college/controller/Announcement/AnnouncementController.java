package com.college.controller.Announcement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.college.entity.Announcement.Announcement;
import com.college.entity.login.Student;
import com.college.repository.notify.StudentRepository;
import com.college.service.Announcement.AnnouncementService;
import com.college.service.notify.NotificationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AnnouncementController {

	@Autowired
    private AnnouncementService service;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private StudentRepository studentRepo;

	 // Admin Page
    @GetMapping("/admin/announcements")
    public String adminPage(Model model){

        model.addAttribute("announcement", new Announcement());
        model.addAttribute("announcements", service.getAllAnnouncements());

        return "admin/admin-announcements";
    }

    // Save Announcement
    @PostMapping("/admin/save-announcement")
    public String saveAnnouncement(@ModelAttribute Announcement announcement,
                                   @RequestParam("file") MultipartFile file) {

        try {

        	String uploadDir = System.getProperty("user.dir") + "/uploads/announcement/";

        	Path uploadPath = Paths.get(uploadDir);

        	// create folder if not exists
        	if (!Files.exists(uploadPath)) {
        	    Files.createDirectories(uploadPath);
        	}

        	// get file name
        	String fileName = file.getOriginalFilename();

        	// copy file
        	Files.copy(
        	        file.getInputStream(),
        	        uploadPath.resolve(fileName),
        	        StandardCopyOption.REPLACE_EXISTING
        	);

        	// save to DB
        	announcement.setAttachment(fileName);
        	System.out.println("Saving file to: " + uploadPath.resolve(fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        service.saveAnnouncement(announcement);

     // 🔔 Notification Logic
        String message = "📢 New Announcement: " + announcement.getTitle();

        // 1️⃣ Notice for all students
        if (announcement.isForAllStudents()) {

            notificationService.sendToAll(message);

        }

        // 2️⃣ Department wise
        else if (announcement.getDepartment() != null && announcement.getYear() == null) {

            List<Student> students =
                    studentRepo.findByDepartment(announcement.getDepartment());

            for (Student s : students) {
                notificationService.sendToStudent(s.getPrn(), message);
            }

        }

        // 3️⃣ Department + Year
        else if (announcement.getDepartment() != null && announcement.getYear() != null) {

            List<Student> students =
                    studentRepo.findByDepartmentAndYear(
                            announcement.getDepartment(),
                            announcement.getYear()
                    );

            for (Student s : students) {
                notificationService.sendToStudent(s.getPrn(), message);
            }
        }
        return "redirect:/admin/announcements";
    }

    // Delete
    @GetMapping("/admin/delete-announcement/{id}")
    public String deleteAnnouncement(@PathVariable Long id){

        service.deleteAnnouncement(id);

        return "redirect:/admin/announcements";
    }

    // Edit Page
    @GetMapping("/admin/edit-announcement/{id}")
    public String editAnnouncement(@PathVariable Long id, Model model){

        Announcement a = service.getAnnouncementById(id);

        model.addAttribute("announcement", a);
        model.addAttribute("announcements", service.getAllAnnouncements());

        return "admin/admin-announcements";
    }

    // ================= STUDENT PAGE =================

    @GetMapping("/announcements")
    public String studentAnnouncements(Model model,HttpSession session){

        String department = (String) session.getAttribute("department");
        String year = (String) session.getAttribute("year");

        List<Announcement> pinned = service.getPinnedAnnouncements();
        List<Announcement> announcements = service.getStudentAnnouncements(department,year);

        model.addAttribute("pinned", pinned);
        model.addAttribute("announcements", announcements);

        return "Announcement/student-announcements";
    }
}

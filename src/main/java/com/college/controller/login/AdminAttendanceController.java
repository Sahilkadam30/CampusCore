package com.college.controller.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.entity.login.Attendance;
import com.college.service.login.AttendanceService;

@Controller
@RequestMapping("/admin")
public class AdminAttendanceController {

	@Autowired
    private AttendanceService service;

    @GetMapping("/attendance")
    public String adminPage(Model model){

        model.addAttribute("attendance", new ArrayList<>());
        return "attendence/admin-attendance";
    }
   
    @GetMapping("/student-attendance")
    public String viewStudent(@RequestParam Long prn, Model model){

        List<Attendance> list = service.getStudentAttendance(prn);
        model.addAttribute("attendance", list);

        return "attendence/admin-attendance";
    }
    
    @PostMapping("/uploadAttendance")
    public String uploadAttendance(@RequestParam("file") MultipartFile file,
                                   @RequestParam String month,
                                   @RequestParam String year,
                                   RedirectAttributes redirectAttributes) {

    	service.uploadExcel(file, month, year);

        redirectAttributes.addFlashAttribute("successMessage",
                "Attendance uploaded successfully!");

        return "redirect:/admin/attendance";
    }
    
    @GetMapping("/searchAttendance")
    public String searchAttendance(Long prn, String month, String year, Model model){

        List<Attendance> list =
        		service.getAttendanceByMonthYear(prn, month, year);

        model.addAttribute("attendanceList", list);
        model.addAttribute("prn", prn);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "attendence/admin-attendance";
    }
}

package com.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.college.dto.StudentRegistrationDTO;
import com.college.service.newstudentservice.StudentService;
import com.college.service.newstudentservice.getAllProfileService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class StudentController {

	@Autowired
    private final StudentService studentService;
	
	@Autowired
	private getAllProfileService getallprofileservice;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping("/newstudent")
    public String showForm(Model model) {
        model.addAttribute("student", new StudentRegistrationDTO());
        return "dashboard/new-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(
            @Valid @ModelAttribute("student") StudentRegistrationDTO dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "dashboard/new-student";
        }

        try {
            studentService.saveStudent(dto);
            model.addAttribute("success", "Student Registered Successfully");
            return "dashboard/new-student";

        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "dashboard/new-student";
        }
    }
    
    @GetMapping("/myprofile")
    public String viewProfile(HttpSession session, Model model) {

        Long prnno = (Long) session.getAttribute("prn");

        if (prnno == null) {
            return "redirect:/";
        }

        StudentRegistrationDTO student = getallprofileservice.getFullStudentProfile(prnno);

        if (student == null) {
            model.addAttribute("noData", true);   // ✅ important
        } else {
            model.addAttribute("student", student);
        }

        return "login/myprofile";
    }
}

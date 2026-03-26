package com.college.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.dto.StudentRegistrationDTO;
import com.college.entity.login.Student;
import com.college.service.login.StudentLoginService;
import com.college.service.newstudentservice.getAllProfileService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentLoginController {

	
	@Autowired
	private StudentLoginService studentloginservice;
	
	@Autowired
	private getAllProfileService getallprofileservice; 
	
	// Login Page
    @GetMapping("/")
    public String loginPage() {
        return "login/login";
    }

    // Register Page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("student", new Student());
        return "login/register";
    }
    
 // Register
    @PostMapping("/register")
    public String register(@ModelAttribute Student student,
                           @RequestParam String confirmPassword,
                           Model model) {

        if (!student.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "login/register";
        }

        String result = studentloginservice.registerStudent(student);

        if (result.equals("User already exists!") 
        	    || result.equals("PRN already exists!")
        	    || result.equals("Email already registered!")) {

        	    model.addAttribute("error", result);
        	    return "login/register";
        	}

        model.addAttribute("success", "Registration Successful! Please Login.");
        return "login/login";
    }
    
    // Login
    @PostMapping("/login")
    public String login(@RequestParam Long prn,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        Student student = studentloginservice.login(prn, password);

        if (student != null) {

            session.setAttribute("prn", student.getPrn());
            session.setAttribute("firstname", student.getFirstName());
            session.setAttribute("lastname", student.getLastName());
            session.setAttribute("department", student.getDepartment());
            session.setAttribute("year", student.getYear());
            session.setAttribute("email", student.getEmail());
            

            return "dashboard/firstpage.html";
        }

        model.addAttribute("error", "Invalid PRN or Password");
        return "login/login";
    }
    
    @PostMapping("/studentlogout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
	
}

package com.college.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.entity.login.Admin;
import com.college.entity.login.Role;
import com.college.service.login.AdminService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private AdminService adminService;

    // LOGIN PAGE
	@GetMapping("/login")
	public String showLoginPage() {
	    return "login/admin-login";   
	}

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		
		model.addAttribute("admin", new Admin());
	    return "login/admin-register";   
	}

    // REGISTER
    @PostMapping("/register")
    public String register(@ModelAttribute Admin admin,
                           @RequestParam String confirmPassword,
                           Model model) {

        String result = adminService.registerAdmin(admin, confirmPassword);

        if (!result.equals("Admin Registered Successfully!")) {
            model.addAttribute("error", result);
            return "login/admin-register"; 
        }

        model.addAttribute("success", result);
        return "login/admin-login";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestParam Long adminId,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Admin admin = adminService.login(adminId, password);

        if (admin != null) {

            session.setAttribute("adminId", admin.getAdminId());
            session.setAttribute("role", admin.getRole());

            return "dashboard/admin-dashboard.html";
        }

        model.addAttribute("error", "Invalid credentials");
        return "login/admin-login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/admin/login";
        }

        return "dashboard/admin-dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();   // 🔥 destroys session completely

        return "login/admin-login";
    }
}

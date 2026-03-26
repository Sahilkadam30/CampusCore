package com.college.controller.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.college.service.notify.NotificationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NotificationController {

	@Autowired
    private NotificationService service;

    @GetMapping("/notifications")
    public String viewNotifications(Model model,
                                    HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");

        if (prn == null) {
            return "redirect:/login";
        }

        model.addAttribute("notifications",
                service.getStudentNotifications(prn));

        return "notification/notification";
    }
}

package com.college.controller.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.college.entity.assignment.Assignment;
import com.college.entity.login.Attendance;
import com.college.repository.assignment.AssignmentRepository;
import com.college.service.assignment.AssignmentService;
import com.college.service.event.EventService;
import com.college.service.login.AttendanceService;
import com.college.service.notify.NotificationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@Autowired
    private NotificationService notificationService;

    @Autowired
    private EventService eventService;
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");
        String department = (String) session.getAttribute("department");
        String year = (String) session.getAttribute("year");
        
        if (prn == null) {
            return "redirect:/";
        }
        
        model.addAttribute("recentNotifications",
                notificationService.getRecentNotifications(prn));

        model.addAttribute("recentEvents",
                eventService.getRecentEvents());
        
        List<Assignment> assignments =
                assignmentService.getAssignments(department, year);

        model.addAttribute("recentAssignments",
                assignmentService.getRecentAssignments(department, year));

        List<Attendance> attendanceList = attendanceService.getAttendanceByPrn(prn);

        model.addAttribute("attendanceList", attendanceList);

        // Calculate overall attendance
        double total = 0;

        for (Attendance a : attendanceList) {
            total += a.getPercentage();
        }

        double avg = attendanceList.size() > 0 ? total / attendanceList.size() : 0;

        model.addAttribute("attendanceAvg", Math.round(avg));
        return "dashboard/dashboard";
    }
}

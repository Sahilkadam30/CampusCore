package com.college.controller.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.entity.login.Attendance;
import com.college.service.login.AttendanceService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentAttendanceController {

	@Autowired
    private AttendanceService service;

	@GetMapping("/attendance")
    public String studentAttendance(
            Model model,
            HttpSession session,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year) {

        Long prn = (Long) session.getAttribute("prn");

        if (prn == null) {
            return "redirect:/login";
        }

        List<Attendance> attendance;

        if (month != null && year != null && !month.isEmpty() && !year.isEmpty()) {

            attendance = service.getStudentAttendanceByMonthAndYear(prn, month, year);

        } else if (month != null && !month.isEmpty()) {

            attendance = service.getStudentAttendanceByMonth(prn, month);

        } else {

            attendance = service.getStudentAttendance(prn);
        }

        model.addAttribute("attendance", attendance);

        return "attendence/student-attendance";
    }
}

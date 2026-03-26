package com.college.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.college.entity.event.Event;
import com.college.entity.event.EventRegistration;
import com.college.service.event.EventService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EventController {

	@Autowired
    private EventService service;

    // Student View
	@GetMapping("/events")
	public String viewEvents(Model model, HttpSession session) {

	    Long prn = (Long) session.getAttribute("prn");

	    model.addAttribute("events", service.getAllEvents());
	    model.addAttribute("prn", prn);

	    return "event/event";
	}

    // Open Register Form
    @GetMapping("/registerEvent/{id}")
    public String registerForm(@PathVariable Long id,
                               Model model,
                               HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");
        String firstName = (String) session.getAttribute("firstname");
        String lastName = (String) session.getAttribute("lastname");

        EventRegistration reg = new EventRegistration();
        reg.setPrn(prn);
        reg.setName(firstName + " " + lastName);

        model.addAttribute("eventId", id);
        model.addAttribute("registration", reg);

        return "event/registerForm";
    }

    // Submit Registration
    @PostMapping("/registerEvent/{id}")
    public String registerSubmit(@PathVariable Long id,
                                 @ModelAttribute EventRegistration reg,
                                 Model model) {

        String result = service.registerStudent(reg, id);

        if (!result.equals("Success")) {
            model.addAttribute("error", result);
            return "event/registerForm";
        }

        return "redirect:/events";
    }

    // Admin Page
    @GetMapping("/admin/events")
    public String adminPage(Model model) {
        model.addAttribute("events", service.getAllEvents());
        model.addAttribute("event", new Event());
        return "admin/adminEvent";
    }

    // Add Event
    @PostMapping("/admin/addEvent")
    public String addEvent(@ModelAttribute Event event) {
        service.addEvent(event);
        return "redirect:/admin/events";
    }

    // Delete Event
    @GetMapping("/admin/deleteEvent/{id}")
    public String deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
        return "redirect:/admin/events";
    }
    
    @GetMapping("/admin/event/{id}/students")
    public String viewStudents(@PathVariable Long id, Model model) {

        model.addAttribute("students", service.getRegistrations(id));
        return "admin/studentList";
    }
}

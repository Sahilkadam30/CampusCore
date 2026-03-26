package com.college.controller.assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.college.entity.assignment.Assignment;
import com.college.service.assignment.AssignmentService;
import com.college.service.assignment.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudyMaterialController {

	@Autowired
    private AssignmentService assignmentService;

    @Autowired
    private NotesService notesService;

    @GetMapping("/study-material")
    public String studyMaterial(Model model, HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");
        String department = (String) session.getAttribute("department");
        String year = (String) session.getAttribute("year");

        List<Assignment> assignments =
                assignmentService.getAssignments(department, year);

        Map<Long, Boolean> completedMap = new HashMap<>();

        for (Assignment a : assignments) {
            completedMap.put(a.getId(),
                    assignmentService.isCompleted(a.getId(), prn));
        }

        model.addAttribute("assignments", assignments);
        model.addAttribute("completedMap", completedMap);

        model.addAttribute("notes",
                notesService.getNotes(department, year));

        return "assignment/study_material";
    }


    @GetMapping("/completeAssignment/{id}")
    public String completeAssignment(@PathVariable Long id,
                                     HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");

        assignmentService.completeAssignment(id, prn);

        return "redirect:/study-material";
    }
}

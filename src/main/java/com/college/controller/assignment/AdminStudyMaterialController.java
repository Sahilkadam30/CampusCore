package com.college.controller.assignment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.entity.assignment.Assignment;
import com.college.entity.assignment.Notes;
import com.college.service.assignment.AssignmentService;
import com.college.service.assignment.NotesService;
import com.college.service.notify.NotificationService;

@Controller
@RequestMapping("/admin")
public class AdminStudyMaterialController {

	@Autowired
    private AssignmentService assignmentService;

    @Autowired
    private NotesService notesService;

    @Autowired
    private NotificationService notificationService;
    
    @GetMapping("/study-material")
    public String adminStudyMaterialPage(Model model) {

        model.addAttribute("assignment", new Assignment());
        model.addAttribute("notes", new Notes());

        model.addAttribute("assignments",
                assignmentService.getAllAssignments());

        return "assignment/admin_study_material";
    }

    // ================= SAVE ASSIGNMENT =================

    @PostMapping("/saveAssignment")
    public String saveAssignment(@ModelAttribute Assignment assignment,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

        try {

            String fileName = file.getOriginalFilename();

            Path uploadPath = Paths.get("uploads/assignments");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path path = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), path,
                    StandardCopyOption.REPLACE_EXISTING);

            assignment.setFileName(fileName);
            assignment.setFilePath(path.toString());

            assignmentService.saveAssignment(assignment);

            // 🔔 SEND NOTIFICATION
            notificationService.sendToAll(
                    "New Assignment Uploaded: " + assignment.getSubject()
                    + " | Due Date: " + assignment.getDueDate()
            );
            
            redirectAttributes.addFlashAttribute("message",
                    "Assignment added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/study-material";
    }

    // ================= SAVE NOTES =================

    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes,
                            @RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

        try {

            String fileName = file.getOriginalFilename();

            Path uploadPath = Paths.get("uploads/notes");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path path = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), path,
                    StandardCopyOption.REPLACE_EXISTING);

            notes.setFileName(fileName);
            notes.setFilePath(path.toString());

            notesService.saveNotes(notes);

            // 🔔 SEND NOTIFICATION
            notificationService.sendToAll(
                    "New Notes Uploaded: " + notes.getTopic()
            );
            redirectAttributes.addFlashAttribute("message",
                    "Notes added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/study-material";
    }
    
    @GetMapping("/assignmentCompleted/{id}")
    public String viewCompletedStudents(@PathVariable Long id,
                                        Model model){

        model.addAttribute("students",
                assignmentService.getCompletedStudents(id));

        return "assignment/admin_completed_students";
    }
    
    @GetMapping("/deleteAssignment/{id}")
    public String deleteAssignment(@PathVariable Long id) {

    	assignmentService.deleteAssignment(id);

        return "redirect:/admin/study-material";
    }
}

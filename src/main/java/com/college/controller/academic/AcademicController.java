package com.college.controller.academic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.dto.ExamDTO;
import com.college.entity.academic.ExamSchedule;
import com.college.entity.academic.Result;
import com.college.entity.academic.Syllabus;
import com.college.service.academic.AcademicService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AcademicController {

    @Autowired
    private AcademicService service;

    // ================= STUDENT ACADEMIC PAGE =================
    @GetMapping("/academic")
    public String academicPage(Model model, HttpSession session,
                               @RequestParam(required = false) String year,
                               @RequestParam(required = false) String semester,
                               @RequestParam(required = false) String department) {

        Long prn = (Long) session.getAttribute("prn");

        if (prn == null) {
            return "redirect:/login";
        }

        String studentDept = (String) session.getAttribute("department");
        String studentYear = (String) session.getAttribute("year");

        model.addAttribute("exams",
                service.getExamByDeptAndYear(studentDept, studentYear));

        if (year != null) {
            model.addAttribute("results",
                    service.getResultByYear(prn, year));
        }

        if (semester != null && department != null) {
            model.addAttribute("syllabus",
                    service.getFilteredSyllabus(semester, department));
        }

        return "academic/academic";
    }


    // ================= ADMIN ACADEMIC DASHBOARD =================
    @GetMapping("/admin/academic")
    public String adminPage(Model model) {

        model.addAttribute("exams", service.getAllExams());
        model.addAttribute("results", service.getAllResults());
        model.addAttribute("syllabus", service.getAllSyllabus());

        model.addAttribute("examDTO", new ExamDTO());
        model.addAttribute("result", new Result());
        model.addAttribute("syllabusObj", new Syllabus());

        return "admin/adminAcademic";
    }


    // ================= EXAM MANAGEMENT =================
    @PostMapping("/admin/addExam")
    public String addExam(@ModelAttribute ExamDTO dto,
                          RedirectAttributes redirectAttributes) {

        service.addExam(dto);
        redirectAttributes.addFlashAttribute("success",
                "Exam schedule added successfully!");

        return "redirect:/admin/academic";
    }

    @GetMapping("/admin/deleteExam/{id}")
    public String deleteExam(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {

        service.deleteExam(id);
        redirectAttributes.addFlashAttribute("success",
                "Exam deleted successfully!");

        return "redirect:/admin/exam-timetable";
    }


    // ================= RESULT MANAGEMENT =================
    @PostMapping("/admin/addResult")
    public String addResult(@Valid @ModelAttribute Result result,
                            BindingResult resultBinding,
                            RedirectAttributes redirectAttributes) {

        if (resultBinding.hasErrors()) {
            return "admin/adminAcademic";
        }

        service.addResult(result);
        redirectAttributes.addFlashAttribute("success",
                "Result added successfully!");

        return "redirect:/admin/academic";
    }

    @GetMapping("/admin/deleteResult/{id}")
    public String deleteResult(@PathVariable Long id) {

        service.deleteResult(id);
        return "redirect:/admin/academic";
    }


    // ================= SYLLABUS MANAGEMENT =================
    @PostMapping("/admin/addSyllabus")
    public String addSyllabus(@ModelAttribute Syllabus syllabus,
                              RedirectAttributes redirectAttributes) {

        service.addSyllabus(syllabus);
        redirectAttributes.addFlashAttribute("success",
                "Syllabus uploaded successfully!");

        return "redirect:/admin/academic";
    }

    @GetMapping("/admin/deleteSyllabus/{id}")
    public String deleteSyllabus(@PathVariable Long id) {

        service.deleteSyllabus(id);
        return "redirect:/admin/academic";
    }


    // ================= RESULT PDF DOWNLOAD =================
    @GetMapping("/academic/result/pdf/{id}")
    public void downloadResultPdf(@PathVariable Long id,
                                  HttpServletResponse response) throws Exception {

        Result result = service.getResultById(id);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=result.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

        Paragraph college =
                new Paragraph("ABC College of Engineering", titleFont);
        college.setAlignment(Element.ALIGN_CENTER);
        document.add(college);

        Paragraph resultTitle =
                new Paragraph("Student Result", normalFont);
        resultTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(resultTitle);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("PRN: " + result.getPrn()));
        document.add(new Paragraph("Year: " + result.getYear()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(2);

        table.addCell("Subject");
        table.addCell("Marks");

        table.addCell(result.getSubject1());
        table.addCell(String.valueOf(result.getMarks1()));

        table.addCell(result.getSubject2());
        table.addCell(String.valueOf(result.getMarks2()));

        table.addCell(result.getSubject3());
        table.addCell(String.valueOf(result.getMarks3()));

        table.addCell(result.getSubject4());
        table.addCell(String.valueOf(result.getMarks4()));

        table.addCell(result.getSubject5());
        table.addCell(String.valueOf(result.getMarks5()));

        document.add(table);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Total: " + result.getTotal()));

        Double percentage = result.getPercentage();

        if (percentage != null) {
            document.add(new Paragraph("Percentage: " + percentage + "%"));
        } else {
            document.add(new Paragraph("Percentage: Not Available"));
        }

        String grade = service.calculateGrade(result.getPercentage());
        document.add(new Paragraph("Grade: " + grade));

        document.close();
    }


    // ================= SEARCH RESULT =================
    @GetMapping("/admin/searchResult")
    public String searchResult(@RequestParam Long prn, Model model) {

        model.addAttribute("searchResults",
                service.getStudentResults(prn));

        model.addAttribute("exams", service.getAllExams());
        model.addAttribute("results", service.getAllResults());
        model.addAttribute("syllabus", service.getAllSyllabus());

        model.addAttribute("examDTO", new ExamDTO());
        model.addAttribute("result", new Result());
        model.addAttribute("syllabusObj", new Syllabus());

        return "admin/adminAcademic";
    }


    // ================= EDIT RESULT =================
    @GetMapping("/admin/editResult/{id}")
    public String editResult(@PathVariable Long id, Model model) {

        Result result = service.getResultById(id);
        model.addAttribute("editResult", result);

        return "admin/editResult";
    }

    @PostMapping("/admin/updateResult")
    public String updateResult(@ModelAttribute Result result) {

        service.addResult(result);
        return "redirect:/admin/academic";
    }


    // ================= EDIT EXAM =================
    @GetMapping("/admin/editExam/{id}")
    public String editExam(@PathVariable Long id, Model model) {

        ExamSchedule exam = service.getExamById(id);
        model.addAttribute("exam", exam);

        return "admin/editExam";
    }

    @PostMapping("/admin/updateExam")
    public String updateExam(@ModelAttribute ExamSchedule exam,
                             RedirectAttributes redirectAttributes) {

        service.updateExam(exam);
        redirectAttributes.addFlashAttribute("success",
                "Exam updated successfully!");

        return "redirect:/admin/academic";
    }


    // ================= EXAM TIMETABLE =================
    @GetMapping("/admin/exam-timetable")
    public String showExamTimetable(Model model) {

        List<ExamSchedule> exams = service.getAllExams();
        model.addAttribute("exams", exams);

        return "admin/examTimetable";
    }

    @PostMapping("/admin/updateExamInline")
    public String updateExamInline(@ModelAttribute ExamSchedule exam,
                                   RedirectAttributes redirectAttributes) {

        service.updateExam(exam);
        redirectAttributes.addFlashAttribute("success",
                "Exam updated successfully!");

        return "redirect:/admin/exam-timetable";
    }
}

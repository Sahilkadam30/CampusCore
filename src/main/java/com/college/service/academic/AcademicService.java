package com.college.service.academic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dto.ExamDTO;
import com.college.dto.SubjectDTO;
import com.college.entity.academic.ExamSchedule;
import com.college.entity.academic.Result;
import com.college.entity.academic.Syllabus;
import com.college.repository.academic.ExamScheduleRepository;
import com.college.repository.academic.ResultRepository;
import com.college.repository.academic.SyllabusRepository;
import com.college.service.notify.NotificationService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AcademicService {

	@Autowired
    private ExamScheduleRepository examRepo;

    @Autowired
    private ResultRepository resultRepo;

    @Autowired
    private SyllabusRepository syllabusRepo;

    @Autowired
    private NotificationService notificationService;
    
    // Exam
    public List<ExamSchedule> getAllExams() {
        return examRepo.findAll();
    }

    public void addExam(ExamDTO dto) {

        if (dto.getSubjects() == null || dto.getSubjects().isEmpty()) {
            return;
        }

        for (SubjectDTO subject : dto.getSubjects()) {

            if (subject.getSubjectName() == null || subject.getSubjectName().isEmpty()) {
                continue;
            }

            for (String year : subject.getYear()) {

                ExamSchedule exam = new ExamSchedule(
                        dto.getDepartment(),
                        subject.getSubjectName(),
                        subject.getExamDate(),
                        year
                );

                List<ExamSchedule> exams = new ArrayList<>();
                exams.add(exam);

                examRepo.saveAll(exams);
            }
        }

        notificationService.sendToAll(
                "📢 New Exam Schedule Published for " + dto.getDepartment()
        );
    }
    
    public List<ExamSchedule> getExamByDeptAndYear(String department, String year) {
    	return examRepo.findByDepartmentIgnoreCaseAndYearIgnoreCase(department, year);
    }

    public void deleteExam(Long id) {
        examRepo.deleteById(id);
    }

    // Result
    public List<Result> getStudentResults(Long prn) {
        return resultRepo.findByPrn(prn);
    }
    
    public List<Result> getResultByYear(Long prn, String year) {
        return resultRepo.findByPrnAndYear(prn, year);
    }

    public void addResult(Result result) {

        int m1 = result.getMarks1();
        int m2 = result.getMarks2();
        int m3 = result.getMarks3();
        int m4 = result.getMarks4();
        int m5 = result.getMarks5();

        int total = m1 + m2 + m3 + m4 + m5;
        result.setTotal(total);

        double percentage = total / 5.0;
        result.setPercentage(percentage);

        resultRepo.save(result);
    }

    public void deleteResult(Long id) {
        resultRepo.deleteById(id);
    }
    
    public List<Result> getAllResults() {
        return resultRepo.findAll();
    }

    public Result getResultById(Long id) {
        return resultRepo.findById(id).orElse(null);
    }
    
    public String calculateGrade(double percentage) {

        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";

        return "Fail";
    }
    
    // Syllabus
    public List<Syllabus> getAllSyllabus() {
        return syllabusRepo.findAll();
    }
    
    public List<Syllabus> getFilteredSyllabus(String semester, String department) {
        return syllabusRepo.findBySemesterAndDepartment(semester, department);
    }

    public void addSyllabus(Syllabus syllabus) {
        syllabusRepo.save(syllabus);
    }

    public void deleteSyllabus(Long id) {
        syllabusRepo.deleteById(id);
    }
    
    public ExamSchedule getExamById(Long id) {
        return examRepo.findById(id).orElse(null);
    }

    public void updateExam(ExamSchedule exam) {
        examRepo.save(exam);
    }

}

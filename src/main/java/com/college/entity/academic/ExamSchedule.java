package com.college.entity.academic;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExamSchedule {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;   // NEW FIELD

    private String subjectName;

    private LocalDate examDate;

    private String year;  // First Year / Second Year / Third Year / Fourth Year

    public ExamSchedule() {}

    public ExamSchedule(String department, String subjectName,
                        LocalDate examDate, String year) {
        this.department = department;
        this.subjectName = subjectName;
        this.examDate = examDate;
        this.year = year;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}

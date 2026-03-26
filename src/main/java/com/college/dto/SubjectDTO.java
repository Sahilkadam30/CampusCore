package com.college.dto;

import java.time.LocalDate;
import java.util.List;

public class SubjectDTO {

	private String subjectName;
    private LocalDate examDate;
    private List<String> year;
    
	public SubjectDTO() {

	}

	public SubjectDTO(String subjectName, LocalDate examDate, List<String> year) {
		super();
		this.subjectName = subjectName;
		this.examDate = examDate;
		this.year = year;
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

	public List<String> getYear() {
		return year;
	}

	public void setYear(List<String> year) {
		this.year = year;
	}
}

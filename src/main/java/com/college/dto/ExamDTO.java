package com.college.dto;

import java.time.LocalDate;
import java.util.List;

public class ExamDTO {

	private String department;
    private List<SubjectDTO> subjects;
    
	public ExamDTO() {

	}

	public ExamDTO(String department, List<SubjectDTO> subjects) {
		super();
		this.department = department;
		this.subjects = subjects;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
}

package com.college.entity.academic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Result {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prn;

    private String year;
    private String semester;     
    private String department;   

    private String subject1;
    private int marks1;

    private String subject2;
    private int marks2;

    private String subject3;
    private int marks3;

    private String subject4;
    private int marks4;

    private String subject5;
    private int marks5;

    private int total;
    private Double percentage;

	public Result() {

	}

	public Result(Long id, Long prn, String year, String semester, String department, String subject1, int marks1,
			String subject2, int marks2, String subject3, int marks3, String subject4, int marks4, String subject5,
			int marks5, int total, Double percentage) {
		super();
		this.id = id;
		this.prn = prn;
		this.year = year;
		this.semester = semester;
		this.department = department;
		this.subject1 = subject1;
		this.marks1 = marks1;
		this.subject2 = subject2;
		this.marks2 = marks2;
		this.subject3 = subject3;
		this.marks3 = marks3;
		this.subject4 = subject4;
		this.marks4 = marks4;
		this.subject5 = subject5;
		this.marks5 = marks5;
		this.total = total;
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrn() {
		return prn;
	}

	public void setPrn(Long prn) {
		this.prn = prn;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubject1() {
		return subject1;
	}

	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}

	public int getMarks1() {
		return marks1;
	}

	public void setMarks1(int marks1) {
		this.marks1 = marks1;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public int getMarks2() {
		return marks2;
	}

	public void setMarks2(int marks2) {
		this.marks2 = marks2;
	}

	public String getSubject3() {
		return subject3;
	}

	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	public int getMarks3() {
		return marks3;
	}

	public void setMarks3(int marks3) {
		this.marks3 = marks3;
	}

	public String getSubject4() {
		return subject4;
	}

	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}

	public int getMarks4() {
		return marks4;
	}

	public void setMarks4(int marks4) {
		this.marks4 = marks4;
	}

	public String getSubject5() {
		return subject5;
	}

	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}

	public int getMarks5() {
		return marks5;
	}

	public void setMarks5(int marks5) {
		this.marks5 = marks5;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
}

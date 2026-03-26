package com.college.entity.newstudent;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_academic_details")
public class StudentAcademicDetails {

    @Id
    private Long prnNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "prn_number")
    private StudentPersonalDetails studentPersonalDetails;
    
    private String tenthSchoolName;
    private String tenthSchoolAddress;
    private Double tenthMarks;
    private String tenthGrade;

    private String twelfthCollegeName;
    private String twelfthCollegeAddress;
    private Double twelfthMarks;
    private String twelfthGrade;

    private String course;
    private String year;
    private String part;
    private LocalDate admissionDate;

	public StudentAcademicDetails() {

	}

	public StudentAcademicDetails(Long prnNumber, com.college.entity.newstudent.StudentPersonalDetails studentPersonalDetails,
			String tenthSchoolName, String tenthSchoolAddress, Double tenthMarks, String tenthGrade,
			String twelfthCollegeName, String twelfthCollegeAddress, Double twelfthMarks, String twelfthGrade,
			String course, String year, String part, LocalDate admissionDate) {
		super();
		this.prnNumber = prnNumber;
		this.studentPersonalDetails = studentPersonalDetails;
		this.tenthSchoolName = tenthSchoolName;
		this.tenthSchoolAddress = tenthSchoolAddress;
		this.tenthMarks = tenthMarks;
		this.tenthGrade = tenthGrade;
		this.twelfthCollegeName = twelfthCollegeName;
		this.twelfthCollegeAddress = twelfthCollegeAddress;
		this.twelfthMarks = twelfthMarks;
		this.twelfthGrade = twelfthGrade;
		this.course = course;
		this.year = year;
		this.part = part;
		this.admissionDate = admissionDate;
	}

	public Long getPrnNumber() {
		return prnNumber;
	}

	public void setPrnNumber(Long prnNumber) {
		this.prnNumber = prnNumber;
	}

	public StudentPersonalDetails getStudentPersonalDetails() {
		return studentPersonalDetails;
	}

	public void setStudentPersonalDetails(StudentPersonalDetails studentPersonalDetails) {
		this.studentPersonalDetails = studentPersonalDetails;
	}

	public String getTenthSchoolName() {
		return tenthSchoolName;
	}

	public void setTenthSchoolName(String tenthSchoolName) {
		this.tenthSchoolName = tenthSchoolName;
	}

	public String getTenthSchoolAddress() {
		return tenthSchoolAddress;
	}

	public void setTenthSchoolAddress(String tenthSchoolAddress) {
		this.tenthSchoolAddress = tenthSchoolAddress;
	}

	public Double getTenthMarks() {
		return tenthMarks;
	}

	public void setTenthMarks(Double tenthMarks) {
		this.tenthMarks = tenthMarks;
	}

	public String getTenthGrade() {
		return tenthGrade;
	}

	public void setTenthGrade(String tenthGrade) {
		this.tenthGrade = tenthGrade;
	}

	public String getTwelfthCollegeName() {
		return twelfthCollegeName;
	}

	public void setTwelfthCollegeName(String twelfthCollegeName) {
		this.twelfthCollegeName = twelfthCollegeName;
	}

	public String getTwelfthCollegeAddress() {
		return twelfthCollegeAddress;
	}

	public void setTwelfthCollegeAddress(String twelfthCollegeAddress) {
		this.twelfthCollegeAddress = twelfthCollegeAddress;
	}

	public Double getTwelfthMarks() {
		return twelfthMarks;
	}

	public void setTwelfthMarks(Double twelfthMarks) {
		this.twelfthMarks = twelfthMarks;
	}

	public String getTwelfthGrade() {
		return twelfthGrade;
	}

	public void setTwelfthGrade(String twelfthGrade) {
		this.twelfthGrade = twelfthGrade;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
}

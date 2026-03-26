package com.college.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentRegistrationDTO {

	// College
    private String collegeName;
    private String collegeCode;
    private String collegeAddress;
    private String collegeCity;
    private String collegePincode;

    // Personal
    @NotNull(message = "PRN Number is required")
    private Long prnNumber;

    private String firstName;

    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phone;
    private String aadharNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private String pincode;

    // Academic
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
    
	public StudentRegistrationDTO() {

	}

	

	public StudentRegistrationDTO(String collegeName, String collegeCode, String collegeAddress, String collegeCity,
			String collegePincode, Long prnNumber, String firstName, String lastName, String gender,
			LocalDate dateOfBirth, String phone, String aadharNumber, String email, String address, String city,
			String state, String pincode, String tenthSchoolName, String tenthSchoolAddress, Double tenthMarks,
			String tenthGrade, String twelfthCollegeName, String twelfthCollegeAddress, Double twelfthMarks,
			String twelfthGrade, String course, String year, String part, LocalDate admissionDate) {
		super();
		this.collegeName = collegeName;
		this.collegeCode = collegeCode;
		this.collegeAddress = collegeAddress;
		this.collegeCity = collegeCity;
		this.collegePincode = collegePincode;
		this.prnNumber = prnNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.aadharNumber = aadharNumber;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
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



	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public String getCollegeAddress() {
		return collegeAddress;
	}

	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}

	public String getCollegeCity() {
		return collegeCity;
	}

	public void setCollegeCity(String collegeCity) {
		this.collegeCity = collegeCity;
	}

	public String getCollegePincode() {
		return collegePincode;
	}

	public void setCollegePincode(String collegePincode) {
		this.collegePincode = collegePincode;
	}

	public Long getPrnNumber() {
		return prnNumber;
	}

	public void setPrnNumber(Long prnNumber) {
		this.prnNumber = prnNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

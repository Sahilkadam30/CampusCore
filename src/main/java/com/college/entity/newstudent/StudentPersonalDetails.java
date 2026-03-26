package com.college.entity.newstudent;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_personal_details")
public class StudentPersonalDetails {

    @Id
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

    @OneToOne(mappedBy = "studentPersonalDetails", cascade = CascadeType.ALL)
    private StudentAcademicDetails studentacademicdetails;

    @OneToOne(mappedBy = "studentPersonalDetails", cascade = CascadeType.ALL)
    private CollegeDetails collegedetails;

	public StudentPersonalDetails() {

	}

	public StudentPersonalDetails(Long prn, String firstName, String lastName, String gender, LocalDate dateOfBirth,
			String phone, String aadharNumber, String email, String address, String city, String state, String pincode,
			StudentAcademicDetails studentacademicdetails, CollegeDetails collegedetails) {
		super();
		this.prnNumber = prn;
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
		this.studentacademicdetails = studentacademicdetails;
		this.collegedetails = collegedetails;
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

	public StudentAcademicDetails getStudentacademicdetails() {
		return studentacademicdetails;
	}

	public void setStudentacademicdetails(StudentAcademicDetails studentacademicdetails) {
		this.studentacademicdetails = studentacademicdetails;
	}

	public CollegeDetails getCollegedetails() {
		return collegedetails;
	}

	public void setCollegedetails(CollegeDetails collegedetails) {
		this.collegedetails = collegedetails;
	} 
}

package com.college.entity.newstudent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "college_details")
public class CollegeDetails {

    @Id
    private Long prnNumber;

    private String collegeName;
    private String collegeCode;
    private String address;
    private String city;
    private String pincode;
    
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "prn_number")
    private StudentPersonalDetails studentPersonalDetails;
    
	public CollegeDetails() {

	}

	public CollegeDetails(Long prnNumber, String collegeName, String collegeCode, String address, String city, String pincode,
			com.college.entity.newstudent.StudentPersonalDetails studentPersonalDetails) {
		super();
		this.prnNumber = prnNumber;
		this.collegeName = collegeName;
		this.collegeCode = collegeCode;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.studentPersonalDetails = studentPersonalDetails;
	}

	public Long getPrnNumber() {
		return prnNumber;
	}

	public void setPrnNumber(Long prnNumber) {
		this.prnNumber = prnNumber;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public StudentPersonalDetails getStudentPersonalDetails() {
		return studentPersonalDetails;
	}

	public void setStudentPersonalDetails(StudentPersonalDetails studentPersonalDetails) {
		this.studentPersonalDetails = studentPersonalDetails;
	}
}

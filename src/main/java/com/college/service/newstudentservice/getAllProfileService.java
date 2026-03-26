package com.college.service.newstudentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dto.StudentRegistrationDTO;
import com.college.entity.newstudent.CollegeDetails;
import com.college.entity.newstudent.StudentAcademicDetails;
import com.college.entity.newstudent.StudentPersonalDetails;
import com.college.repository.newStudentRepository.StudentAcademicRepository;
import com.college.repository.newStudentRepository.StudentCollegeDetailsRepository;
import com.college.repository.newStudentRepository.StudentPersonalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class getAllProfileService {

	 @Autowired
	    private StudentPersonalRepository personalrepo;

	    @Autowired
	    private StudentCollegeDetailsRepository collegerepo;

	    @Autowired
	    private StudentAcademicRepository academicrepo;

	    public StudentRegistrationDTO getFullStudentProfile(Long prn) {

	        StudentPersonalDetails personal = personalrepo.findById(prn).orElse(null);
	        StudentAcademicDetails academic = academicrepo.findById(prn).orElse(null);
	        CollegeDetails college = collegerepo.findById(prn).orElse(null);

	        if (personal == null) {
	            return null;
	        }

	        StudentRegistrationDTO dto = new StudentRegistrationDTO();

	        // Personal
	        dto.setPrnNumber(personal.getPrnNumber());
	        dto.setFirstName(personal.getFirstName());
	        dto.setLastName(personal.getLastName());
	        dto.setGender(personal.getGender());
	        dto.setDateOfBirth(personal.getDateOfBirth());
	        dto.setPhone(personal.getPhone());
	        dto.setAadharNumber(personal.getAadharNumber());
	        dto.setEmail(personal.getEmail());
	        dto.setAddress(personal.getAddress());
	        dto.setCity(personal.getCity());
	        dto.setState(personal.getState());
	        dto.setPincode(personal.getPincode());

	        // College
	        if(college != null){
	            dto.setCollegeName(college.getCollegeName());
	            dto.setCollegeCode(college.getCollegeCode());
	            dto.setCollegeAddress(college.getAddress());
	            dto.setCollegeCity(college.getCity());
	            dto.setCollegePincode(college.getPincode());
	        }

	        // Academic
	        if(academic != null){
	            dto.setTenthSchoolName(academic.getTenthSchoolName());
	            dto.setTenthSchoolAddress(academic.getTenthSchoolAddress());
	            dto.setTenthMarks(academic.getTenthMarks());
	            dto.setTenthGrade(academic.getTenthGrade());

	            dto.setTwelfthCollegeName(academic.getTwelfthCollegeName());
	            dto.setTwelfthCollegeAddress(academic.getTwelfthCollegeAddress());
	            dto.setTwelfthMarks(academic.getTwelfthMarks());
	            dto.setTwelfthGrade(academic.getTwelfthGrade());

	            dto.setCourse(academic.getCourse());
	            dto.setYear(academic.getYear());
	            dto.setPart(academic.getPart());
	            dto.setAdmissionDate(academic.getAdmissionDate());
	        }

	        return dto;
	    }
}

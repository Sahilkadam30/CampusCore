package com.college.service.newstudentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dto.StudentRegistrationDTO;
import com.college.entity.newstudent.CollegeDetails;
import com.college.entity.newstudent.StudentAcademicDetails;
import com.college.entity.newstudent.StudentPersonalDetails;
import com.college.repository.newStudentRepository.StudentPersonalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentPersonalRepository personalRepo;

    @Autowired
    public StudentServiceImpl(StudentPersonalRepository personalRepo) {
        this.personalRepo = personalRepo;
    }
    
    @Override
    public StudentPersonalDetails getStudentProfile(Long prnNumber) {
        return personalRepo.findByPrnNumber(prnNumber);
    }

    @Override
    public void saveStudent(StudentRegistrationDTO dto) {

        if (personalRepo.existsByPrnNumber(dto.getPrnNumber())) {
            throw new RuntimeException("PRN already exists");
        }

        StudentPersonalDetails personal = new StudentPersonalDetails();
        personal.setPrnNumber(Long.valueOf(dto.getPrnNumber()));
        personal.setFirstName(dto.getFirstName());
        personal.setLastName(dto.getLastName());
        personal.setGender(dto.getGender());
        personal.setDateOfBirth(dto.getDateOfBirth());
        personal.setPhone(dto.getPhone());
        personal.setAadharNumber(dto.getAadharNumber());
        personal.setEmail(dto.getEmail());
        personal.setAddress(dto.getAddress());
        personal.setCity(dto.getCity());
        personal.setState(dto.getState());
        personal.setPincode(dto.getPincode());

        // ---- College ----
        CollegeDetails college = new CollegeDetails();
        college.setCollegeName(dto.getCollegeName());
        college.setCollegeCode(dto.getCollegeCode());
        college.setAddress(dto.getCollegeAddress());
        college.setCity(dto.getCollegeCity());
        college.setPincode(dto.getCollegePincode());

        // ---- Academic ----
        StudentAcademicDetails academic = new StudentAcademicDetails();
        academic.setTenthSchoolName(dto.getTenthSchoolName());
        academic.setTenthSchoolAddress(dto.getTenthSchoolAddress());
        academic.setTenthMarks(dto.getTenthMarks());
        academic.setTenthGrade(dto.getTenthGrade());
        academic.setTwelfthCollegeName(dto.getTwelfthCollegeName());
        academic.setTwelfthCollegeAddress(dto.getTwelfthCollegeAddress());
        academic.setTwelfthMarks(dto.getTwelfthMarks());
        academic.setTwelfthGrade(dto.getTwelfthGrade());
        academic.setCourse(dto.getCourse());
        academic.setYear(dto.getYear());
        academic.setPart(dto.getPart());
        academic.setAdmissionDate(dto.getAdmissionDate());

        // 🔥 IMPORTANT: Set BOTH sides
        personal.setCollegedetails(college);
        personal.setStudentacademicdetails(academic);

        college.setStudentPersonalDetails(personal);
        academic.setStudentPersonalDetails(personal);

        // Save only parent
        personalRepo.save(personal);
    }
    
    
}

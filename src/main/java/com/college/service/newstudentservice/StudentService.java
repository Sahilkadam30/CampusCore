package com.college.service.newstudentservice;

import com.college.dto.StudentRegistrationDTO;
import com.college.entity.newstudent.StudentPersonalDetails;

public interface StudentService {
    StudentPersonalDetails getStudentProfile(Long prnNumber);
    void saveStudent(StudentRegistrationDTO dto);
}

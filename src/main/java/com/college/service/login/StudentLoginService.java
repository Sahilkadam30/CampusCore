package com.college.service.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.entity.login.Role;
import com.college.entity.login.Student;
import com.college.repository.login.StudentLoginRepository;

@Service
public class StudentLoginService {

	@Autowired
	private StudentLoginRepository studentLoginRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	// Register
	public String registerStudent(Student student) {

	    if (studentLoginRepository.existsByPrn(student.getPrn())) {
	        return "PRN already exists!";
	    }

	    if (studentLoginRepository.existsByEmail(student.getEmail())) {
	        return "Email already registered!";
	    }

	    // Encrypt password
	    student.setPassword(passwordEncoder.encode(student.getPassword()));

	    student.setRole(Role.STUDENT);
	    studentLoginRepository.save(student);

	    return "Registered Successfully!";
	}
    
    // Login
	public Student login(Long prn, String rawPassword) {

	    Optional<Student> optional = studentLoginRepository.findByPrn(prn);

	    if (optional.isPresent()) {

	        Student student = optional.get();

	        if (passwordEncoder.matches(rawPassword, student.getPassword())) {
	            return student;   // return full student object
	        }
	    }

	    return null;  //  login failed
	}
}

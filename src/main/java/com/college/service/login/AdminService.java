package com.college.service.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.entity.login.Admin;
import com.college.repository.login.AdminRepository;

@Service
public class AdminService {

	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final String SECRET_KEY = "ADMIN123"; // 🔐 change this

    // REGISTER
    public String registerAdmin(Admin admin, String confirmPassword) {

        if (!admin.getPassword().equals(confirmPassword)) {
            return "Passwords do not match!";
        }

        if (!admin.getSecretCode().equals(SECRET_KEY)) {
            return "Invalid Secret Code!";
        }

        if (adminRepository.existsByEmail(admin.getEmail())) {
            return "Email already exists!";
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        adminRepository.save(admin);

        return "Admin Registered Successfully!";
    }

    // LOGIN
    public Admin login(Long adminId, String password) {

        Optional<Admin> optional = adminRepository.findByAdminId(adminId);

        if (optional.isPresent()) {
            Admin admin = optional.get();

            if (passwordEncoder.matches(password, admin.getPassword())) {
                return admin;
            }
        }

        return null;
    }
}

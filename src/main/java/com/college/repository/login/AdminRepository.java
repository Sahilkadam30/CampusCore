package com.college.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.login.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByAdminId(Long adminId);

    boolean existsByEmail(String email);
}

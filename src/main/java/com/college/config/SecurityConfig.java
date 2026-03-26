package com.college.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())

	        .authorizeHttpRequests(auth -> auth
	            // ✅ PUBLIC ACCESS
	            .requestMatchers(
	                "/", 
	                "/login", 
	                "/register",
	                "/admin/login",
	                "/admin/register",
	                "/admin/logout"   
	            ).permitAll()


	            .anyRequest().permitAll()
	        )

	        .formLogin(form -> form.disable());

	    return http.build();
	}
}

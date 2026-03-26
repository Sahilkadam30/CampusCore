package com.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CampusCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusCoreApplication.class, args);
	}
}

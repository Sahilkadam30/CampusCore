package com.college.entity.login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attendance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prn;

    private String subject;

    private String month;

    private String year;   // First Year, Second Year, Third Year

    private int totalClasses;

    private int attendedClasses;

    private double percentage;

	public Attendance() {

	}

	public Attendance(Long id, Long prn, String subject, String month, String year, int totalClasses,
			int attendedClasses, double percentage) {
		super();
		this.id = id;
		this.prn = prn;
		this.subject = subject;
		this.month = month;
		this.year = year;
		this.totalClasses = totalClasses;
		this.attendedClasses = attendedClasses;
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrn() {
		return prn;
	}

	public void setPrn(Long prn) {
		this.prn = prn;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getTotalClasses() {
		return totalClasses;
	}

	public void setTotalClasses(int totalClasses) {
		this.totalClasses = totalClasses;
	}

	public int getAttendedClasses() {
		return attendedClasses;
	}

	public void setAttendedClasses(int attendedClasses) {
		this.attendedClasses = attendedClasses;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
}

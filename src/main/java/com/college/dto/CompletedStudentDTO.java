package com.college.dto;

public class CompletedStudentDTO {

	private Long prn;
    private String name;
    private String department;
    private String year;

    public CompletedStudentDTO(Long prn, String name,
                               String department, String year) {
        this.prn = prn;
        this.name = name;
        this.department = department;
        this.year = year;
    }

	public Long getPrn() {
		return prn;
	}

	public void setPrn(Long prn) {
		this.prn = prn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}

package com.college.entity.Announcement;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Announcement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String message;

    private String department;
    private String year;

    private boolean forAllStudents;

    private boolean pinned;          // 📌 important announcement

    private String priority;         // LOW / MEDIUM / HIGH

    private String attachment;       

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime expiryDate;

	public Announcement() {

	}

	public Announcement(Long id, String title, String message, String department, String year, boolean forAllStudents,
			boolean pinned, String priority, String attachment, boolean active, LocalDateTime createdAt,
			LocalDateTime expiryDate) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.department = department;
		this.year = year;
		this.forAllStudents = forAllStudents;
		this.pinned = pinned;
		this.priority = priority;
		this.attachment = attachment;
		this.active = active;
		this.createdAt = createdAt;
		this.expiryDate = expiryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public boolean isForAllStudents() {
		return forAllStudents;
	}

	public void setForAllStudents(boolean forAllStudents) {
		this.forAllStudents = forAllStudents;
	}

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Announcement)) return false;
	    Announcement that = (Announcement) o;
	    return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
}
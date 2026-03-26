package com.college.entity.gallery;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PostComment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prnNumber;
    private String studentName;
    private String comment;

    private LocalDateTime commentTime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private GalleryPost post;

	public PostComment() {

	}

	public PostComment(Long id, Long prnNumber, String studentName, String comment, LocalDateTime commentTime,
			GalleryPost post) {
		super();
		this.id = id;
		this.prnNumber = prnNumber;
		this.studentName = studentName;
		this.comment = comment;
		this.commentTime = commentTime;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrnNumber() {
		return prnNumber;
	}

	public void setPrnNumber(Long prnNumber) {
		this.prnNumber = prnNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}

	public GalleryPost getPost() {
		return post;
	}

	public void setPost(GalleryPost post) {
		this.post = post;
	}
}

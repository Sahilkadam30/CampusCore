package com.college.entity.gallery;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class GalleryPost {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prnNumber;
    private String studentName;

    private String caption;
    private LocalDateTime uploadDateTime;

    private boolean approved = false;

    @Lob
    @Column(columnDefinition = "LONGBLOB") // For MySQL
    private byte[] image;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> likes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> comments;

	public GalleryPost() {

	}

	public GalleryPost(Long id, Long prnNumber, String studentName, String caption, LocalDateTime uploadDateTime,
			boolean approved, byte[] image, List<PostLike> likes, List<PostComment> comments) {
		super();
		this.id = id;
		this.prnNumber = prnNumber;
		this.studentName = studentName;
		this.caption = caption;
		this.uploadDateTime = uploadDateTime;
		this.approved = approved;
		this.image = image;
		this.likes = likes;
		this.comments = comments;
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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public LocalDateTime getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(LocalDateTime uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<PostLike> getLikes() {
		return likes;
	}

	public void setLikes(List<PostLike> likes) {
		this.likes = likes;
	}

	public List<PostComment> getComments() {
		return comments;
	}

	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	} 
}

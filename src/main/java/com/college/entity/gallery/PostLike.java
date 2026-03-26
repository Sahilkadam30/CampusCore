package com.college.entity.gallery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "prnNumber"}))
public class PostLike {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prnNumber;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private GalleryPost post;

	public PostLike() {

	}

	public PostLike(Long id, Long prnNumber, GalleryPost post) {
		super();
		this.id = id;
		this.prnNumber = prnNumber;
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

	public GalleryPost getPost() {
		return post;
	}

	public void setPost(GalleryPost post) {
		this.post = post;
	}
}

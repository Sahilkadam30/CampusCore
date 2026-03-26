package com.college.repository.gallery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.gallery.GalleryPost;

public interface GalleryPostRepository extends JpaRepository<GalleryPost, Long> {

	List<GalleryPost> findByApprovedTrue();

    List<GalleryPost> findByApprovedFalse();
}

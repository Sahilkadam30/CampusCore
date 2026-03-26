package com.college.repository.gallery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.gallery.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
	List<PostComment> findByPostId(Long postId);
}

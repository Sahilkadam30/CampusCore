package com.college.repository.gallery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.gallery.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>{

	Optional<PostLike> findByPostIdAndPrnNumber(Long postId, Long prnNumber);
	long countByPostId(Long postId);
}

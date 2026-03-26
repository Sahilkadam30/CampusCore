package com.college.service.gallery;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.gallery.GalleryPost;
import com.college.entity.gallery.PostComment;
import com.college.entity.gallery.PostLike;
import com.college.repository.gallery.GalleryPostRepository;
import com.college.repository.gallery.PostCommentRepository;
import com.college.repository.gallery.PostLikeRepository;
import com.college.service.notify.NotificationService;

@Service
public class GalleryService {

    @Autowired
    private GalleryPostRepository postRepo;

    @Autowired
    private PostLikeRepository likeRepo;

    @Autowired
    private PostCommentRepository commentRepo;

    @Autowired
    private NotificationService notificationService;


    // Upload Post (Pending by default)
    public void uploadPost(byte[] image, Long prn, String name, String caption) {

        GalleryPost post = new GalleryPost();
        post.setImage(image);
        post.setPrnNumber(prn);
        post.setStudentName(name);
        post.setCaption(caption);
        post.setUploadDateTime(LocalDateTime.now());
        post.setApproved(false);

        postRepo.save(post);
    }


    // Show Approved Posts
    public List<GalleryPost> getApprovedPosts() {
        return postRepo.findByApprovedTrue();
    }


    public List<GalleryPost> getAllPosts() {
        return postRepo.findAll();
    }


    // Show Pending Posts (Admin)
    public List<GalleryPost> getPendingPosts() {
        return postRepo.findByApprovedFalse();
    }


    public GalleryPost getPostById(Long id) {
        return postRepo.findById(id).orElseThrow();
    }


    public void updatePost(Long id, String caption) {

        GalleryPost post = postRepo.findById(id).orElseThrow();
        post.setCaption(caption);

        postRepo.save(post);
    }


    // Approve Post
    public void approvePost(Long id) {

        GalleryPost post = postRepo.findById(id).orElseThrow();
        post.setApproved(true);

        postRepo.save(post);

        // 🔔 SEND NOTIFICATION TO STUDENT
        notificationService.sendToStudent(
                post.getPrnNumber(),
                "✅ Your gallery post has been approved!"
        );
    }


    // Delete Post
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }


    // Like Post (Only One Like Per Student)
    public void likePost(Long postId, Long prn) {

        if (likeRepo.findByPostIdAndPrnNumber(postId, prn).isEmpty()) {

            GalleryPost post = postRepo.findById(postId).orElseThrow();

            PostLike like = new PostLike();
            like.setPrnNumber(prn);
            like.setPost(post);

            likeRepo.save(like);

            // 🔔 SEND NOTIFICATION TO POST OWNER
            if (!post.getPrnNumber().equals(prn)) { // avoid self-like notification

                notificationService.sendToStudent(
                        post.getPrnNumber(),
                        "👍 Someone liked your gallery post!"
                );
            }
        }
    }


    // Add Comment
    public void addComment(Long postId, Long prn, String name, String text) {

        GalleryPost post = postRepo.findById(postId).orElseThrow();

        PostComment comment = new PostComment();
        comment.setPrnNumber(prn);

        if (name == null) {
            comment.setStudentName("Student");
        } else {
            comment.setStudentName(name);
        }

        comment.setComment(text);
        comment.setCommentTime(LocalDateTime.now());
        comment.setPost(post);

        commentRepo.save(comment);

        // 🔔 Notification
        if (!post.getPrnNumber().equals(prn)) {

            notificationService.sendToStudent(
                    post.getPrnNumber(),
                    "💬 " + comment.getStudentName() + " commented on your post!"
            );
        }
    }
}

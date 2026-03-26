package com.college.controller.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import com.college.entity.gallery.GalleryPost;
import com.college.service.gallery.GalleryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {

    @Autowired
    private GalleryService service;


    // View Gallery (Only Approved Posts)
    @GetMapping("/gallery")
    public String viewGallery(Model model) {

        model.addAttribute("posts", service.getApprovedPosts());

        return "gallery/gallery";
    }


    // Add Comment
    @PostMapping("/comment/{postId}")
    public String addComment(@PathVariable Long postId,
                             @RequestParam String comment,
                             HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");
        String firstName = (String) session.getAttribute("firstname");
        String lastName = (String) session.getAttribute("lastname");

        String fullName = firstName + " " + lastName;

        service.addComment(postId, prn, fullName, comment);

        return "redirect:/gallery";
    }


    // Like Post
    @PostMapping("/like/{id}")
    public String likePost(@PathVariable Long id,
                           HttpSession session) {

        Long prn = (Long) session.getAttribute("prn");

        service.likePost(id, prn);

        return "redirect:/gallery";
    }


    // Upload Post
    @PostMapping("/uploadPost")
    public String uploadPost(@RequestParam("image") MultipartFile file,
                             @RequestParam String caption,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) throws IOException {

        Long prn = (Long) session.getAttribute("prn");
        String firstName = (String) session.getAttribute("firstname"); 
        String lastName = (String) session.getAttribute("lastname"); 

        String fullName = firstName + " " + lastName;

        service.uploadPost(file.getBytes(), prn, fullName, caption);

        redirectAttributes.addFlashAttribute(
            "successMsg",
            "Upload successful.. The post goes to admin. We will reach you soon. Thanks for posting!"
        );

        return "redirect:/gallery";
    }


    // Show Image
    @GetMapping("/image/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {

        GalleryPost post = service.getPostById(id);

        return post.getImage();
    }
}

package com.college.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.service.gallery.GalleryService;
import com.college.service.notify.NotificationService;

@Controller
@RequestMapping("/admin")
public class AdminGalleryController {

    @Autowired
    private GalleryService service;
    
    @Autowired
    private NotificationService notificationService;

    // Show ALL posts
    @GetMapping("/pendingPosts")
    public String viewAllPosts(Model model) {
        model.addAttribute("posts", service.getAllPosts());
        return "gallery/pendingPosts";
    }

    // Approve
    @PostMapping("/approve/{id}")
    public String approvePost(@PathVariable Long id) {
        service.approvePost(id);
        return "redirect:/admin/pendingPosts";  
    }

    // Delete
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        service.deletePost(id);
        return "redirect:/admin/pendingPosts";   // ✅ FIXED
    }

    // Update Caption
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id,
                             @RequestParam String caption) {
        service.updatePost(id, caption);
        return "redirect:/admin/pendingPosts";   // ✅ FIXED
    }
}


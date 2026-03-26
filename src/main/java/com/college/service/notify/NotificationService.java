package com.college.service.notify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.notify.Notification;
import com.college.repository.notify.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
    private NotificationRepository repo;

	public List<Notification> getRecentNotifications(Long prn) {
        return repo.findTop2ByPrnOrPrnIsNullOrderByCreatedAtDesc(prn);
    }
	
    // Send to single student
    public void sendToStudent(Long prn, String message) {
        Notification n = new Notification();
        n.setPrn(prn);
        n.setMessage(message);
        repo.save(n);
    }

    // Broadcast to all students
    public void sendToAll(String message) {
        Notification n = new Notification();
        n.setPrn(null);  // Important for broadcast
        n.setMessage(message);
        repo.save(n);
    }

    // Get notifications for logged-in student
    public List<Notification> getStudentNotifications(Long prn) {
        return repo.findByPrnOrPrnIsNullOrderByCreatedAtDesc(prn);
    }

    // Mark notification as read
    public void markAsRead(Long id) {
        Notification n = repo.findById(id).orElse(null);
        if (n != null) {
            n.setReadStatus(true);
            repo.save(n);
        }
    }
}

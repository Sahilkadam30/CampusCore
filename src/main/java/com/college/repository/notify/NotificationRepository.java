package com.college.repository.notify;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.entity.notify.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByPrnOrPrnIsNullOrderByCreatedAtDesc(Long prn);
	
	List<Notification> findTop2ByPrnOrPrnIsNullOrderByCreatedAtDesc(Long prn);
}

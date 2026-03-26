package com.college.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.event.EventRegistration;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long>{

	boolean existsByPrnAndEventId(Long prn, Long eventId);

    int countByEventId(Long eventId);

    List<EventRegistration> findByEventId(Long eventId);
}

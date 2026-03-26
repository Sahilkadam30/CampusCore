package com.college.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.event.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findTop2ByOrderByEventDateDesc();
}

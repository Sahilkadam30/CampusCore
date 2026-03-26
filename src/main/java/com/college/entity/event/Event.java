package com.college.entity.event;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Event {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String venue;
    private LocalDate eventDate;
    private LocalDate lastDateToApply;
    @Column(length = 1000)
    private String details;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRegistration> registrations;

	public Event() {

	}

	public Event(Long id, String eventName, String venue, LocalDate eventDate, LocalDate lastDateToApply,
			String details, List<EventRegistration> registrations) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.venue = venue;
		this.eventDate = eventDate;
		this.lastDateToApply = lastDateToApply;
		this.details = details;
		this.registrations = registrations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalDate getLastDateToApply() {
		return lastDateToApply;
	}

	public void setLastDateToApply(LocalDate lastDateToApply) {
		this.lastDateToApply = lastDateToApply;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<EventRegistration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<EventRegistration> registrations) {
		this.registrations = registrations;
	}
}

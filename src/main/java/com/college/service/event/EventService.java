package com.college.service.event;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.event.Event;
import com.college.entity.event.EventRegistration;
import com.college.repository.event.EventRegistrationRepository;
import com.college.repository.event.EventRepository;
import com.college.service.notify.NotificationService;

import jakarta.transaction.Transactional;


@Service
public class EventService {

	@Autowired
    private EventRepository eventRepo;

    @Autowired
    private EventRegistrationRepository regRepo;
    
    @Autowired
    private NotificationService notificationService;

    public List<Event> getRecentEvents() {
        return eventRepo.findTop2ByOrderByEventDateDesc();
    } 
    // Get All Events
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    // Add Event
    public void addEvent(Event event) {
        eventRepo.save(event);
    }

    // Delete Event
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }

    // Register Student
    @Transactional
    public String registerStudent(EventRegistration formData, Long eventId) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getLastDateToApply().isBefore(LocalDate.now())) {
            return "Registration Closed!";
        }

        if (regRepo.existsByPrnAndEventId(formData.getPrn(), eventId)) {
            return "Already Registered!";
        }

        // 🔥 IMPORTANT: Create NEW object (Do NOT use form object directly)
        EventRegistration registration = new EventRegistration();
        registration.setName(formData.getName());
        registration.setPrn(formData.getPrn());
        registration.setYear(formData.getYear());
        registration.setBranch(formData.getBranch());
        registration.setEmail(formData.getEmail());
        registration.setEvent(event);

        regRepo.save(registration);
        
        notificationService.sendToStudent(
                formData.getPrn(),
                "🎉 You have successfully registered for " +
                event.getEventName()
        );

        return "Success";
    }

    // Check Already Registered
    public boolean alreadyRegistered(Long prn, Long eventId) {
        return regRepo.existsByPrnAndEventId(prn, eventId);
    }

    // Count
    public int getRegistrationCount(Long eventId) {
        return regRepo.countByEventId(eventId);
    }
    
    public List<EventRegistration> getRegistrations(Long eventId) {
        return regRepo.findByEventId(eventId);
    }
}

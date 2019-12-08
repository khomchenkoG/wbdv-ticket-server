package com.example.wbdvticketprojectserver.controllers;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.EventReview;
import com.example.wbdvticketprojectserver.models.Listing;
import com.example.wbdvticketprojectserver.repositories.EventRepository;
import com.example.wbdvticketprojectserver.repositories.EventReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EventReviewController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventReviewRepository eventReviewRepository;

    @PostMapping("/api/events/{eventId}/reviews")
    public List<EventReview> createEventReview
            (@RequestBody EventReview eventReview,
             @PathVariable("eventId") long eventId) {

        Event event = eventRepository.findEventById(eventId);
        eventReview.setEvent(event);
        eventReviewRepository.save(eventReview);
        return eventReviewRepository.findAllReviewsForEvent(eventId);
    }

    @GetMapping("/api/events/{eventId}/reviews")
    public List<EventReview> findAllReviewsForEvent(@PathVariable("eventId") long eventId) {
        return eventReviewRepository.findAllReviewsForEvent(eventId);
    }
}


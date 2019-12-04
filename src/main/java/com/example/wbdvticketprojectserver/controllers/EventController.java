package com.example.wbdvticketprojectserver.controllers;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.User;
import com.example.wbdvticketprojectserver.repositories.EventRepository;
import com.example.wbdvticketprojectserver.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    VenueRepository venueRepository;

    @PostMapping("/api/events")
    public Event createEvent
            (@Valid @RequestBody Event event) {
        event.setVenue(venueRepository.save(event.getVenue()));
        return eventRepository.save(event);
    }

    @GetMapping("/api/events")
    public List<Event> findAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @GetMapping("/api/events/{id}")
    public Event findEventById
            (@PathVariable("id") long id) {
        return eventRepository.findEventById(id);
    }

    @DeleteMapping("/api/events/{id}")
    public List<Event> deleteEventById
            (@PathVariable("id") long id) {
        Event e = eventRepository.findEventById(id);
        eventRepository.delete(e);
        return (List<Event>) eventRepository.findAll();
    }

}

package com.example.wbdvticketprojectserver.controllers;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.Listing;
import com.example.wbdvticketprojectserver.repositories.EventRepository;
import com.example.wbdvticketprojectserver.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ListingController {

    @Autowired
    ListingRepository listingRepository;

    @Autowired
    EventRepository eventRepository;

    @PostMapping("/api/events/{eventId}/listings")
    public List<Listing> createListing
            (@RequestBody Listing listing,
             @PathVariable("eventId") long eventId) {

        Event event = eventRepository.findEventById(eventId);
        listing.setEvent(event);
        listingRepository.save(listing);
        return listingRepository.findAllListingsForEvent(eventId);
    }

    @GetMapping("/api/events/{eventId}/listings")
    public List<Listing> findAllListingsForEvent(@PathVariable("eventId") long eventId) {
        return listingRepository.findAllListingsForEvent(eventId);
    }

    @GetMapping("/api/events/{eventId}/listings/{id}")
    public Listing findListingById
            (@PathVariable("id") long id,
             @PathVariable("eventId") long eventId) {
        return listingRepository.findListingById(id);
    }

    @DeleteMapping("/api/events/{eventId}/listings/{id}")
    public List<Listing> deleteListingById
            (@PathVariable("id") long id,
             @PathVariable("eventId") long eventId) {
        Listing l = listingRepository.findListingById(id);
        listingRepository.delete(l);
        return listingRepository.findAllListingsForEvent(eventId);
    }

}

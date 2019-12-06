package com.example.wbdvticketprojectserver.controllers;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.EventReview;
import com.example.wbdvticketprojectserver.models.User;
import com.example.wbdvticketprojectserver.repositories.EventRepository;
import com.example.wbdvticketprojectserver.repositories.EventReviewRepository;
import com.example.wbdvticketprojectserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")

public class UserController {

    List<User> courses = new ArrayList<>();

    @Autowired
    UserRepository repository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventReviewRepository eventReviewRepository;

    @GetMapping("/api/users/{username}")
    public User findUserByUsername
            (@PathVariable("username") String username) {
        return repository.findUserByUsername(username);
    }

    @GetMapping("/api/users/{username}/reviews")
    public List<EventReview> findReviewsForUser
            (@PathVariable("username") String username,
             @RequestParam(required = false) Long eventId){
        User u = repository.findUserByUsername(username);
        if (eventId != null){
            List<EventReview> toReturn = new ArrayList<>();
            for (EventReview er: u.getReviews()){
                if (er.getEvent().getId() == eventId){
                    toReturn.add(er);
                }
            }
            return toReturn;
        } else {
            return u.getReviews();
        }


    }

    @GetMapping("/api/users/{username}/reviews/{reviewId}")
    public EventReview findReviewByIdForUser
            (@PathVariable("username") String username,
             @PathVariable("reviewId") int reviewId){
        User u = repository.findUserByUsername(username);
        EventReview found = null;
        for (EventReview er: u.getReviews()){
            if (er.getId() == reviewId){
                found = er;
            }
        }
        return found;
    }


    @PostMapping("/api/users/{username}/reviews/{eventId}")
    public List<EventReview> createReviewForUser
            (@PathVariable("username") String username,
             @PathVariable("eventId") long eventId,
             @RequestBody EventReview review){
        User u = repository.findUserByUsername(username);
        Event e = eventRepository.findEventById(eventId);
        review.setUser(u);
        review.setEvent(e);
        eventReviewRepository.save(review);
        return u.getReviews();
    }

    @PutMapping ("/api/users/{username}/reviews/{reviewId}")
    public List<EventReview> deleteReviewForUser
            (@PathVariable("username") String username,
             @PathVariable("reviewId") Integer reviewId,
             @RequestBody EventReview review){
        User u = repository.findUserByUsername(username);
        Optional<EventReview> r = eventReviewRepository.findById(reviewId);
        if (r.isPresent()) {
            EventReview er = r.get();
            er.setReview(review.getReview());
            eventReviewRepository.save(er);
        }
        return u.getReviews();
    }

    @DeleteMapping ("/api/users/{username}/reviews/{reviewId}")
    public List<EventReview> deleteReviewForUser
            (@PathVariable("username") String username,
             @PathVariable("reviewId") Integer reviewId){
        User u = repository.findUserByUsername(username);
        Optional<EventReview> r = eventReviewRepository.findById(reviewId);

        u.getReviews().remove(r);
        repository.save(u);
        return u.getReviews();
    }

    @DeleteMapping("/api/users/{username}")
    public List<User> deleteUserByUsername
            (@PathVariable("username") String username) {
        User toDelete = repository.findUserByUsername(username);
        repository.delete(toDelete);
        return repository.findAllUsers();
    }


    @PostMapping("/api/users/{uId}/events/{eId}")
    public User followEvent(
            @PathVariable("uId") String uId,
            @PathVariable("eId") long eId) {
        User user = repository.findUserByUsername(uId);
        Event event = eventRepository.findEventById(eId);
        user.followEvent(event);
        repository.save(user);
        return repository.findUserByUsername(uId);
    }

    @DeleteMapping("/api/users/{uId}/events/{eId}")
    public User unfollowEvent(
            @PathVariable("uId") String uId,
            @PathVariable("eId") long eId) {
        User user = repository.findUserByUsername(uId);
        Event event = eventRepository.findEventById(eId);
        user.unFollowEvent(event);
        repository.save(user);
        return repository.findUserByUsername(uId);
    }

    @GetMapping("/api/users/{username}/events")
    public List<Event> findFollowedEvents
            (@PathVariable("username") String username) {
        User user =repository.findUserByUsername(username);
        return user.getEvents();
    }


    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return (List<User>) repository.findAll();
    }

    @PostMapping("/api/users")
    public User createUser
            (@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/api/users/{username}")
    public User updateUser(
            @PathVariable("username") String username,
            @RequestBody User newUser) {
        User user = repository.findUserByUsername(username);
        user.set(newUser);
        return repository.save(user);
    }
}

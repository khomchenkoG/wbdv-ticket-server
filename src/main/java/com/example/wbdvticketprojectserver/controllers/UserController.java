package com.example.wbdvticketprojectserver.controllers;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.User;
import com.example.wbdvticketprojectserver.repositories.EventRepository;
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

    @GetMapping("/api/users/{username}")
    public User findUserByUsername
            (@PathVariable("username") String username) {
        return repository.findUserByUsername(username);
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
        return repository.save(user);}
}

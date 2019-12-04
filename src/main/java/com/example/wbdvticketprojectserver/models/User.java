package com.example.wbdvticketprojectserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "followed_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    @JsonIgnore
    List<Event> events;

    public String getUsername() {
        return username;
    }

    public void followEvent(Event event) {
        this.events.add(event);
        if(!event.getFollowers()
                .contains(this)) {
            event.getFollowers()
                    .add(this);
        }}

    public void unFollowEvent(Event event) {
        this.events.remove(event);
        if(event.getFollowers()
                .contains(this)) {
            event.getFollowers()
                    .remove(this);
        }}


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void set(User newUser) {
        this.username = newUser.username;
        this.password = newUser.password;
        this.firstName = newUser.firstName;
        this.lastName = newUser.lastName;
    }
}

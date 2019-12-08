package com.example.wbdvticketprojectserver.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="events")
public class Event {
    @Id
    private long id;
    private String status;
    private String locale;
    private String name;
    private String description;
    private String webURI;
    private String eventDateLocal;
    private String eventDateUTC;
    private String createdDate;
    private String lastUpdatedDate;
    private String performers;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Listing> listings = new ArrayList<>();

    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    List<User> followers;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EventReview> reviews = new ArrayList<>();

    public List<EventReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<EventReview> reviews) {
        this.reviews = reviews;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public void addFollower(User user) {
        this.followers.add(user);
        if(!user.getEvents()
                .contains(this)) {
            user.getEvents()
                    .add(this);
        }}

    public void removeFollower(User user) {
        this.followers.remove(user);
        if(user.getEvents()
                .contains(this)) {
            user.getEvents()
                    .remove(this);
        }}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebURI() {
        return webURI;
    }

    public void setWebURI(String webURI) {
        this.webURI = webURI;
    }

    public String getEventDateLocal() {
        return eventDateLocal;
    }

    public void setEventDateLocal(String eventDateLocal) {
        this.eventDateLocal = eventDateLocal;
    }

    public String getEventDateUTC() {
        return eventDateUTC;
    }

    public void setEventDateUTC(String eventDateUTC) {
        this.eventDateUTC = eventDateUTC;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getPerformers() {
        return performers;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}

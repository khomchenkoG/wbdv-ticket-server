package com.example.wbdvticketprojectserver.repositories;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.User;
import com.example.wbdvticketprojectserver.models.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VenueRepository
        extends CrudRepository<Venue, Integer> {

    @Query("SELECT venue from Venue venue WHERE venue.id=:id")
    public Optional<Venue> findVenueById
            (@Param("id") long id);
}

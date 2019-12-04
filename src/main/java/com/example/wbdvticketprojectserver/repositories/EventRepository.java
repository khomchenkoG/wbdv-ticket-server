package com.example.wbdvticketprojectserver.repositories;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EventRepository
        extends CrudRepository<Event, Integer> {

    @Query("SELECT event from Event event WHERE event.id=:id")
    public Event findEventById(@Param("id") long id);
}

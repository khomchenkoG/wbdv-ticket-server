package com.example.wbdvticketprojectserver.repositories;

import com.example.wbdvticketprojectserver.models.EventReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventReviewRepository
        extends CrudRepository<EventReview, Integer> {

    @Query("select eventReview from EventReview eventReview where eventReview.event.id = :eid")
    public List<EventReview> findAllReviewsForEvent(@Param("eid") long eId);

}




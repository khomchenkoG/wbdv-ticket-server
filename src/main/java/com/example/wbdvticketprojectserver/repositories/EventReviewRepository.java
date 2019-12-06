package com.example.wbdvticketprojectserver.repositories;

import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.EventReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EventReviewRepository
        extends CrudRepository<EventReview, Integer> {

}


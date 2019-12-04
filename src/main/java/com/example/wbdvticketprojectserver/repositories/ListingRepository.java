package com.example.wbdvticketprojectserver.repositories;
import com.example.wbdvticketprojectserver.models.Event;
import com.example.wbdvticketprojectserver.models.Listing;
import com.example.wbdvticketprojectserver.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListingRepository
        extends CrudRepository<Listing, Integer> {

    @Query("SELECT listing from Listing listing WHERE listing.listingId=:id")
    public Listing findListingById(@Param("id") long id);

    @Query("select listing from Listing listing where listing.event.id = :eid")
    public List<Listing> findAllListingsForEvent(@Param("eid") long eId);
}

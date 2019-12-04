package com.example.wbdvticketprojectserver.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Listing {
    @Id
    private long listingId;
    private String listingRow;
    private int quantity;
    private String sellerSectionName;
    private String sectionName;
    private int isGA;
    private float price;
    private String currency;

    @ManyToOne
    @JsonIgnore
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public String getListingRow() {
        return listingRow;
    }

    public void setListingRow(String listingRow) {
        this.listingRow = listingRow;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellerSectionName() {
        return sellerSectionName;
    }

    public void setSellerSectionName(String sellerSectionName) {
        this.sellerSectionName = sellerSectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getIsGA() {
        return isGA;
    }

    public void setIsGA(int isGA) {
        this.isGA = isGA;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

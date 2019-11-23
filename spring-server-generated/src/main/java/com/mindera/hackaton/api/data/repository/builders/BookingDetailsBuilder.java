package com.mindera.hackaton.api.data.repository.builders;

import com.mindera.hackaton.api.model.BookingDetails;

import java.time.OffsetDateTime;

public final class BookingDetailsBuilder {
    private Integer id;
    private OffsetDateTime from;
    private OffsetDateTime to;
    private String description;
    private String by;

    private BookingDetailsBuilder() {
    }

    public static BookingDetailsBuilder aBookingDetails() {
        return new BookingDetailsBuilder();
    }

    public BookingDetailsBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public BookingDetailsBuilder withFrom(OffsetDateTime from) {
        this.from = from;
        return this;
    }

    public BookingDetailsBuilder withTo(OffsetDateTime to) {
        this.to = to;
        return this;
    }

    public BookingDetailsBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BookingDetailsBuilder withBy(String by) {
        this.by = by;
        return this;
    }

    public BookingDetails build() {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setId(id);
        bookingDetails.setFrom(from);
        bookingDetails.setTo(to);
        bookingDetails.setDescription(description);
        bookingDetails.setBy(by);
        return bookingDetails;
    }
}

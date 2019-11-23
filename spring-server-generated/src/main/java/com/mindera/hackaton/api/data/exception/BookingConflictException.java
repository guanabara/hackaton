package com.mindera.hackaton.api.data.exception;

import com.mindera.hackaton.api.model.BookingDetails;

public class BookingConflictException extends RuntimeException {
    private BookingDetails conflictBooking;

    public BookingConflictException(BookingDetails conflictBooking) {
        super();
        this.conflictBooking = conflictBooking;
    }

    public BookingDetails getConflictBooking() {
        return conflictBooking;
    }
}

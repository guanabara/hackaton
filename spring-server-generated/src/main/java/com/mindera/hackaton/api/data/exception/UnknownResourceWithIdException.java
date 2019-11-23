package com.mindera.hackaton.api.data.exception;

import com.mindera.hackaton.api.model.BookingDetails;

public class UnknownResourceWithIdException extends RuntimeException {
    private final Integer resourceId;

    public UnknownResourceWithIdException(Integer resourceId) {
        super();
        this.resourceId = resourceId;
    }

    public Integer getResourceId() {
        return resourceId;
    }
}

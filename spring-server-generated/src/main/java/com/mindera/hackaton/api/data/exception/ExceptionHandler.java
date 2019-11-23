package com.mindera.hackaton.api.data.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BookingConflictException.class)
    protected ResponseEntity<Object> handleConflict(BookingConflictException ex, WebRequest request) {
        String bodyOfResponse = ex.getConflictBooking().toString();

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownResourceWithIdException.class)
    protected ResponseEntity<Object> handleConflict(UnknownResourceWithIdException ex, WebRequest request) {
        return handleExceptionInternal(ex, "NOT FOUND",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}

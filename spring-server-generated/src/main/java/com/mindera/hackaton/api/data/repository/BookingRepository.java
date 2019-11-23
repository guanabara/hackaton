package com.mindera.hackaton.api.data.repository;

import com.mindera.hackaton.api.data.exception.BookingConflictException;
import com.mindera.hackaton.api.model.BookingDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Component
public class BookingRepository {

    private Map<Integer, List<BookingDetails>> knownBookings = new HashMap<>();

    public BookingRepository() {

    }

    public BookingDetails book(Integer roomId, BookingDetails details) {
        List<BookingDetails> roomBookings = this.knownBookings.getOrDefault(roomId, Collections.emptyList());

        if (!roomBookings.isEmpty()) {
            hasCollision(roomBookings, details).ifPresent(collision -> {
                throw new BookingConflictException(collision);
            });
        }

        details.setId(new Random().nextInt());

        this.knownBookings.putIfAbsent(roomId, new ArrayList<>());

        this.knownBookings.get(roomId).add(details);

        return details;
    }

    private Optional<BookingDetails> hasCollision(List<BookingDetails> roomBookings, BookingDetails details) {
        return roomBookings.stream()
                .filter(existing -> hasCollision(existing, details))
                .findFirst();
    }


    private boolean hasCollision(BookingDetails existing, BookingDetails toAdd) {
        return existing.getFrom().isBefore(toAdd.getTo()) && existing.getTo().isAfter(toAdd.getFrom());
    }
}

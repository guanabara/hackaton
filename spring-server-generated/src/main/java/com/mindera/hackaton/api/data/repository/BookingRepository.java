package com.mindera.hackaton.api.data.repository;

import com.mindera.hackaton.api.data.exception.BookingConflictException;
import com.mindera.hackaton.api.data.repository.builders.BookingDetailsBuilder;
import com.mindera.hackaton.api.model.BookingDetails;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
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

    public void occupyRoom(Integer roomId) {
        BookingDetails occupation = BookingDetailsBuilder.aBookingDetails()
                .withBy("Sensor detector")
                .withFrom(OffsetDateTime.now())
                .withDescription("Auto detected from room activity")
                .build();

        this.knownBookings.putIfAbsent(roomId, new ArrayList<>());

        this.knownBookings.get(roomId).add(occupation);
    }

    public void freeRoom(Integer roomId) {
        List<BookingDetails> roomBookings = this.knownBookings.getOrDefault(roomId, Collections.emptyList());
        roomBookings.removeIf(booking -> booking.getFrom().isBefore(OffsetDateTime.now()));
    }

    public Boolean isOccupied(Integer roomId) {
        List<BookingDetails> roomBookings = this.knownBookings.getOrDefault(roomId, Collections.emptyList());

        return roomBookings.stream().anyMatch(booking -> booking.getFrom().isBefore(OffsetDateTime.now()) &&
                        (booking.getTo() == null || booking.getTo().isAfter(OffsetDateTime.now()))
                );
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

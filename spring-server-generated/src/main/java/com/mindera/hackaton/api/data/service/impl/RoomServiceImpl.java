package com.mindera.hackaton.api.data.service.impl;

import com.mindera.hackaton.api.data.repository.BookingRepository;
import com.mindera.hackaton.api.data.repository.RoomRepository;
import com.mindera.hackaton.api.data.service.RoomService;
import com.mindera.hackaton.api.model.BookingDetails;
import com.mindera.hackaton.api.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepo;
    private BookingRepository bookingRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepo, BookingRepository bookingRepository) {

        this.roomRepo = roomRepo;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return this.roomRepo.getAllRooms();
    }

    @Override
    public BookingDetails bookRoom(Integer roomId, BookingDetails details) {
        return bookingRepository.book(roomId, details);
    }

    @Override
    public Room getById(Integer id) {
        return roomRepo.getById(id);
    }
}

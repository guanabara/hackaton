package com.mindera.hackaton.api.data.service;

import com.mindera.hackaton.api.model.BookingDetails;
import com.mindera.hackaton.api.model.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    BookingDetails bookRoom(Integer roomId, BookingDetails details);

    void occupyRoom(Integer roomId);
    void freeRoom(Integer roomId);

    Room getById(Integer id);
}

package com.mindera.hackaton.api.data.controller;

import com.mindera.hackaton.api.RoomsApi;
import com.mindera.hackaton.api.data.service.RoomService;
import com.mindera.hackaton.api.model.BookingDetails;
import com.mindera.hackaton.api.model.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@Api
public class RoomsController implements RoomsApi {

    private RoomService roomService;

    @Autowired
    public RoomsController(RoomService roomService) {
        this.roomService = roomService;
    }


    @Override
    @RequestMapping(value = "/rooms", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(this.roomService.getAllRooms());
    }

    @Override
    @RequestMapping(value = "/rooms/{id}/book/", produces = { "text/plain", "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<String> bookRoom(@ApiParam(value = "Room id",required=true) @PathVariable("id") Integer id, @ApiParam(value = "Book room details" ,required=true )  @Valid @RequestBody BookingDetails bookingDetails) {
        this.roomService.bookRoom(id, bookingDetails);

        return ResponseEntity.created(URI.create("/rooms/")).body("{ \"success\": true }");
    }

    @RequestMapping(value = "/rooms/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<Room> getById(@ApiParam(value = "Room id",required=true) @PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.roomService.getById(id));

    }
}

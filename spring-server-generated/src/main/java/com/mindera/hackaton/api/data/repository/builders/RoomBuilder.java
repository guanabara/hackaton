package com.mindera.hackaton.api.data.repository.builders;

import com.mindera.hackaton.api.model.Building;
import com.mindera.hackaton.api.model.Feature;
import com.mindera.hackaton.api.model.Room;
import com.mindera.hackaton.api.model.RoomStatus;

import java.util.List;

public final class RoomBuilder {
    private Integer id;
    private Integer floor;
    private Integer number;
    private String name;
    private String description;
    private List<Feature> features = null;
    private Building building = null;
    private RoomStatus status = null;

    private RoomBuilder() {
    }

    public static RoomBuilder aRoom() {
        return new RoomBuilder();
    }

    public RoomBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RoomBuilder withFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public RoomBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public RoomBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoomBuilder withBuilding(Building building) {
        this.building = building;
        return this;
    }

    public RoomBuilder withStatus(RoomStatus status) {
        this.status = status;
        return this;
    }

    public RoomBuilder withFeatures(List<Feature> features) {
        this.features = features;
        return this;
    }

    public Room build() {
        Room room = new Room();
        room.setId(id);
        room.setFloor(floor);
        room.setNumber(number);
        room.setName(name);
        room.setDescription(description);
        room.setFeatures(features);
        room.setBuilding(building);
        return room;
    }
}

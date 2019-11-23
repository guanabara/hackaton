package com.mindera.hackaton.api.data.repository.builders;


import com.mindera.hackaton.api.model.Building;
import com.mindera.hackaton.api.model.Console;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public final class ConsoleBuilder {
    private String name;
    private String room;
    private Building building = null;
    private OffsetDateTime lastSeen;

    private ConsoleBuilder() {
    }

    public static com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder aConsole() {
        return new com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder();
    }

    public com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder withRoom(String room) {
        this.room = room;
        return this;
    }

    public com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder withBuilding(Building building) {
        this.building = building;
        return this;
    }

    public com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder withLastSeen(OffsetDateTime lastSeen) {
        this.lastSeen = lastSeen;
        return this;
    }

    public Console build() {
        Console console = new Console();
        console.setName(name);
        console.setRoom(room);
        console.setBuilding(building);
        console.setLastSeen(lastSeen);
        return console;
    }
}

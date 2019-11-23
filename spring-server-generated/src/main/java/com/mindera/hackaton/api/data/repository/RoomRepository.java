package com.mindera.hackaton.api.data.repository;

import com.mindera.hackaton.api.data.exception.UnknownResourceWithIdException;
import com.mindera.hackaton.api.data.repository.builders.RoomBuilder;
import com.mindera.hackaton.api.model.Building;
import com.mindera.hackaton.api.model.Feature;
import com.mindera.hackaton.api.model.Room;
import com.mindera.hackaton.api.model.RoomStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class RoomRepository {
    private final List<Room> knownRooms;
    private Random random = new Random();

    public RoomRepository() {
        this.knownRooms = initializeOfflineRooms();
    }

    public List<Room> getAllRooms() {
        return this.knownRooms;
    }

    private List<Room> initializeOfflineRooms() {
        return Stream.of(106, 116, 118, 119, 201, 203, 216, 307, 311, 404, 407, 502, 507)
                .map(roomNumber -> RoomBuilder.aRoom()
                        .withId(roomNumber)
                        .withBuilding(Building.MAPFRE_PORTO)
                        .withFloor(Integer.divideUnsigned(roomNumber, 100))
                        .withNumber(roomNumber)
                        .withDescription("sala de reuniões")
                        .withFeatures(this.randomFeatures())
                        .withName("MAPFRE " + roomNumber))
                .map(RoomBuilder::build)
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 30*1000)
    private void updateRoomLastSeen() {
        this.knownRooms.forEach(room -> room.setStatus(this.randomEnum(RoomStatus.class)));
    }

    private <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private List<Feature> randomFeatures() {
        return IntStream.range(0, this.random.nextInt(Feature.values().length))
                .boxed()
                .map(i -> Feature.values()[i])
                .distinct()
                .collect(Collectors.toList());
    }

    public Room getById(Integer id) {
        return knownRooms.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UnknownResourceWithIdException(id));
    }
}

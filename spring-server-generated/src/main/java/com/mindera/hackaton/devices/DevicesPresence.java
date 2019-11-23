package com.mindera.hackaton.devices;

import com.mindera.hackaton.api.data.repository.builders.ConsoleBuilder;
import com.mindera.hackaton.api.model.Building;
import com.mindera.hackaton.api.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DevicesPresence {
    private Map<String, Console> hosts;

    @Autowired
    public DevicesPresence() {
        this.hosts = new HashMap<>();
        this.hosts.put("10.10.68.195", ConsoleBuilder.aConsole()
                .withBuilding(Building.MAPFRE_PORTO)
                .withName("PS4")
                .withRoom("204")
                .build());

        this.hosts.put("invalid", ConsoleBuilder.aConsole()
                .withBuilding(Building.MAPFRE_PORTO)
                .withName("XBOX ONE")
                .withRoom("119")
                .build());


    }

    public List<Console> getAll() {
        return new ArrayList<>(this.hosts.values());
    }

    @Scheduled(fixedRate = 5000)
    private void updateDevicesPresence() {
        hosts.keySet().parallelStream()
                .forEach(host -> {
                    if (this.isReachable(host)) {
                        hosts.get(host).setLastSeen(OffsetDateTime.now());
                    }
                });
    }

    private Boolean isReachable(String target) {
        try {
            return InetAddress.getByName(target).isReachable(1000);
        } catch (IOException e) {
            //do nothing
        }

        return false;
    }
}

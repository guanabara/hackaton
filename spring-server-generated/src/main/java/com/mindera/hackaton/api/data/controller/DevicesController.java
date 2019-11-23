package com.mindera.hackaton.api.data.controller;

import com.mindera.hackaton.api.DevicesApi;
import com.mindera.hackaton.api.model.Console;
import com.mindera.hackaton.devices.DevicesPresence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DevicesController implements DevicesApi {

    private DevicesPresence service;

    @Autowired
    public DevicesController(DevicesPresence service) {
        this.service = service;
    }

    @Override
    @RequestMapping(value = "/devices", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Console>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}

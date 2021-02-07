package com.example.test.controller;

import com.example.test.model.Zone;
import com.example.test.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @GetMapping
    public List<Zone> getAll() {
        return zoneService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Zone zone) {
        zoneService.create(zone);
    }
}

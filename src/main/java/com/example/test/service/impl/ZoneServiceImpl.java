package com.example.test.service.impl;

import com.example.test.model.Zone;
import com.example.test.repository.ZonaDAO;
import com.example.test.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZonaDAO zonaDAO;

    @Override
    public List<Zone> getAll() {
        return this.zonaDAO.findAll();
    }

    @Override
    public void create(Zone zone) {
        this.zonaDAO.create(zone);
    }
}

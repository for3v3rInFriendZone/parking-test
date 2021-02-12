package com.example.test.service.impl;

import com.example.test.model.ParkingKarte;
import com.example.test.repository.ParkingKartaDAO;
import com.example.test.service.ParkingKarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingKarteServiceImpl implements ParkingKarteService {

    @Autowired
    private ParkingKartaDAO parkingKartaDAO;

    @Override
    public List<ParkingKarte> getAllTrajanjeFilter(Integer from, Integer to) {
        if (from == null && to == null) {
            return this.parkingKartaDAO.findAll();
        } else if (to == null) {
            return this.parkingKartaDAO.getAllTrajanjeFrom(from);
        } else if (from == null) {
            return this.parkingKartaDAO.getAllTrajanjeTo(to);
        } else {
            return this.parkingKartaDAO.getAllTrajanjeBetween(from, to);
        }
    }
}

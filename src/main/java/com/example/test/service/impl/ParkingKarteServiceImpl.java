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
     private ParkingKartaDAO parkingKarteRepository;

    @Override
    public List<ParkingKarte> getAll() {
        return this.parkingKarteRepository.findAll();
    }

    @Override
    public List<ParkingKarte> getAllTrajanjeBetween(Integer from, Integer to) {
        if (from == null && to == null) {
            return this.parkingKarteRepository.findAll();
        } else if (to == null) {
            return this.parkingKarteRepository.getAllTrajanjeFrom(from);
        } else if (from == null) {
            return this.parkingKarteRepository.getAllTrajanjeTo(to);
        } else {
            return this.parkingKarteRepository.getAllTrajanjeBetween(from, to);
        }
    }
}

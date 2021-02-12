package com.example.test.service;

import com.example.test.model.ParkingKarte;

import java.util.List;

public interface ParkingKarteService {
    List<ParkingKarte> getAllTrajanjeFilter(Integer from, Integer to);
}

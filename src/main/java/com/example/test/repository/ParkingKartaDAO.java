package com.example.test.repository;

import com.example.test.model.OsobaSaInvaliditetom;
import com.example.test.model.ParkingKarte;
import com.example.test.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingKartaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParkingKarte> findAll() {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id";

        ParkingKartaRowCallBackHandler handler = new ParkingKartaRowCallBackHandler();
        jdbcTemplate.query(sql, handler);

        return handler.getParkingKarte();
    }

    public List<ParkingKarte> getAllTrajanjeFrom(Integer from) {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id " +
                "WHERE pk.trajanjeUMinutima >= ?";

        ParkingKartaRowCallBackHandler handler = new ParkingKartaRowCallBackHandler();
        jdbcTemplate.query(sql, handler, from);

        return handler.getParkingKarte();
    }

    public List<ParkingKarte> getAllTrajanjeTo(Integer to) {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id " +
                "WHERE pk.trajanjeUMinutima <= ?";

        ParkingKartaRowCallBackHandler hanlder = new ParkingKartaRowCallBackHandler();
        jdbcTemplate.query(sql, hanlder, to);

        return hanlder.getParkingKarte();
    }

    public List<ParkingKarte> getAllTrajanjeBetween(Integer from, Integer to) {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id " +
                "WHERE pk.trajanjeUMinutima BETWEEN ? AND ?";

        ParkingKartaRowCallBackHandler handler = new ParkingKartaRowCallBackHandler();
        jdbcTemplate.query(sql, handler, from, to);

        return handler.getParkingKarte();
    }

    // Ovo je privatna klasa u kojoj se zapravo mapiraju podaci koji se dobiju iz baze, nakon sto se izvrsi select upit na objekat klase ParkingKarte
    private static class ParkingKartaRowCallBackHandler implements RowCallbackHandler {
        private List<ParkingKarte> parkingKarte = new ArrayList<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String registracija = resultSet.getString(index++);
            String pocetakParkinga = resultSet.getString(index++);
            Integer trajanjeUMinutima = resultSet.getInt(index++);
            OsobaSaInvaliditetom osobaSaInvaliditetom = OsobaSaInvaliditetom.valueOf(resultSet.getString(index++));
            String nazivZone = resultSet.getString(index++);

            ParkingKarte parkingKarta = new ParkingKarte();
            parkingKarta.setPocetakParkinga(pocetakParkinga);
            parkingKarta.setRegistracija(registracija);
            parkingKarta.setTrajanjeUMinutima(trajanjeUMinutima);
            parkingKarta.setOsobaSaInvaliditetom(osobaSaInvaliditetom);
            parkingKarta.setId(id);

            Zone zona = new Zone();
            zona.setNaziv(nazivZone);
            parkingKarta.setZone(zona);

            parkingKarte.add(parkingKarta);
        }

        public List<ParkingKarte> getParkingKarte() {
            return parkingKarte;
        }
    }

}

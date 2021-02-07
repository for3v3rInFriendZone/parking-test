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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParkingKartaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParkingKarte> findAll() {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id";

        ParkingKartaRowCallBackHanlder hanlder = new ParkingKartaRowCallBackHanlder();
        jdbcTemplate.query(sql, hanlder);

        return hanlder.getParkingKarte();
    }

    public List<ParkingKarte> getAllTrajanjeFrom(Integer from) {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id " +
                "WHERE pk.trajanjeUMinutima >= ?";

        ParkingKartaRowCallBackHanlder hanlder = new ParkingKartaRowCallBackHanlder();
        jdbcTemplate.query(sql, hanlder, from);

        return hanlder.getParkingKarte();
    }

    public List<ParkingKarte> getAllTrajanjeTo(Integer to) {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id " +
                "WHERE pk.trajanjeUMinutima <= ?";

        ParkingKartaRowCallBackHanlder hanlder = new ParkingKartaRowCallBackHanlder();
        jdbcTemplate.query(sql, hanlder, to);

        return hanlder.getParkingKarte();
    }

    public List<ParkingKarte> getAllTrajanjeBetween(Integer from, Integer to) {
        String sql = "SELECT pk.id, pk.registracija, pk.pocetakParkinga, pk.trajanjeUMinutima, pk.osobaSaInvaliditetom, z.naziv " +
                "FROM parkingKarte pk " +
                "JOIN zone z ON pk.zonaId = z.id " +
                "WHERE pk.trajanjeUMinutima BETWEEN ? AND ?";

        ParkingKartaRowCallBackHanlder hanlder = new ParkingKartaRowCallBackHanlder();
        jdbcTemplate.query(sql, hanlder, from, to);

        return hanlder.getParkingKarte();
    }

    // Ovo je privatna klasa u kojoj se zapravo mapiraju podaci koji se dobiju iz baze, nakon sto se izvrsi select upit na objekat klase ParkingKarte
    private static class ParkingKartaRowCallBackHanlder implements RowCallbackHandler {
        private Map<Long, ParkingKarte> parkingKarte = new LinkedHashMap<>();

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

            parkingKarte.put(parkingKarta.getId(), parkingKarta);
        }

        public List<ParkingKarte> getParkingKarte() {
            return new ArrayList<>(parkingKarte.values());
        }
    }

}

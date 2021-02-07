package com.example.test.repository;

import com.example.test.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ZonaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Zone> findAll() {
        String sql = "SELECT * from zone";

        ZonaDAO.ZonaRowCallBackHanlder hanlder = new ZonaDAO.ZonaRowCallBackHanlder();
        jdbcTemplate.query(sql, hanlder);

        return hanlder.getZone();
    }

    public void create(Zone zone) {
        String sql = "INSERT INTO zone(naziv, cenaZaSat, dozvoljenoVremeParkingaUSatima) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, zone.getNaziv(), zone.getCenaZaSat(), zone.getDozvoljenoVremeParkingaUSatima());
    }

    // Ovo je privatna klasa u kojoj se zapravo mapiraju podaci koji se dobiju iz baze, nakon sto se izvrsi select upit na objekat klase Zone
    private static class ZonaRowCallBackHanlder implements RowCallbackHandler {
        private Map<Integer, Zone> zoneMap = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Integer id = resultSet.getInt(index++);
            String naziv = resultSet.getString(index++);
            BigDecimal cenaZaSat = BigDecimal.valueOf(resultSet.getDouble(index++));
            Integer dozvoljenoVremeParkingaUSatima = resultSet.getInt(index++);

            Zone zone = new Zone();
            zone.setId(id);
            zone.setNaziv(naziv);
            zone.setCenaZaSat(cenaZaSat);
            zone.setDozvoljenoVremeParkingaUSatima(dozvoljenoVremeParkingaUSatima);


            zoneMap.put(zone.getId(), zone);
        }

        public List<Zone> getZone() {
            return new ArrayList<>(zoneMap.values());
        }
    }
}

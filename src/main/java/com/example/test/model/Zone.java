package com.example.test.model;

import java.math.BigDecimal;

public class Zone {

    private Integer id;

    private String naziv;

    private BigDecimal cenaZaSat;

    private Integer dozvoljenoVremeParkingaUSatima;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCenaZaSat() {
        return cenaZaSat;
    }

    public void setCenaZaSat(BigDecimal cenaZaSat) {
        this.cenaZaSat = cenaZaSat;
    }

    public Integer getDozvoljenoVremeParkingaUSatima() {
        return dozvoljenoVremeParkingaUSatima;
    }

    public void setDozvoljenoVremeParkingaUSatima(Integer dozvoljenoVremeParkingaUSatima) {
        this.dozvoljenoVremeParkingaUSatima = dozvoljenoVremeParkingaUSatima;
    }
}

package com.example.test.dto;

public class ParkingKarteRequest {

    private String registracija;

    private String pocetakParkinga;

    private Integer trajanjeUMinutima;

    private String zona;

    private String osobaSaInvaliditetom;

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public String getPocetakParkinga() {
        return pocetakParkinga;
    }

    public void setPocetakParkinga(String pocetakParkinga) {
        this.pocetakParkinga = pocetakParkinga;
    }

    public Integer getTrajanjeUMinutima() {
        return trajanjeUMinutima;
    }

    public void setTrajanjeUMinutima(Integer trajanjeUMinutima) {
        this.trajanjeUMinutima = trajanjeUMinutima;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getOsobaSaInvaliditetom() {
        return osobaSaInvaliditetom;
    }

    public void setOsobaSaInvaliditetom(String osobaSaInvaliditetom) {
        this.osobaSaInvaliditetom = osobaSaInvaliditetom;
    }
}

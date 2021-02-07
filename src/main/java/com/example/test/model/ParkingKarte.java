package com.example.test.model;


public class ParkingKarte {

    private Long id;

    private String registracija;

    private String pocetakParkinga;

    private Integer trajanjeUMinutima;

    private OsobaSaInvaliditetom osobaSaInvaliditetom;

    private Zone zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public OsobaSaInvaliditetom getOsobaSaInvaliditetom() {
        return osobaSaInvaliditetom;
    }

    public void setOsobaSaInvaliditetom(OsobaSaInvaliditetom osobaSaInvaliditetom) {
        this.osobaSaInvaliditetom = osobaSaInvaliditetom;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "ParkingKarte{" +
                "id=" + id +
                ", registracija='" + registracija + '\'' +
                ", pocetakParkinga='" + pocetakParkinga + '\'' +
                ", trajanjeUMinutima=" + trajanjeUMinutima +
                ", osobaSaInvaliditetom=" + osobaSaInvaliditetom +
                ", zone=" + zone.getNaziv() +
                '}';
    }
}

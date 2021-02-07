package com.example.test.controller;

import com.example.test.dto.ParkingKarteRequest;
import com.example.test.model.OsobaSaInvaliditetom;
import com.example.test.model.ParkingKarte;
import com.example.test.model.Zone;
import com.example.test.repository.ParkingKartaDAO;
import com.example.test.service.ParkingKarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ParkingKarteController {

    private static final String PARKING_KARTE_SESSION = "PARKING_KARTE";

    private static Long generatedId = 0L;
    private int ukupanBrojCrvenihKarata = 0;
    private final List<Zone> zone = generateZones();
    private List<ParkingKarte> parkingKarte = getParkingKarte();

    @Autowired
    private ParkingKarteService parkingKarteService;

    @Autowired
    private ParkingKartaDAO parkingKartaDAO;

    @GetMapping("/zadatak2.html")
    public String getParkingKartaView(Model model) {
        model.addAttribute("parkingKarta", new ParkingKarteRequest());

        return "zadatak2";
    }

    @PostMapping("/novaKarta")
    public String createParkingKarta(ParkingKarteRequest parkingKarteRequest, HttpServletRequest request) {
        List<ParkingKarte> parkingKarteKorisnika = getParkingKarteFromSession(request);

        // Racunanje koliko ima crvenih parking karata
        if (parkingKarteRequest.getZona().equals("crvena")) {
            ukupanBrojCrvenihKarata++;
        }

        Zone zona = getZonaByName(parkingKarteRequest.getZona());

        final ParkingKarte parkingKarta = new ParkingKarte();
        parkingKarta.setPocetakParkinga(parkingKarteRequest.getPocetakParkinga());
        parkingKarta.setRegistracija(parkingKarteRequest.getRegistracija());
        parkingKarta.setTrajanjeUMinutima(parkingKarteRequest.getTrajanjeUMinutima());
        parkingKarta.setOsobaSaInvaliditetom(OsobaSaInvaliditetom.valueOf(parkingKarteRequest.getOsobaSaInvaliditetom()));
        parkingKarta.setId(++generatedId);
        parkingKarta.setZone(zona);

        //dodavanje nove Parking karte u List-u
        parkingKarte.add(parkingKarta);

        //Dodavanje u sesiju korisnika novu Parking kartu, ali NJEGOVU SAMO!!!
        parkingKarteKorisnika.add(parkingKarta);
        request.getSession().setAttribute(PARKING_KARTE_SESSION, parkingKarteKorisnika);

        //Ispis Parking Karata u konzoli, prikaz podataka iz memorije
        for (ParkingKarte pk : parkingKarte) {
            System.out.println(pk);
        }
        System.out.println("Ukupan broj crvenih karata: " + ukupanBrojCrvenihKarata);

        return "redirect:/ParkingKarte/Zadatak3";
    }

    @GetMapping("/ParkingKarte/Zadatak3")
    public String showParkingKarte(Model model, HttpServletRequest request) {
        List<ParkingKarte> parkingKarteKorisnika = getParkingKarteFromSession(request);

        List<ParkingKarte> asd = parkingKartaDAO.findAll();

        // Ispisati sesiju korisnika i njegove Parking karte u konzolu
        for (ParkingKarte pk : parkingKarteKorisnika) {
            System.out.println("ISPIS IZ SESIJE: " + pk);
        }

        model.addAttribute("parkingKarte", asd);

        Double zarada = calculateZarada();
        model.addAttribute("zarada", zarada);

        //Racunanje broja crvenih parking karata
        int brojCrvenihParkingKarata = 0;
        for (ParkingKarte karta : parkingKarte) {
            if (karta.getZone().getNaziv().equals("crvena")) {
                brojCrvenihParkingKarata++;
            }
            System.out.println(karta);
            System.out.println("Broj crvenih parking karata: " + brojCrvenihParkingKarata);
        }

        return "zadatak3";
    }

    @GetMapping("/ParkingKarte/Zadatak5")
    public String getParkingKarteSearch(Model model, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {
        List<ParkingKarte> parkingKarte = this.parkingKarteService.getAllTrajanjeBetween(from, to);

        // Ispis svih filtriranih rezultata u konzoli
        for (ParkingKarte karta : parkingKarte) {
            System.out.println(karta);
        }

        model.addAttribute("parkingKarte", parkingKarte);

        return "zadatak5";
    }

    @GetMapping("/zadatak6.html")
    public String getValidationView() {
        return "zadatak6";
    }

    @GetMapping("/zadatak7.html")
    public String getZoneView() {
        return "zadatak7";
    }

    private static List<Zone> generateZones() {
        final List<Zone> zones = new ArrayList<>();
        Zone zone1 = new Zone();
        zone1.setNaziv("bela");
        zone1.setCenaZaSat(BigDecimal.valueOf(35.00));

        Zone zone2 = new Zone();
        zone2.setNaziv("plava");
        zone2.setCenaZaSat(BigDecimal.valueOf(45.00));

        Zone zone3 = new Zone();
        zone3.setNaziv("crvena");
        zone3.setCenaZaSat(BigDecimal.valueOf(53.00));
        zone3.setDozvoljenoVremeParkingaUSatima(2);

        zones.add(zone1);
        zones.add(zone2);
        zones.add(zone3);

        return zones;
    }

    private static List<ParkingKarte> getParkingKarte() {
        final List<ParkingKarte> lista = new ArrayList<>();

        ParkingKarte parkingKarte = new ParkingKarte();
        parkingKarte.setPocetakParkinga("2021-02-07 11:31:31");
        parkingKarte.setZone(generateZones().get(0));
        parkingKarte.setOsobaSaInvaliditetom(OsobaSaInvaliditetom.DA);
        parkingKarte.setTrajanjeUMinutima(121);
        parkingKarte.setRegistracija("BG-031-JD");

        lista.add(parkingKarte);

        return lista;
    }

    private List<ParkingKarte> getParkingKarteFromSession(HttpServletRequest request) {
        List<ParkingKarte> parkingKarteKorisnika = (List<ParkingKarte>) request.getSession().getAttribute(PARKING_KARTE_SESSION);
        if (parkingKarteKorisnika == null) {
            parkingKarteKorisnika = new ArrayList<>();
        }

        return parkingKarteKorisnika;
    }

    private Zone getZonaByName(String nazivZone) {
        // Pronalazenje Zona OBJEKTA!!!!! kom nova Parking karta pripada (strani kljuc u principu)
        Zone zona = null;
        for (Zone trenutnaZona : this.zone) {
            if (trenutnaZona.getNaziv().equals(nazivZone)) {
                zona = trenutnaZona;
                break;
            }
        }

        return zona;
    }

    private Double calculateZarada() {
        // Racunanje ukupne zarada za sve Parking Karte iz memorije
        Double zarada = 0.0;
        for (ParkingKarte parkingKarte1 : parkingKarte) {
            if (parkingKarte1.getOsobaSaInvaliditetom().equals(OsobaSaInvaliditetom.NE)) {
                int brojCasova = parkingKarte1.getTrajanjeUMinutima() / 60;
                if (parkingKarte1.getTrajanjeUMinutima() % 60 != 0) {
                    brojCasova++;
                }
                zarada += brojCasova * parkingKarte1.getZone().getCenaZaSat().doubleValue();
            }
        }

        return zarada;
    }
}

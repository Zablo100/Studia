package webapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Zadanie {


//    private Projekt projekt;

    private Integer zadanieId;

    private String nazwa;

    private Integer kolejnosc;

    private String opis;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public LocalDateTime data_oddania;

//    public Projekt getProjekt() {
//        return projekt;
//    }
//
//    public void setProjekt(Projekt projekt) {
//        this.projekt = projekt;
//    }

    public Integer getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(Integer zadanieId) {
        this.zadanieId = zadanieId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(Integer kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getdata_oddania() {
        return data_oddania;
    }

    public void setdata_oddania(LocalDateTime data) {
        this.data_oddania = data;
    }

    public Zadanie(){

    }
    public Zadanie(String nazwa, Integer kolejnosc, String opis) {
        this.nazwa = nazwa;
        this.kolejnosc = kolejnosc;
        this.opis = opis;
    }
}

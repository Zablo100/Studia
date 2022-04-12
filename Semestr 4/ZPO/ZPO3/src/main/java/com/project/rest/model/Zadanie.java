package com.project.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "zadanie")
public class Zadanie {

    @ManyToOne
    @JoinColumn(name = "projekt_id")
    private Projekt projekt;

    @Id
    @GeneratedValue
    @Column(name = "zadanie_id")
    private Integer zadanieId;

    @Column(nullable = false, length = 50)
    private String nazwa;

    @Column
    private Integer kolejnosc;

    @Column(length = 1000)
    private String opis;

    @Column(nullable = false)
    private LocalDateTime dataczas_dodania;

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

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

    public LocalDateTime getDataczas_dodania() {
        return dataczas_dodania;
    }

    public void setDataczas_dodania(LocalDateTime dataczas_dodania) {
        this.dataczas_dodania = dataczas_dodania;
    }

    public Zadanie(){

    }
    public Zadanie(String nazwa, Integer kolejnosc, String opis) {
        this.nazwa = nazwa;
        this.kolejnosc = kolejnosc;
        this.opis = opis;
    }
}

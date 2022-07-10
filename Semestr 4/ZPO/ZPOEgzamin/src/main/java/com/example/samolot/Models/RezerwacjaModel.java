package com.example.samolot.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Rezerwacja")
public class RezerwacjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "klient_id", referencedColumnName = "Id")
    private KlientModel klient;

    @OneToOne
    @JoinColumn(name = "miejsce_id", referencedColumnName = "Id")
    private MiejsceModel miejsce;

    public RezerwacjaModel(KlientModel klient, MiejsceModel miejsce) {
        this.klient = klient;
        this.miejsce = miejsce;
    }
}

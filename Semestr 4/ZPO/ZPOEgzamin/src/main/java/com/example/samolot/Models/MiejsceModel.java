package com.example.samolot.Models;

import com.example.samolot.Services.MiejsceService;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Miejsce")
@Setter
@Getter
public class MiejsceModel {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer numer;

    private Integer rzad;

    private boolean zarezerwowane;

}

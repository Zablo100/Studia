package com.example.projekt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PcLog {
    @Id
    @GeneratedValue
    private int id;

    private LocalDate date;
    private int pcId;
    private String msg;
}

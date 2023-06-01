package com.example.projekt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Printer {
    @Id
    @GeneratedValue
    private int id;

    private String model;
    private String manufacturer;
    private String serialNumber;
    private String invoiceDescription;
    private String description;
    private String contractNumber;
    private String ip;
    private String department;

    private boolean test;

}

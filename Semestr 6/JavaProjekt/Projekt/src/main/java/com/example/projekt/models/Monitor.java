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
public class Monitor {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String hardwareID;
}

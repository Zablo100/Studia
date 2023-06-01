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
public class HardDrive {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int size;
    private String type;
}

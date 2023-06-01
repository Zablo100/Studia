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
public class Item {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int quantity;
    private String description ;
    private double price;

}

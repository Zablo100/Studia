package com.example.projekt.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String seller;
    private LocalDate date;
    private double totalCost;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name="ii_fk", referencedColumnName = "id")
    private List<Item> items;
}

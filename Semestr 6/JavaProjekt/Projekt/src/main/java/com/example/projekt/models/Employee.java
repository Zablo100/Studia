package com.example.projekt.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private User user;

    private String name;
    private String lastName;
    private String email;
    private String JobTitle;

    @OneToOne(targetEntity = Factory.class)
    @JoinColumn(referencedColumnName = "id")
    private Factory factory;

    @OneToOne(targetEntity = Department.class)
    @JoinColumn(referencedColumnName = "id")
    private Department department;

    @OneToOne(targetEntity = Computer.class)
    @JoinColumn(referencedColumnName = "id")
    private Computer computer;
}
